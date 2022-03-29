package com.napier.application.logic;

import com.napier.application.data.City;
import com.napier.application.data.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Generates reports pulled from database
 */
public class Report {
    /**
     * Connection to MySQL database
     */
    private Connection connection;

    public Report(Connection connection) { this.connection = connection; }

    /**
     * Generates country report for all countries in database
     * @return an ArrayList of Country
     */
    public ArrayList<Country> countryReport() {
        try {
            // Create an SQL statement
            Statement statement = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, NAME, Continent, Region, Population, Capital "
                            + "FROM country c "
                            + "ORDER BY NAME ASC ";

            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<Country>();

            while (resultSet.next()) {
                Country country = new Country();
                country.Code = resultSet.getString("Code");
                country.Name = resultSet.getString("NAME");
                country.Continent = resultSet.getString("Continent");
                country.Region = resultSet.getString("Region");
                country.Population = resultSet.getInt("Population");
                country.Capital = resultSet.getInt("Capital");
                countries.add(country);
            }
            // Print header
            System.out.println("COUNTRY REPORT");
            System.out.println(String.format("%-20s %20s %20s %20s %20s %20s ",
                                             "Code", "Name", "Continent", "Region", "Population", "Capital"));
            // Loop over all employees in the list
            for (Country country : countries) {
                String string = String.format("%-20s %20s %20s %20s %20s %20s ",
                                country.Code, country.Name, country.Continent, country.Region,
                                country.Population, country.Capital);
                System.out.println(string);
            }
            return countries;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country report");
            return null;
        }
    }

