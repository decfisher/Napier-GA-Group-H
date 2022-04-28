package com.napier.application.logic;

import com.napier.application.data.City;
import com.napier.application.data.Country;

import javax.xml.transform.Result;
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
     *
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
     * @param option - specifies whether continent or region
     * @param input - specifies the name of continent or region to filter by
     * @return
     */
    public ArrayList<Country> getCountryPopulation(String option, String input) {
        if (option.isEmpty()) {
            throw new IllegalArgumentException("Option for query must be specified!");
        }

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input must be specified!");
        }

        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query;

            if (option.equals("Continent")) {
                query =
                        "SELECT co.Code, ci.Name, co.Continent, co.Region, ci.Population, ci.Name AS CapitalCity "
                                + "FROM country co "
                                + "LEFT JOIN city ci ON co.Capital = ci.ID "
                                + "WHERE co.Continent = '" + input + "' "
                                + "ORDER BY ci.Population DESC; ";

            } else if (option.equals("Region")) {
                query =
                        "SELECT co.Code, ci.Name, co.Continent, co.Region, ci.Population, ci.Name AS CapitalCity "
                                + "FROM country co "
                                + "LEFT JOIN city ci ON co.Capital = ci.ID "
                                + "WHERE co.Region = '" + input + "' "
                                + "ORDER BY ci.Population DESC; ";
            } else {
                throw new IllegalArgumentException("Invalid option specified!");
            }
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<Country> result = addCountries(resultSet);
            // Generate report and send to "reports" folder
            exporter.countryReport(result, "AllCountriesIn" + input + "LargestToSmallest");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries!");
            return null;
        }
    }

    /**
     * Top N populated countries
     */

    /**
     * City population - Largest to smallest
     */

    /**
     * Outputs population of all cities in the world - largest to smallest
     * @return
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

    public ArrayList<City> getCapitalCityPopulation() {
        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query =
                    "SELECT ci.Name, co.Name AS Country, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "ORDER BY ci.Population DESC; ";


            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<City> result = addCapitalCities(resultSet);
            // Generate report and send to "reports" folder
            exporter.capitalCityReport(result, "AllCapitalCitiesInTheWorldLargestToSmallest");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities");
            return null;
        }
    }
    /**
     * Capital city population - Largest to smallest by continent or region
     */

    public ArrayList<City> getCapitalCityPopulation(String option, String input) {
         if (option.isEmpty()) {
            throw new IllegalArgumentException("Option for query must be specified!");
        }

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input must be specified!");
        }

        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query;
            
            if (option.equals("Continent")) {
                query =
                    "SELECT ci.Name, co.Name AS Country, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Continent = '" + input + "' "
                            + "ORDER BY ci.Population DESC; ";
                
            } else if (option.equals("Region")) {
                query = 
                    "SELECT ci.Name, co.Name AS Country, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Region = '" + input + "' "
                            + "ORDER BY ci.Population DESC; ";
            } else {
                throw new IllegalArgumentException("Invalid option specified!");
            }

            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<City> result = addCapitalCities(resultSet);
            // Generate report and send to "reports" folder
            exporter.capitalCityReport(result, "AllCapitalCitiesIn" + input + "LargestToSmallest");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities");
            return null;
        }
    }

    /**
     * Top N populated capital cities by continent or region
     */
    public ArrayList<City> getTopNCapitalCityPopulation(String option, String input, int n) {
        if (n < 1) {
            throw new NullPointerException("N must be greater than 0");
        }
         if (option.isEmpty()) {
            throw new IllegalArgumentException("Option for query must be specified!");
        }

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input must be specified!");
        }
        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query; 
            
            if (option.equals("Continent")) {
                query =
                    "SELECT ci.Name, co.Name AS Country, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Continent = '" + input + "' "
                            + "ORDER BY ci.Population DESC; "
                            + " LIMIT " + n + ";";
                
            } else if (option.equals("Region")) {
                query =
                    "SELECT ci.Name, co.Name AS Country, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Region = '" + input + "' "
                            + "ORDER BY ci.Population DESC; "
                            + " LIMIT " + n + ";";
            } else {
                throw new IllegalArgumentException("Invalid option specified!");
            }


            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<City> result = addCapitalCities(resultSet);
            // Generate report and send to "reports" folder
            exporter.capitalCityReport(result, "GetTop" + n + "CapitalCitiesPopulationIn" + input);
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities");
            return null;
        }
    }
     /**
     * Top N populated capital cities
     */
    public ArrayList<City> getTopNCapitalCityPopulation(int n) {
        if (n < 1) {
            throw new NullPointerException("N must be greater than 0");
        }
        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query =
                    "SELECT ci.Name, co.Name AS Country, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "ORDER BY ci.Population DESC; "
                            + " LIMIT " + n + ";";


            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<City> result = addCapitalCities(resultSet);
            // Generate report and send to "reports" folder
            exporter.capitalCityReport(result, "GetTop" + n + "CapitalCitiesPopulationInTheWorld");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities");
            return null;
        }
    }   
    
    
    /**
     * Population in and out of cities
     */

    /**
     * Outputs population in and out of all cities in a continent/region/country
     * @param option - specifies whether continent/region/country
     * @return
     */
    public ArrayList<Country> getPopulationInAndOutOfCities(String option) {
        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query;

            if (option.equals("Continent")) {
                query =
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

            } else if (option.equals("Region")) {
                query =
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

            } else if (option.equals("Country")) {
                query =
                        "SELECT co.Name AS Name, SUM(co.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "round(((SUM(ci.Population)/SUM(co.Population))*100),2) AS In_City_Perc, " +
                                "SUM(co.Population) - SUM(ci.Population) AS Out_city, " +
                                "round((((SUM(co.Population) - SUM(ci.Population))/SUM(co.Population))*100),2) AS Out_City_Perc " +
                                "FROM country co " +
                                "JOIN (SELECT ci.countrycode, SUM(ci.population) AS Population " +
                                "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code " +
                                "GROUP BY Name";
            } else {
                throw new IllegalArgumentException("Invalid option specified!");
            }
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<Country> result = addPopulations(resultSet, option);
            // Generate report and send to "reports" folder
            exporter.populationInAndOutReport(result, option, "AllCityPopulationsInAndOutEach" + option);
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city populations");
            return null;
        }
    }

    /**
     * Total population
     */
    public Country getPopulationOf() {
        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query =
                    "SELECT SUM(population) AS Population "
                            + "FROM country co ;";
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            Country result = addPopulation(resultSet, "World");
            if (result == null) {
                throw new RuntimeException("Country data is null!");
            }
            // Generate report and send to "reports" folder
            exporter.populationReport(result, "PopulationOfTheWorld");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population data!");
            return null;
        }
    }

    public Country getPopulationOf(String option, String area) {

        if (option.isEmpty()) {
            throw new IllegalArgumentException("Option must be specified!");
        }

        if (area.isEmpty()) {
            throw new IllegalArgumentException("Area must be specified!");
        }

        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query;

            if (option.equals("Continent")) {
                query =
                        "SELECT co.Continent, SUM(population) AS Population "
                                + "FROM country co "
                                + "WHERE co.continent = '" + area + "';";

            } else if (option.equals("Country")) {
                query =
                        "SELECT Population "
                                + "FROM country co "
                                + "WHERE co.Name = '" + area + "';";
            } else {
                throw new IllegalArgumentException("Invalid option specified!");
            }
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            Country result = addPopulation(resultSet, option);
            // Generate report and send to "reports" folder
            exporter.populationReport(result, "PopulationOf" + area);
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population data!");
            return null;
        }
    }

    public Country getPopulationOf(String option, String area, String subArea) {

        if (option.isEmpty()) {
            throw new IllegalArgumentException("Option must be specified!");
        }

        if (area.isEmpty()) {
            throw new IllegalArgumentException("Area must be specified!");
        }

        if (subArea.isEmpty()) {
            throw new IllegalArgumentException("Sub-area must be specified!");
        }

        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query;

            if (option.equals("Region")) {
                query =
                        "SELECT co.Region, Population "
                                + "FROM country co "
                                + "WHERE co.continent = '" + area + "' AND co.Region = '" + subArea + "' ;";

            } else if (option.equals("District")) {
                query =
                        "SELECT SUM(ci.Population) AS Population "
                                + "FROM country co "
                                + "LEFT JOIN city ci ON co.Capital = ci.ID "
                                + "WHERE co.Name = '" + area + "' AND ci.District = '" + subArea +"';";

            } else if (option.equals("City")) {
                query =
                        "SELECT SUM(ci.Population) AS Population "
                                + "FROM country co "
                                + "LEFT JOIN city ci ON co.Capital = ci.ID "
                                + "WHERE ci.District = '" + area + "' AND ci.Name = '" + subArea +"';";
            } else {
                throw new IllegalArgumentException("Invalid option specified!");
            }
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            Country result = addPopulation(resultSet, option);
            // Generate report and send to "reports" folder
            exporter.populationReport(result, "PopulationOf" + subArea);
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population data!");
            return null;
        }
    }

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

    private ArrayList<City> addCapitalCities(ResultSet resultSet) throws SQLException {
        ArrayList<City> capitalCities = new ArrayList<>();

        while (resultSet.next()) {
            City capitalCity = new City();
            capitalCity.Name = resultSet.getString("NAME");
            capitalCity.Country = resultSet.getString("Country");
            capitalCity.Population = resultSet.getInt("Population");
            capitalCities.add(capitalCity);
        }
        return capitalCities;
    }

    private ArrayList<Country> addPopulations(ResultSet resultSet, String type) throws SQLException {
        ArrayList<Country> populations = new ArrayList<>();
        while (resultSet.next()) {
            Country country = new Country();
            if (type.equals("Continent")) {
                country.Continent = resultSet.getString("Continent");
            } else if (type.equals("Region")) {
                country.Region = resultSet.getString("Region");
            } else if (type.equals("Country")) {
                country.Name = resultSet.getString("NAME");
            } else {
                country.Name = null;
            }
            country.Population = resultSet.getLong("Total_Pop");
            country.InCityPop = resultSet.getLong("In_city");
            country.InCityPerc = resultSet.getLong("In_City_Perc");
            country.OutCityPop = resultSet.getLong("Out_city");
            country.OutCityPerc = resultSet.getLong("Out_City_Perc");
            populations.add(country);
        }
        return populations;
    }

    private Country addPopulation(ResultSet resultSet, String name) throws SQLException {
        if (resultSet.next()) {
            Country country = new Country();
            country.Name = name;
            country.Population = resultSet.getLong("Population");
            return country;
        }
        return null;
    }
}
