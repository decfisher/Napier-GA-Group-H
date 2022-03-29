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
}