    public ArrayList<City> cityReport() {
        try {
            // Create an SQL statement
            Statement statement = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                            + "FROM city ci "
                            + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                            + "ORDER BY ci.Name ASC ";

            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<City>();

            while (resultSet.next()) {
                City city = new City();
                city.Name = resultSet.getString("NAME");
                city.Country = resultSet.getString("Country");
                city.District = resultSet.getString("District");
                city.Population = resultSet.getInt("Population");
                cities.add(city);
            }
            // Print header
            System.out.println("CITY REPORT");
            System.out.println(String.format("%-20s %20s %20s %20s ", "Name", "Country", "District", "Population"));
            // Loop over all employees in the list
            for (City city : cities) {
                String string = String.format("%-20s %20s %20s %20s ",
                                city.Name, city.Country, city.District, city.Population);
                System.out.println(string);
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report");
            return null;
        }
    }

    public ArrayList<City> capitalCityReport() {
        try {
            // Create an SQL statement
            Statement statement = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, co.Name AS Country, ci.Population "
                            + "FROM city ci "
                            + "INNER JOIN country co ON ci.ID = co.Capital "
                            + "ORDER BY ci.Name ASC ";

            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<City>();

            while (resultSet.next()) {
                City city = new City();
                city.Name = resultSet.getString("NAME");
                city.Country = resultSet.getString("Country");
                city.Population = resultSet.getInt("Population");
                cities.add(city);
            }
            // Print header
            System.out.println("CAPITAL CITY REPORT");
            System.out.println(String.format("%-20s %20s %20s ", "Name", "Country", "Population"));
            // Loop over all employees in the list
            for (City city : cities) {
                String string = String.format("%-20s %20s %20s ",
                                city.Name, city.Country, city.Population);
                System.out.println(string);
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report");
            return null;
        }
    }

    public ArrayList<Country> populationReport(String option) {

        if (option == null) {
            throw new IllegalArgumentException("Option for this query must be specified");
        }

        try {
            if (option.equals("Continent")) {
                // Create an SQL statement
                Statement stmt = connection.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT co.Continent AS Continent, SUM(co.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "round(((SUM(ci.Population)/SUM(co.Population))*100),2) AS In_City_Perc, " +
                                "SUM(co.Population) - SUM(ci.Population) AS Out_city, " +
                                "round((((SUM(co.Population) - SUM(ci.Population))/SUM(co.Population))*100),2) AS Out_City_Perc " +
                                "FROM country co " +
                                "JOIN (SELECT " +
                                "ci.countrycode " +
                                ",SUM(ci.population) AS Population " +
                                "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code " +
                                "GROUP BY Continent";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Continent = rset.getString("Continent");
                    country.Population = rset.getLong("Total_Pop");
                    country.InCityPop = rset.getLong("In_city");
                    country.InCityPerc = rset.getLong("In_City_Perc");
                    country.OutCityPop = rset.getLong("Out_city");
                    country.OutCityPerc = rset.getLong("Out_City_Perc");
                    countries.add(country);
                }
                System.out.println("Population Report By Continent");
                System.out.println(String.format("%-10s %25s %25s %25s %25s %25s ",
                        "Continent", "Total Population", "Population In Cities", "Population In Cities (%)", "Population Out of Cities", "Population Out of Cities (%)"));
                // Loop over all employees in the list
                for (Country country : countries) {
                    String string = String.format("%-10s %25s %25s %25s %25s %25s ",
                            country.Continent, country.Population, country.InCityPop, country.InCityPerc, country.OutCityPop, country.OutCityPerc);
                    System.out.println(string);
                }
                return countries;

            } else if (option.equals("Region")) {
                // Create an SQL statement
                Statement stmt = connection.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT co.Region AS Region, SUM(co.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "round(((SUM(ci.Population)/SUM(co.Population))*100),2) AS In_City_Perc, " +
                                "SUM(co.Population) - SUM(ci.Population) AS Out_city, " +
                                "round((((SUM(co.Population) - SUM(ci.Population))/SUM(co.Population))*100),2) AS Out_City_Perc " +
                                "FROM country co " +
                                "JOIN (SELECT " +
                                "ci.countrycode " +
                                ",SUM(ci.population) AS Population " +
                                "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code " +
                                "GROUP BY Region";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Region = rset.getString("Region");
                    country.Population = rset.getLong("Total_Pop");
                    country.InCityPop = rset.getLong("In_city");
                    country.InCityPerc = rset.getLong("In_City_Perc");
                    country.OutCityPop = rset.getLong("Out_city");
                    country.OutCityPerc = rset.getLong("Out_City_Perc");
                    countries.add(country);
                }
                System.out.println("Population Report By Region");
                System.out.println(String.format("%-10s %25s %25s %25s %25s %25s ",
                        "Region", "Total Population", "Population In Cities", "Population In Cities (%)", "Population Out of Cities", "Population Out of Cities (%)"));
                // Loop over all employees in the list
                for (Country country : countries) {
                    String string = String.format("%-10s %25s %25s %25s %25s %25s ",
                            country.Region, country.Population, country.InCityPop, country.InCityPerc, country.OutCityPop, country.OutCityPerc);
                    System.out.println(string);
                }
                return countries;

            } else if (option.equals("Country")) {
                // Create an SQL statement
                Statement stmt = connection.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT co.Name AS Name, SUM(co.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "round(((SUM(ci.Population)/SUM(co.Population))*100),2) AS In_City_Perc, " +
                                "SUM(co.Population) - SUM(ci.Population) AS Out_city, " +
                                "round((((SUM(co.Population) - SUM(ci.Population))/SUM(co.Population))*100),2) AS Out_City_Perc " +
                                "FROM country co " +
                                "JOIN (SELECT ci.countrycode, SUM(ci.population) AS Population " +
                                "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code " +
                                "GROUP BY Name";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Name = rset.getString("Name");
                    country.Population = rset.getLong("Total_Pop");
                    country.InCityPop = rset.getLong("In_city");
                    country.InCityPerc = rset.getLong("In_City_Perc");
                    country.OutCityPop = rset.getLong("Out_city");
                    country.OutCityPerc = rset.getLong("Out_City_Perc");
                    countries.add(country);
                }
                System.out.println("Population Report By Country");
                System.out.println(String.format("%-10s %25s %25s %25s %25s %25s ",
                        "Country", "Total Population", "Population In Cities", "Population In Cities (%)", "Population Out of Cities", "Population Out of Cities (%)"));
                // Loop over all employees in the list
                for (Country country : countries) {
                    String string = String.format("%-10s %25s %25s %25s %25s %25s ",
                            country.Name, country.Population, country.InCityPop, country.InCityPerc, country.OutCityPop, country.OutCityPerc);
                    System.out.println(string);
                }
                return countries;

            } else {
                throw new IllegalArgumentException("Invalid option specified");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute populationInAndOutOfCities");
            return null;
        }
    }
}
