package com.napier.application.logic;

import com.napier.application.data.City;
import com.napier.application.data.Country;
import com.napier.application.data.Language;

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
     * Outputs population of top n countries in the world
     * @param n - number of countries to filter by
     * @return
     */
    public ArrayList<Country> getTopNCountryPopulation(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String query =
                    "SELECT co.Code, ci.Name, co.Continent, co.Region, ci.Population, ci.Name AS CapitalCity "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "ORDER BY ci.Population DESC "
                            + "LIMIT " + n;

            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            // Return query result if query is successful
            ArrayList<Country> result = addCountries(resultSet);
            // Generate report and send to "reports" folder
            exporter.countryReport(result, "Top" + n + "CountriesInTheWorldLargestToSmallest");

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query");
            return null;
        }
    }

    /**
     * Outputs population of top n countries in a continent/region
     * @param n - number of countries to filter by
     * @param queryType - specifies continent or region
     * @param name - name of continent/region
     * @return
     */
    public ArrayList<Country> getTopNCountryPopulation(int n, String queryType, String name) {

        if (n < 1) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        if (queryType == null || name == null) {
            throw new IllegalArgumentException("queryType or name must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            ResultSet rset;

            if (queryType.equals("Continent")) {

                // Create string for SQL statement
                String query =
                        "SELECT co.Code, ci.Name, co.Continent, co.Region, ci.Population, ci.Name AS CapitalCity "
                                + "FROM country co "
                                + "LEFT JOIN city ci ON co.Capital = ci.ID "
                                + "WHERE continent = '" + name + "'"
                                + "ORDER BY ci.Population DESC "
                                + "LIMIT " + n;

                // Get result set of the SQL query
                resultSet = getResultSet(statement, query);
                // Return query result if query is successful
                ArrayList<Country> result = addCountries(resultSet);
                // Generate report and send to "reports" folder
                exporter.countryReport(result, "Top" + n + "CountriesIn" + name + "LargestToSmallest");
                return result;

            } else if (queryType.equals("Region")) {
                // Create string for SQL statement
                String query =
                        "SELECT co.Code, ci.Name, co.Continent, co.Region, ci.Population, ci.Name AS CapitalCity "
                                + "FROM country co "
                                + "LEFT JOIN city ci ON co.Capital = ci.ID "
                                + "WHERE region = '" + name + "'"
                                + "ORDER BY ci.Population DESC "
                                + "LIMIT " + n;

                // Get result set of the SQL query
                resultSet = getResultSet(statement, query);
                // Return query result if query is successful
                ArrayList<Country> result = addCountries(resultSet);
                // Generate report and send to "reports" folder
                exporter.countryReport(result, "Top" + n + "CountriesIn" + name + "LargestToSmallest");
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query");
            return null;
        }
    }

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
     * Outputs population of all cities in a continent/region/country/district - largest to smallest
     * @param name - name of continent/region/country/district
     * @param queryType - specifies continent/region/country/district
     * @return an ArrayList of Cities
     * Fix of bug returning incorrect output in unit test
     */
    public ArrayList<City> getCityPopulation(String queryType, String name) {

        if (queryType == null || name == null) {
            throw new IllegalArgumentException("queryType or name must be specified");
        }

        try {

            if (queryType.equals("Continent")) {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query =
                    "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                            + "FROM city ci "
                            + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                            + "WHERE co.continent = '" + name + "'"
                            + "ORDER BY ci.Population DESC ";

            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<City> result = addCities(resultSet);
            // Generate report and send to "reports" folder
            exporter.cityReport(result, "AllCitiesIn" + name + "LargestToSmallest");
            return result;

            } else if (queryType.equals("Region")) {
                // Create a new SQL statement
                statement = connection.createStatement();
                // Create string for SQL query
                String query =
                        "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE co.Region = '" + name + "'"
                                + "ORDER BY ci.Population DESC ";

                // Get result set of the SQL query
                resultSet = getResultSet(statement, query);
                ArrayList<City> result = addCities(resultSet);
                // Generate report and send to "reports" folder
                exporter.cityReport(result, "AllCitiesIn" + name + "LargestToSmallest");
                return result;

            } else if (queryType.equals("Country")) {
                // Create a new SQL statement
                statement = connection.createStatement();
                // Create string for SQL query
                String query =
                        "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE co.Name = '" + name + "'"
                                + "ORDER BY ci.Population DESC ";

                // Get result set of the SQL query
                resultSet = getResultSet(statement, query);
                ArrayList<City> result = addCities(resultSet);
                // Generate report and send to "reports" folder
                exporter.cityReport(result, "AllCitiesIn" + name + "LargestToSmallest");
                return result;

            } else if (queryType.equals("District")) {
                // Create a new SQL statement
                statement = connection.createStatement();
                // Create string for SQL query
                String query =
                        "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE ci.District = '" + name + "'"
                                + "ORDER BY ci.Population DESC ";

                // Get result set of the SQL query
                resultSet = getResultSet(statement, query);
                ArrayList<City> result = addCities(resultSet);
                // Generate report and send to "reports" folder
                exporter.cityReport(result, "AllCitiesIn" + name +"LargestToSmallest");
                return result;

            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute getCityPopulation");
            return null;
        }
    }


    /**
     * Outputs population of top n cities in the world
     * @param n - number of cities to filter by
     * @return ArrayList of City
     */
    public ArrayList<City> getTopNCityPopulation(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query =
                    "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                            + "FROM city ci "
                            + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                            + "ORDER BY ci.Population DESC "
                            + " LIMIT " + n;
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<City> result = addCities(resultSet);
            // Generate report and send to "reports" folder
            exporter.cityReport(result, "Top"+n+"CitiesInTheWorldLargestToSmallest");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities");
            return null;
        }
    }

    /**
     * Outputs population of top n cities in a continent/region/country/district - largest to smallest
     * @param queryType - specifies continent/region/country/district
     * @param name - name of continent/region/country/district
     * @param n - number of countries to filter by
     * @return ArrayList of City
     */
    public ArrayList<City> getTopNCityPopulation(int n, String queryType, String name) {

        if (queryType == null || name == null) {
            throw new IllegalArgumentException("queryType or name must be specified");
        }

        if (n < 1) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        try {

            if (queryType.equals("Continent")) {
                // Create a new SQL statement
                statement = connection.createStatement();
                // Create string for SQL query
                String query =
                        "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE co.continent = '" + name + "'"
                                + "ORDER BY ci.Population DESC "
                                + "LIMIT " + n;

                // Get result set of the SQL query
                resultSet = getResultSet(statement, query);
                ArrayList<City> result = addCities(resultSet);
                // Generate report and send to "reports" folder
                exporter.cityReport(result, "Top" + n + " CitiesIn" + name + "LargestToSmallest");
                return result;

            } else if (queryType.equals("Region")) {
                // Create a new SQL statement
                statement = connection.createStatement();
                // Create string for SQL query
                String query =
                        "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE co.Region = '" + name + "'"
                                + "ORDER BY ci.Population DESC "
                                + "LIMIT " + n;

                // Get result set of the SQL query
                resultSet = getResultSet(statement, query);
                ArrayList<City> result = addCities(resultSet);
                // Generate report and send to "reports" folder
                exporter.cityReport(result, "Top" + n + " CitiesIn" + name + "LargestToSmallest");
                return result;

            } else if (queryType.equals("Country")) {
                // Create a new SQL statement
                statement = connection.createStatement();
                // Create string for SQL query
                String query =
                        "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE co.Name = '" + name + "'"
                                + "ORDER BY ci.Population DESC "
                                + "LIMIT " + n;

                // Get result set of the SQL query
                resultSet = getResultSet(statement, query);
                ArrayList<City> result = addCities(resultSet);
                // Generate report and send to "reports" folder
                exporter.cityReport(result, "Top" + n + " CitiesIn" + name + "LargestToSmallest");
                return result;

            } else if (queryType.equals("District")) {
                // Create a new SQL statement
                statement = connection.createStatement();
                // Create string for SQL query
                String query =
                        "SELECT ci.Name, co.Name AS Country, ci.District, ci.Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE ci.District = '" + name + "'"
                                + "ORDER BY ci.Population DESC "
                                + "LIMIT " + n;

                // Get result set of the SQL query
                resultSet = getResultSet(statement, query);
                ArrayList<City> result = addCities(resultSet);
                // Generate report and send to "reports" folder
                exporter.cityReport(result, "Top" + n + " CitiesIn" + name + "LargestToSmallest");
                return result;

            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute getCityPopulation");
            return null;
        }
    }


    /**
     * Outputs population of all capital cities in the world - largest to smallest
     * @return ArrayList of City
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
     * Outputs population of all capital cities in a continent/region - largest to smallest
     * @param option - specifies continent/region
     * @param input - specifies name of continent/region
     * @return ArrayList of City
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
     * Outputs population of top n capital cities in the world
     * @param n - number of capital cities to filter by
     * @return ArrayList of City
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
                            + "ORDER BY ci.Population DESC "
                            + "LIMIT " + n + ";";


            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<City> result = addCapitalCities(resultSet);
            // Generate report and send to "reports" folder
            exporter.capitalCityReport(result, "Top" + n + "CapitalCitiesPopulationInTheWorld");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities");
            return null;
        }
    }

    /**
     * Outputs population of top n capital cities in a continent/region
     * @param n - number of capital cities to filter by
     * @param option - specifies continent/region
     * @param input - specifies name of continent/region
     * @return ArrayList of City
     */
    public ArrayList<City> getTopNCapitalCityPopulation(int n, String option, String input) {
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
                                + "ORDER BY ci.Population DESC "
                                + "LIMIT " + n + ";";

            } else if (option.equals("Region")) {
                query =
                        "SELECT ci.Name, co.Name AS Country, ci.Population "
                                + "FROM country co "
                                + "LEFT JOIN city ci ON co.Capital = ci.ID "
                                + "WHERE co.Region = '" + input + "' "
                                + "ORDER BY ci.Population DESC "
                                + "LIMIT " + n + ";";
            } else {
                throw new IllegalArgumentException("Invalid option specified!");
            }


            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<City> result = addCapitalCities(resultSet);
            // Generate report and send to "reports" folder
            exporter.capitalCityReport(result, "Top" + n + "CapitalCitiesPopulationIn" + input);
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
     * @param option - specifies continent/region/country
     * @return ArrayList of Country
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

    /**
     * Outputs total population of the world
     * @return ArrayList of Country
     */
    public ArrayList<Country> getPopulationOf() {
        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query =
                    "SELECT SUM(co.Population) AS Total_Pop, " +
                            "SUM(ci.Population) AS In_city, " +
                            "round(((SUM(ci.Population)/SUM(co.Population))*100),2) AS In_City_Perc, " +
                            "SUM(co.Population) - SUM(ci.Population) AS Out_city, " +
                            "round((((SUM(co.Population) - SUM(ci.Population))/SUM(co.Population))*100),2) AS Out_City_Perc " +
                            "FROM country co " +
                            "JOIN (SELECT ci.countrycode, SUM(ci.population) AS Population " +
                            "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code;";

            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<Country> result = addPopulations(resultSet, "World");
            // Generate report and send to "reports" folder
            exporter.populationInAndOutReport(result, "World", "PopulationOfTheWorld");
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population data!");
            return null;
        }
    }

    /**
     * Outputs total population of a continent/country
     * @param option - specifies continent/country
     * @param area - specifies name of area
     * @return Country
     */
    public ArrayList<Country> getPopulationOf(String option, String area) {

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
                        "SELECT co.Continent AS Continent, SUM(co.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "round(((SUM(ci.Population)/SUM(co.Population))*100),2) AS In_City_Perc, " +
                                "SUM(co.Population) - SUM(ci.Population) AS Out_city, " +
                                "round((((SUM(co.Population) - SUM(ci.Population))/SUM(co.Population))*100),2) AS Out_City_Perc " +
                                "FROM country co " +
                                "JOIN (SELECT ci.countrycode, SUM(ci.population) AS Population " +
                                "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code " +
                                "WHERE co.Continent = '" + area + "';";


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
                                "WHERE co.Name = '" + area + "';";
            } else {
                throw new IllegalArgumentException("Invalid option specified!");
            }
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<Country> result = addPopulations(resultSet, option);
            // Generate report and send to "reports" folder
            exporter.populationInAndOutReport(result, option, "PopulationOf" + area);
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population data!");
            return null;
        }
    }

    /**
     * Outputs total population of a continent/country
     * @param option - specifies region/district/city
     * @param area - specifies name of area
     * @param subArea - specifies name of sub-area
     * @return Country
     */
    public ArrayList<Country> getPopulationOf(String option, String area, String subArea) {

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
                        "SELECT co.Region AS Region, SUM(co.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "round(((SUM(ci.Population)/SUM(co.Population))*100),2) AS In_City_Perc, " +
                                "SUM(co.Population) - SUM(ci.Population) AS Out_city, " +
                                "round((((SUM(co.Population) - SUM(ci.Population))/SUM(co.Population))*100),2) AS Out_City_Perc " +
                                "FROM country co " +
                                "JOIN (SELECT ci.countrycode, SUM(ci.population) AS Population " +
                                "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code " +
                                "WHERE co.continent = '" + area + "' AND co.Region = '" + subArea + "';";

            } else if (option.equals("District")) {
                query =
                        "SELECT ci.District, SUM(ci.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "round(((SUM(ci.Population)/SUM(co.Population))*100),2) AS In_City_Perc, " +
                                "SUM(co.Population) - SUM(ci.Population) AS Out_city, " +
                                "round((((SUM(co.Population) - SUM(ci.Population))/SUM(co.Population))*100),2) AS Out_City_Perc " +
                                "FROM country co " +
                                "JOIN (SELECT ci.countrycode, ci.District, SUM(ci.population) AS Population " +
                                "FROM city ci WHERE ci.District = '" + subArea + "' GROUP BY 1,2) ci ON ci.countrycode = co.code " +
                                "WHERE co.Name = '" + area + "' AND ci.District = '" + subArea + "'" +
                                "GROUP BY ci.District;";

            } else if (option.equals("City")) {
                query =
                        "SELECT ci.Name, SUM(ci.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "round(((SUM(ci.Population)/SUM(co.Population))*100),2) AS In_City_Perc, " +
                                "SUM(co.Population) - SUM(ci.Population) AS Out_city, " +
                                "round((((SUM(co.Population) - SUM(ci.Population))/SUM(co.Population))*100),2) AS Out_City_Perc " +
                                "FROM country co " +
                                "JOIN (SELECT ci.countrycode, ci.Name, ci.District, SUM(ci.population) AS Population " +
                                "FROM city ci WHERE ci.Name = '" + subArea + "'GROUP BY 1,2,3) ci ON ci.countrycode = co.code " +
                                "WHERE ci.District = '" + area + "' AND ci.Name = '" + subArea + "'" +
                                "GROUP BY ci.Name;";

            } else {
                throw new IllegalArgumentException("Invalid option specified!");
            }
            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<Country> result = addPopulations(resultSet, option);
            // Generate report and send to "reports" folder
            exporter.populationInAndOutReport(result, option, "PopulationOf" + subArea);
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
    public ArrayList<Language> getLanguagePercentage() {
        try {
            // Create a new SQL statement
            statement = connection.createStatement();
            // Create string for SQL query
            String query =
                    "SELECT A.Language, ROUND(SUM((A.Percentage/100) * (B.Population/1000000)),2) AS TotalSpeakers, " +
                            "ROUND((SUM((A.Percentage/100) * (B.Population/1000000))/(SELECT SUM(Population/1000000) from country))*100,2) AS PercentOfWorldPop "
                            + "FROM countrylanguage A "
                            + "LEFT JOIN country B ON A.CountryCode = B.Code "
                            + "WHERE A.Language IN('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') "
                            + "GROUP BY A.Language "
                            + "ORDER BY 2 DESC;";

            // Get result set of the SQL query
            resultSet = getResultSet(statement, query);
            ArrayList<Language> result = addLanguages(resultSet);
            // Generate report and send to "reports" folder
            exporter.languageReport(result, "PercentageOfLanguagesSpoken");
            return result;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Helper methods
     */

    /**
     * Gets result set of an SQL query
     * @param statement - Statement object provided by connection
     * @param query - String of query provided from query method
     * @return ResultSet
     * @throws SQLException
     */
    private ResultSet getResultSet(Statement statement, String query) throws SQLException {
        return statement.executeQuery(query);
    }

    /**
     * Adds countries to an ArrayList to use as output
     * @param resultSet - provided by query method which includes the results from SQL query
     * @return ArrayList of Country
     * @throws SQLException
     */
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

    /**
     * Adds cities to an ArrayList to use as output
     * @param resultSet - provided by query method which includes the results from SQL query
     * @return ArrayList of City
     * @throws SQLException
     */
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

    /**
     * Adds capital cities to an ArrayList to use as output
     * @param resultSet - provided by query method which includes the results from SQL query
     * @return ArrayList of City
     * @throws SQLException
     */
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

    /**
     * Adds populations to an ArrayList to use as output
     * @param resultSet - provided by query method which includes the results from SQL query
     * @param type - specifies continent/region/country
     * @return ArrayList of Country
     * @throws SQLException
     */
    private ArrayList<Country> addPopulations(ResultSet resultSet, String type) throws SQLException {
        ArrayList<Country> populations = new ArrayList<>();

        while (resultSet.next()) {
            Country country = new Country();
            if (type.equals("World")) {
                country.Name = "World";
            } else if (type.equals("Continent")) {
                country.Continent = resultSet.getString("Continent");
            } else if (type.equals("Region")) {
                country.Region = resultSet.getString("Region");
            } else if (type.equals("Country")) {
                country.Name = resultSet.getString("Name");
            } else if (type.equals("District")) {
                country.District = resultSet.getString("ci.District");
            } else if (type.equals("City")) {
                country.Name = resultSet.getString("ci.Name");
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

    /**
     * Outputs languages to an ArrayList to use as an output
     * @param resultSet - provided by query method which includes the results from SQL query
     * @return ArrayList of Language
     * @throws SQLException
     */
    private ArrayList<Language> addLanguages(ResultSet resultSet) throws SQLException {
        ArrayList<Language> languages = new ArrayList<>();
        while (resultSet.next()) {
            Language language = new Language();
            language.Language = resultSet.getString("Language");
            language.TotalSpeakers = resultSet.getDouble("TotalSpeakers");
            language.PercentOfWorldPop = resultSet.getDouble("PercentOfWorldPop");
            languages.add(language);
        }
        return languages;
    }
}
