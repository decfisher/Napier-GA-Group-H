package com.napier.application.logic;

import com.napier.application.data.City;
import com.napier.application.data.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class to produce queries from SQL database
 */
public class SQLQuery {

    private Connection connection;
    private Exporter exporter;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Private constructor to initialise variables
     * @param connection
     * @param exporter
     */
    private SQLQuery(Connection connection, Exporter exporter) {
        this.connection = connection;
        this.exporter = exporter;
    }

    /**
     * Public constructor to use outwith class that only requires connection pass
     * @param connection
     */
    public SQLQuery(Connection connection) {
        this(connection, new Exporter());
    }

    /**
     * Country population - Largest to smallest
     */

    /**
     * Output population of all countries in the world - largest to smallest
     * @return
     */
    public ArrayList<Country> getCountryPopulation() {
        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query =
                    "SELECT co.Code, co.Name, co.Continent, co.Region, co.Population, ci.Name AS CapitalCity "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "ORDER BY co.Population DESC ";
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<Country> result = addCountries(resultSet);
            // Generate report and send to "reports" folder
            exporter.countryReport(result, "AllCountriesInTheWorldLargestToSmallest");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
            return null;
        }
    }

    /**
     * Output population of all countries in a continent/region - largest to smallest
     * @param option
     * @return
     */
    public ArrayList<Country> getCountryPopulation(String option) {
        return null;
    }

    /**
     * Top N populated countries
     */

    /**
     * City population - Largest to smallest
     */

    public ArrayList<City> getCityPopulation() {
        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query =
                    "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                            + "FROM city ci "
                            + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                            + "ORDER BY ci.Population DESC ";
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<City> result = addCities(resultSet);
            // Generate report and send to "reports" folder
            exporter.cityReport(result, "AllCitiesInTheWorldLargestToSmallest");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities");
            return null;
        }
    }

    /**
     * Top N populated cities
     */

    /**
     * Capital city population - Largest to smallest
     */

    /**
     * Top N populated capital cities
     */

    /**
     * Population in and out of cities
     */

    /**
     * Total population
     */

    /**
     * Languages - Percentage spoken
     */


    /**
     * Helper methods
     */

    private ResultSet getResultSet(Statement statement, String query) throws SQLException {
        return statement.executeQuery(query);
    }

    private ArrayList<Country> addCountries(ResultSet resultSet) throws SQLException {
        ArrayList<Country> countries = new ArrayList<>();

        while (resultSet.next()) {
            Country country = new Country();
            country.Code = resultSet.getString("Code");
            country.Name = resultSet.getString("NAME");
            country.Continent = resultSet.getString("Continent");
            country.Region = resultSet.getString("Region");
            country.Population = resultSet.getInt("Population");
            String capCity = resultSet.getString("CapitalCity");
            if (capCity == null) {
                country.CapitalCity = "No capital";
            } else {
                country.CapitalCity = capCity;
            }
            countries.add(country);
        }
        return countries;
    }

    private ArrayList<City> addCities(ResultSet resultSet) throws SQLException {
        ArrayList<City> cities = new ArrayList<>();

        while (resultSet.next()) {
            City city = new City();
            city.Name = resultSet.getString("NAME");
            city.Country = resultSet.getString("Country");
            city.District = resultSet.getString("District");
            city.Population = resultSet.getInt("Population");
            cities.add(city);
        }
        return cities;
    }
}
