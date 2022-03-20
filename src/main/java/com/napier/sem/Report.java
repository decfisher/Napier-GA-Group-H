package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Report {

    private Connection connection;

    public Report(Connection connection) { this.connection = connection; }

    public ArrayList<Country> countryReport() {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, NAME, Continent, Region, Population, Capital "
                            + "FROM country c "
                            + "ORDER BY NAME ASC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("NAME");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Capital = rset.getInt("Capital");
                countries.add(country);
            }
            // Print header
            System.out.println("COUNTRY REPORT");
            System.out.println(String.format("%-20s %20s %20s %20s %20s %20s ", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            // Loop over all employees in the list
            for (Country country : countries) {
                String s =
                        String.format("%-20s %20s %20s %20s %20s %20s ",
                                country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital);
                System.out.println(s);
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
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "ORDER BY 4 ASC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.Name = rset.getString("NAME");
                city.Country = rset.getString("Country");
                city.District = rset.getString("District");
                city.Population = rset.getInt("Population");
                cities.add(city);
            }
            // Print header
            System.out.println("CITY REPORT");
            System.out.println(String.format("%-20s %20s %20s %20s ", "Name", "Country", "District", "Population"));
            // Loop over all employees in the list
            for (City city : cities) {
                String s =
                        String.format("%-20s %20s %20s %20s ",
                                city.Name, city.Continent, city.District, city.Population);
                System.out.println(s);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report");
            return null;
        }
    }

    public ArrayList<Country> capitalCityReport() {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, co.Name, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "ORDER BY 4 ASC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.Name = rset.getString("NAME");
                country.Name = rset.getString("NAME");
                country.Population = rset.getInt("Population");
                countries.add(country);
            }
            // Print header
            System.out.println("CAPITAL CITY REPORT");
            System.out.println(String.format("%-20s %20s %20s ", "Name", "Country", "Population"));
            // Loop over all employees in the list
            for (Country country : countries) {
                String s =
                        String.format("%-20s %20s %20s ",
                                country.Name, country.Name, country.Population);
                System.out.println(s);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city report");
            return null;
        }
    }

}
