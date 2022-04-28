package com.napier.application.logic;

import com.napier.application.data.City;
import com.napier.application.data.Country;
import com.napier.application.data.Language;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Produces queries from the database
 */
public class Query {

    private Reporter reporter = new Reporter();
    private Connection connection;

    public Query(Connection connection) {
        this.connection = connection;
    }

    /**
     * Population Largest to Smallest
     */

    /**
     * Gets list of countries by their population count
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getCountriesByPopulation() {

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population "
                            + "FROM country c "
                            + "ORDER BY 2 DESC ";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                countries.add(cou);
            }
            //Generates a report for the countries by population for all countries in the world, saved as a markdown file
            reporter.outputCountries(countries, "World", "AllCountryPopulation.md");
            return countries;
        }
        catch (Exception e) {
            //If an error no information is found an error message is displayed
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    /**
     * Gets a list of countries by population filtered by continent
     * @param Continent
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getCountriesByPopulation(String Continent) {

        if (Continent == null) {
            throw new IllegalArgumentException("Continent must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population, Continent "
                            + "FROM country c "
                            + "WHERE continent = '" + Continent + "' "
                            + " ORDER BY 2 DESC";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                cou.Continent = rset.getString("Continent");
                countries.add(cou);
            }
            //Generates a report for the countries by population for all countries in a continent, saved as a markdown file
            reporter.outputCountries(countries, "Continent", "AllCountryPopulationIn" + reporter.concatString(Continent) + ".md");
            return countries;
        }
        catch (Exception e) {
            //If an error no information is found an error message is displayed
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    /**
     * Gets a list of countries by population filtered by region
     * @param Continent
     * @param Region
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getCountriesByPopulation(String Continent, String Region) {

        if (Continent == null || Region == null) {
            throw new IllegalArgumentException("Continent or Region must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population, Continent, Region "
                            + "FROM country c "
                            + "WHERE continent = '" + Continent + "' AND Region = '" + Region + "' "
                            + " ORDER BY 2 DESC ";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                cou.Continent = rset.getString("Continent");
                cou.Region = rset.getString("Region");
                countries.add(cou);
            }
            //Generates a report for the countries by population for all countries in a region, saved as a markdown file
            reporter.outputCountries(countries, "Region", "AllCountryPopulationIn" + reporter.concatString(Region) + ".md");
            return countries;
        }
        catch (Exception e) {
            //If an error no information is found an error message is displayed
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    /**
     * Top N Populated Countries
     */

    /**
     * Gets the top N of countries based on population
     * @param n
     * @return an ArrayList of Countries
     */
    public ArrayList<Country> getTopNCountryPopulation(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population "
                            + "FROM country c "
                            + " ORDER BY population DESC"
                            + " LIMIT " + n;

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next()) {
                Country country = new Country();
                country.Name = rset.getString("NAME");
                country.Population = rset.getInt("Population");
                countries.add(country);
            }
            reporter.outputCountries(countries, "World", "Top" + n + "MostPopulatedCountriesInTheWorld.md");
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query");
            return null;
        }
    }

    /**
     * Gets the top N of continent/region based on population
     * @param n
     * @param name
     * @param queryType
     * @return an ArrayList of Countries
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
                String strSelect =
                        "SELECT NAME, continent, population "
                                + "FROM country c "
                                + "WHERE continent = '" + name + "'"
                                + " ORDER BY population DESC"
                                + " LIMIT " + n;

                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Name = rset.getString("NAME");
                    country.Continent = rset.getString("Continent");
                    country.Population = rset.getInt("Population");
                    countries.add(country);
                }
                reporter.outputNameOnly(countries, "Top" + n + "MostPopulatedCountriesIn" + reporter.concatString(name) + ".md");
                return countries;

            } else if (queryType.equals("Region")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT NAME, region, population "
                                + "FROM country c "
                                + "WHERE region = '" + name + "'"
                                + " ORDER BY population DESC"
                                + " LIMIT " + n;
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next())
                {
                    Country country = new Country();
                    country.Name = rset.getString("NAME");
                    country.Region = rset.getString("Region");
                    country.Population = rset.getInt("Population");
                    countries.add(country);
                }
                reporter.outputNameOnly(countries, "Top" + n + "MostPopulatedCountriesIn" + reporter.concatString(name) + ".md");
                return countries;
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
     * City Population from Largest to Smallest
     */

    /**
     * Orders cities based on population
     * @return an ArrayList of Cities
     */
    public ArrayList<City> getCitiesByPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population "
                            + "FROM city a "
                            + " ORDER BY population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City city = new City();
                city.Name = rset.getString("NAME");
                city.Population = rset.getInt("Population");
                cities.add(city);
            }
            reporter.outputCities(cities, "World", "MostPopularCitiesInTheWorldLargeToSmall.md");
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute largeToSmallCityPopulation");
            return null;
        }
    }

    /**
     * Orders the cities in a continent/region/country/district based on population
     * @param name
     * @param queryType
     * @return an ArrayList of Cities
     * Fix of bug returning incorrect output in unit test
     */
    public ArrayList<City> getCitiesByPopulation(String queryType, String name) {

        if (queryType == null || name == null) {
            throw new IllegalArgumentException("queryType or name must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            ResultSet rset;

            if (queryType.equals("Continent")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT c.NAME, co.continent, c.population "
                                + "FROM city c "
                                + "LEFT JOIN country co ON c.CountryCode = co.Code "
                                + "WHERE co.continent = '" + name + "'"
                                + " ORDER BY population DESC";

                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next()) {
                    City city = new City();
                    city.Name = rset.getString("NAME");
                    city.Continent = rset.getString("Continent");
                    city.Population = rset.getInt("Population");
                    cities.add(city);
                }
                System.out.println("Largest to smallest populated cities in " + name);
                printCities(cities, "Continent");
                reporter.outputCities(cities, "Continent", "MostPopularCitiesIn" + reporter.concatString(name) + "LargeToSmall.md");
                return cities;

            } else if (queryType.equals("Region")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT c.NAME, co.Continent, co.Region, c.population "
                                + "FROM city c "
                                + "LEFT JOIN country co ON c.CountryCode = co.Code "
                                + "WHERE co.Region = '" + name + "'"
                                + " ORDER BY population DESC";
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next()) {
                    City city = new City();
                    city.Name = rset.getString("NAME");
                    city.Continent = rset.getString("Continent");
                    city.Region = rset.getString("Region");
                    city.Population = rset.getInt("Population");
                    cities.add(city);
                }
                reporter.outputCities(cities, "Region", "MostPopularCitiesIn" + reporter.concatString(name) + "LargeToSmall.md");
                return cities;

            } else if (queryType.equals("Country")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT c.NAME, co.Continent as Continent, co.Region as Region, co.Name as Country, c.population "
                                + "FROM city c "
                                + "LEFT JOIN country co ON c.CountryCode = co.Code "
                                + "WHERE co.Name = '" + name + "'"
                                + " ORDER BY population DESC";
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next())
                {
                    City city = new City();
                    city.Name = rset.getString("NAME");
                    city.Continent = rset.getString("Continent");
                    city.Region = rset.getString("Region");
                    city.Country = rset.getString("Country");
                    city.Population = rset.getInt("Population");
                    cities.add(city);
                }
                reporter.outputCities(cities, "Country", "MostPopularCitiesIn" + reporter.concatString(name) + "LargeToSmall.md");
                return cities;

            } else if (queryType.equals("District")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT c.NAME, co.Continent as Continent, co.Region as Region, co.Name as Country, c.District, c.population "
                                + "FROM city c "
                                + "LEFT JOIN country co ON c.CountryCode = co.Code "
                                + "WHERE c.District = '" + name + "'"
                                + " ORDER BY population DESC";
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<City> cities = new ArrayList<City>();
                while (rset.next())
                {
                    City city = new City();
                    city.Name = rset.getString("NAME");
                    city.Continent = rset.getString("Continent");
                    city.Region = rset.getString("Region");
                    city.Country = rset.getString("Country");
                    city.District = rset.getString("District");
                    city.Population = rset.getInt("Population");
                    cities.add(city);
                }
                reporter.outputCities(cities, "District", "MostPopularCitiesIn" + reporter.concatString(name) + "LargeToSmall.md");
                return cities;

            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute largeToSmallCityPopulation");
            return null;
        }
    }

    public ArrayList<Country> getCitiesByPopulationInAndOut(String option) {

        if (option == null) {
            throw new IllegalArgumentException("Option for this query must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            ResultSet rset;

            if (option.equals("Continent")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT co.Continent AS Continent, SUM(co.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "SUM(co.Population)  - SUM(ci.Population) AS Out_city " +
                                "FROM country co " +
                                "JOIN (SELECT " +
                                "ci.countrycode " +
                                ",SUM(ci.population) AS Population " +
                                "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code " +
                                "GROUP BY Continent";
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Continent = rset.getString("Continent");
                    country.Population = rset.getLong("Total_Pop");
                    country.InCityPop = rset.getLong("In_city");
                    country.OutCityPop = rset.getLong("Out_city");
                    countries.add(country);
                }
                // Generate report
                reporter.outputCityPopulationInAndOut(countries, "Continent", "PopulationInAndOutOfCitiesInEachContinent.md");
                return countries;

            } else if (option.equals("Region")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT co.Region AS Region, SUM(co.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "SUM(co.Population)  - SUM(ci.Population) AS Out_city " +
                                "FROM country co " +
                                "JOIN (SELECT " +
                                "ci.countrycode " +
                                ",SUM(ci.population) AS Population " +
                                "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code " +
                                "GROUP BY Region";
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Region = rset.getString("Region");
                    country.Population = rset.getLong("Total_Pop");
                    country.InCityPop = rset.getLong("In_city");
                    country.OutCityPop = rset.getLong("Out_city");
                    countries.add(country);
                }
                // Generate report
                reporter.outputCityPopulationInAndOut(countries, "Region", "PopulationInAndOutOfCitiesInEachRegion.md");
                return countries;

            } else if (option.equals("Country")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT co.Name AS Name, SUM(co.Population) AS Total_Pop, " +
                                "SUM(ci.Population) AS In_city, " +
                                "SUM(co.Population)  - SUM(ci.Population) AS Out_city " +
                                "FROM country co " +
                                "JOIN (SELECT " +
                                "ci.countrycode " +
                                ",SUM(ci.population) AS Population " +
                                "FROM city ci GROUP BY 1) ci ON ci.countrycode = co.code " +
                                "GROUP BY Name";
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Name = rset.getString("Name");
                    country.Population = rset.getLong("Total_Pop");
                    country.InCityPop = rset.getLong("In_city");
                    country.OutCityPop = rset.getLong("Out_city");
                    countries.add(country);
                }
                // Generate report
                reporter.outputCityPopulationInAndOut(countries, "Country", "PopulationInAndOutOfCitiesInEachCountry.md");
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

    /**
     * Top N City Population
     */

    public ArrayList<City> getTopNCityPopulation(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        try {
            // Create an SQL statement
            Statement statement = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population "
                            + "FROM city c "
                            + " ORDER BY population DESC"
                            + " LIMIT " + n;

            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (resultSet.next()) {
                City city = new City();
                city.Name = resultSet.getString("NAME");
                city.Population = resultSet.getInt("population");
                cities.add(city);
            }
            // Generate report
            reporter.outputCityPopulation(cities, "Top" + n + "MostPopulatedCitiesInTheWorld.md");
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query");
            return null;
        }
    }

    public ArrayList<City> getTopNCityPopulation(int n, String option, String name) {

        if (n < 1) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        if (option == null) {
            throw new IllegalArgumentException("Option must be specified for this query");
        }

        try {
            // Create an SQL statement
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            if (option.equals("Continent")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT ci.Name AS Name, co.Continent AS Continent, ci.Population AS Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE Continent = '" + name + "' "
                                + "ORDER BY Population DESC "
                                + " LIMIT " + n + ";";

                // Execute SQL statement
                resultSet = getResultSet(statement, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<City> cities = new ArrayList<City>();
                while (resultSet.next()) {
                    City city = new City();
                    city.Name = resultSet.getString("NAME");
                    city.Continent = resultSet.getString("Continent");
                    city.Population = resultSet.getInt("population");
                    cities.add(city);
                }
                // Generate report
                reporter.outputCities(cities, "Continent", "Top" + n + "MostPopulatedCitiesIn" + reporter.concatString(name) + ".md");
                return cities;

            } else if (option.equals("Region")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT ci.Name AS Name, co.Continent AS Continent, co.Region AS Region, ci.Population AS Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE Region = '" + name + "' "
                                + "ORDER BY Population DESC "
                                + " LIMIT " + n + ";";

                // Execute SQL statement
                resultSet = getResultSet(statement, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<City> cities = new ArrayList<City>();
                while (resultSet.next()) {
                    City city = new City();
                    city.Name = resultSet.getString("NAME");
                    city.Continent = resultSet.getString("Continent");
                    city.Region = resultSet.getString("Region");
                    city.Population = resultSet.getInt("population");
                    cities.add(city);
                }
                // Generate report
                reporter.outputCities(cities, "Region", "Top" + n + "MostPopulatedCitiesIn" + reporter.concatString(name) + ".md");
                return cities;

            } else if (option.equals("Country")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT ci.Name AS Name, co.Continent AS Continent, co.Region AS Region, co.Name AS Country, ci.Population AS Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE co.Name = '" + name + "' "
                                + "ORDER BY Population DESC "
                                + " LIMIT " + n + ";";

                // Execute SQL statement
                resultSet = getResultSet(statement, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<City> cities = new ArrayList<City>();
                while (resultSet.next()) {
                    City city = new City();
                    city.Name = resultSet.getString("NAME");
                    city.Continent = resultSet.getString("Continent");
                    city.Region = resultSet.getString("Region");
                    city.Country = resultSet.getString("Country");
                    city.Population = resultSet.getInt("population");
                    cities.add(city);
                }
                // Generate report
                reporter.outputCities(cities, "Country", "Top" + n + "MostPopulatedCitiesIn" + reporter.concatString(name) + ".md");
                return cities;

            } else if (option.equals("District")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT ci.Name AS Name, co.Continent AS Continent, co.Region AS Region, co.Name AS Country, ci.District AS District, ci.Population AS Population "
                                + "FROM city ci "
                                + "LEFT JOIN country co ON ci.CountryCode = co.Code "
                                + "WHERE ci.District = '" + name + "' "
                                + "ORDER BY Population DESC "
                                + " LIMIT " + n + ";";

                // Execute SQL statement
                resultSet = getResultSet(statement, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<City> cities = new ArrayList<City>();
                while (resultSet.next()) {
                    City city = new City();
                    city.Name = resultSet.getString("NAME");
                    city.Continent = resultSet.getString("Continent");
                    city.Region = resultSet.getString("Region");
                    city.Country = resultSet.getString("Country");
                    city.District = resultSet.getString("District");
                    city.Population = resultSet.getInt("population");
                    cities.add(city);
                }
                // Generate report
                reporter.outputCities(cities, "District", "Top" + n + "MostPopulatedCitiesIn" + reporter.concatString(name) + ".md");
                return cities;

            } else {
                throw new IllegalArgumentException("Option specified is invalid");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query");
            return null;
        }
    }

    /**
     * Gets the total population of a continent/region/country
     * @param queryType
     */
    public void getPopulation(String queryType) {

        if (queryType == null) {
            throw new IllegalArgumentException("queryType must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            ResultSet rset;

            if (queryType.equals("Continent")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT continent, SUM(population) AS Population " +
                                "FROM country c " +
                                "GROUP BY 1 " +
                                "ORDER BY Population DESC";
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Continent = rset.getString("Continent");
                    country.Population = rset.getLong("Population");
                    countries.add(country);
                }
                // Generate report
                reporter.outputPopulation(countries, "Continent", "TotalPopulationInAllContinents.md");

            } else if (queryType.equals("Region")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT region, SUM(population) AS Population " +
                                "FROM country c " +
                                "GROUP BY 1 " +
                                "ORDER BY Population DESC";
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Region = rset.getString("Region");
                    country.Population = rset.getLong("Population");
                    countries.add(country);
                }
                // Generate report
                reporter.outputPopulation(countries, "Region", "TotalPopulationInAllRegions.md");

            } else if (queryType.equals("Country")) {
                // Create string for SQL statement
                String strSelect =
                        "SELECT NAME, SUM(population) AS Population " +
                                "FROM country c " +
                                "GROUP BY 1 " +
                                "ORDER BY Population DESC";
                // Execute SQL statement
                rset = getResultSet(stmt, strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next()) {
                    Country country = new Country();
                    country.Name = rset.getString("NAME");
                    country.Population = rset.getLong("Population");
                    countries.add(country);
                }
                // Generate report
                reporter.outputPopulation(countries, "Country", "TotalPopulationInAllCountries.md");

            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population");
        }
    }

    /**
     * Gets list of Capital Cities by their population count
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getCapitalCitiesByPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, ci.Country, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "ORDER BY 2 DESC; ";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> cap_cities = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Country = rste.getString("Country");
                cou.Population = rset.getInt("Population");
                cap_cities.add(cou);
            }
            // Generate report
            reporter.outputCapitalCities(cap_cities, "World", "CapitalCityPopulationInTheWorld.md");
            return cap_cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City details");
            return null;
        }
    }

    /**
     * Gets a list of Capital Cities by population filtered by continent
     * @param Continent
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getCapitalCitiesByPopulation(String Continent) {

        if (Continent == null) {
            throw new IllegalArgumentException("Continent must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, ci.Country, co.Continent, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Continent = '"+ Continent + "' "
                            + "ORDER BY 3 DESC; ";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> cap_cities = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Country = rste.getString("Country");
                cou.Continent = rset.getString("Continent");
                cou.Population = rset.getInt("Population");
                cap_cities.add(cou);
            }
            // Generate report
            reporter.outputCapitalCities(cap_cities, "Continent", "CapitalCityPopulationIn" + reporter.concatString(Continent) + ".md");
            return cap_cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City  details");
            return null;
        }
    }

    /**
     * Gets a list of Capital Cities by population filtered by region
     * @param Continent
     * @param Region
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getCapitalCitiesByPopulation(String Continent, String Region) {

        if (Continent == null || Region == null) {
            throw new IllegalArgumentException("Continent or Region must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, ci.Country, co.Continent, co.Region, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Continent = '"+ Continent + "' AND co.Region = '" + Region + "' "
                            + "ORDER BY 4 DESC; ";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> capitalCities = new ArrayList<Country>();
            while (rset.next()) {
                Country country = new Country();
                country.Name = rset.getString("NAME");
                country.Country = rste.getString("Country");
                country.Population = rset.getInt("Population");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                capitalCities.add(country);
            }
            // Generate report
            reporter.outputCapitalCities(capitalCities, "Region", "CapitalCityPopulationIn" + reporter.concatString(Region) + ".md");
            return capitalCities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City  details");
            return null;
        }
    }

    /**
     * Gets list of top n Capital Cities by their population count
     * Where n is defined by user
     * @param n
     * @return an ArrayList of Country objects
     */

    public ArrayList<Country> getTopNCapitalCityPopulation(int n) {

        if (n < 1) {
            throw new NullPointerException("N must be greater than 0");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, ci.Country, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "ORDER BY 2 DESC "
                            + " LIMIT " + n + ";";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is sucessful
            // Check one is returned
            ArrayList<Country> cap_cities = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Country = rste.getString("Country");
                cou.Population = rset.getInt("Population");
                cap_cities.add(cou);
            }
            // Generate report
            reporter.outputCapitalCities(cap_cities, "World", "Top" + n + "CapitalCitiesInTheWorld.md");
            return cap_cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query topNPopulatedCapitalCities");
            return null;
        }
    }

    /**
     * Gets list of top n Capital Cities by their population count
     * Filtered by continent and where n is defined by user
     * @param Continent
     * @param n
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getTopNCapitalCityPopulation(String Continent, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        if (Continent == null) {
            throw new IllegalArgumentException("Continent must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, ci.Country, co.Continent, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Continent = '"+ Continent + "' "
                            + "ORDER BY 3 DESC "
                            + " LIMIT " + n + ";";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> cap_cities = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Country = rset.getString("Country");
                cou.Population = rset.getInt("Population");
                cou.Continent = rset.getString("Continent");
                cap_cities.add(cou);
            }
            // Generate report
            reporter.outputCapitalCities(cap_cities, "Continent", "Top" + n + "CapitalCitiesIn" + reporter.concatString(Continent) + ".md");
            return cap_cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query topNPopulatedCapitalCities");
            return null;
        }
    }

    /**
     * Gets list of top n Capital Cities by their population count
     * Filtered by region and where n is defined by user
     * @param continent
     * @param region
     * @param n
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getTopNCapitalCityPopulation(String continent, String region, int n) {

        if (n < 1) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        if (continent == null || region == null) {
            throw new IllegalArgumentException("Continent or region must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, ci.Country, co.Continent, co.Region, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Continent = '"+ continent + "' AND co.Region = '" + region + "' "
                            + "ORDER BY 4 DESC "
                            + " LIMIT " + n + ";";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> cap_cities = new ArrayList<Country>();

            while (rset.next()) {
                Country country = new Country();
                country.Name = rset.getString("NAME");
                country.Country = rset.getString("Country");
                country.Population = rset.getInt("Population");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                cap_cities.add(country);
            }
            // Generate report
            reporter.outputCapitalCities(cap_cities, "Region", "Top" + n + "CapitalCitiesIn" + reporter.concatString(region) + ".md");
            return cap_cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query topNPopulatedCapitalCities");
            return null;
        }
    }

    /**
     * Population Queries
     */

    /**
     * Gets the population of the world
     * @return a Country object
     */
    public Country getPopulationOf() {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(population) AS Population "
                            + "FROM country co ;";

            // Execute SQL statement
            ResultSet resultSet = getResultSet(stmt, strSelect);
            Country country;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query getPopulationOfWorld");
            return null;
        }
        return null;
    }

    /**
     * Gets the population of a Continent
     * @return a Country object
     */
    public Country getPopulationOf(String Continent) {

        if (Continent == null) {
            throw new IllegalArgumentException("Continent must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT co.continent, SUM(population) AS Population "
                            + "FROM country co "
                            + "WHERE co.continent = '" + Continent + "';";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.Continent = rset.getString("Continent");
                country.Population = rset.getLong("Population");
                reporter.outputPopulation(country, "Continent", "PopulationOf" + reporter.concatString(Continent) + ".md");
                return country;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query getPopulationOfContinent");
            return null;
        }
    }

    /**
     * Gets the population of a Region
     * @return a Country object
     */
    public Country getPopulationOf(String Continent, String Region) {
        try {

            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT co.Region, Population "
                            + "FROM country co "
                            + "WHERE co.continent = '" + Continent + "' AND co.Region = '" + Region + "' ;";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.Region = rset.getString("Region");
                country.Population = rset.getLong("Population");
                reporter.outputPopulation(country, "Region", "PopulationOf" + reporter.concatString(Region) + ".md");
                return country;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query getPopulationOfRegion");
            return null;
        }
    }

    /**
     * Gets the population of a Country
     * @return a Country object
     */
    public Country getPopulationOf(String Continent, String Region, String aCountry) {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Population "
                            + "FROM country co "
                            + "WHERE co.Name = '" + aCountry + "';";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.Name = aCountry;
                country.Population = rset.getLong("Population");
                reporter.outputPopulation(country, "Country", "PopulationOf" + reporter.concatString(aCountry) + ".md");
                return country;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query getPopulationOfCountry");
            return null;
        }
    }

    /**
     * Gets the population of a District
     * @return a Country object
     */
    public Country getPopulationOf(String Continent, String Region, String aCountry, String District) {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(ci.Population) AS Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Name = '" + aCountry + "' AND ci.District = '" + District +"';";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.Name = District;
                country.Population = rset.getLong("Population");
                reporter.outputPopulation(country, "District", "PopulationOf" + reporter.concatString(District) + ".md");
                return country;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query getPopulationOfDistrict");
            return null;
        }
    }

    /**
     * Gets the population of a City
     * @return a Country object
     */
    public Country getPopulationOf(String Continent, String Region, String aCountry, String District, String City) {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(ci.Population) AS Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE ci.Name = '" + City + "' AND ci.District = '" + District +"';";

            // Execute SQL statement
            ResultSet rset = getResultSet(stmt, strSelect);
            // Return query result if query is successful
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.Name = City;
                country.Population = rset.getLong("Population");
                reporter.outputPopulation(country, "City", "PopulationOf" + reporter.concatString(City) + ".md");
                return country;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query getPopulationOfCity");
            return null;
        }
    }

    /**
     * Helper Functions
     */

    /**
     * Prints a list of Capital Cities
     * @param cap_cities
     * @param type
     */
    public static void printCapitalCities(ArrayList<Country> cap_cities, String type) {

        if (type.equals("World")) {
            // Print header
            System.out.println(String.format("%-10s %10s ", "Capital City", "Population"));
            // Loop over all employees in the list
            for (Country cou : cap_cities) {
                String cou_string =
                        String.format("%-10s %10s ",
                                cou.Name, cou.Population);
                System.out.println(cou_string);
            }
        } else if (type.equals("Continent")) {
            // Print Header
            System.out.println(String.format("%-10s %10s %10s ", "Capital City", "Continent", "Population"));
            // Loop over all employees in the list
            for (Country cou : cap_cities) {
                String cou_string =
                        String.format("%-10s %10s %10s ",
                                cou.Name, cou.Continent, cou.Population);
                System.out.println(cou_string);
            }
        }  else {
            // Print Header
            System.out.println(String.format("%-10s %10s %10s %10s ", "Capital City", "Continent", "Region", "Population"));
            // Loop over all employees in the list
            for (Country cou : cap_cities) {
                String cou_string =
                        String.format("%-10s %10s %10s %10s ",
                                cou.Name, cou.Continent, cou.Region, cou.Population);
                System.out.println(cou_string);
            }
        }
    }

    /**
     * Prints a list of Cities
     */
    public static void printCities(ArrayList<City> cities, String type) {

        if (type.equals("World")) {
            // Print header
            System.out.println(String.format("%-10s %35s ", "City", "Population"));
            // Loop over all cities in the list
            for (City cit : cities) {
                String cit_string =
                        String.format("%-10s %35s ",
                                cit.Name, cit.Population);
                System.out.println(cit_string);
            }

        } else if (type.equals("Continent")) {
            // Print Header
            System.out.println(String.format("%-10s %35s %15s ", "City", "Continent", "Population"));
            // Loop over all cities in the list
            for (City cit : cities) {
                String cit_string =
                        String.format("%-10s %35s %15s ",
                                cit.Name, cit.Continent, cit.Population);
                System.out.println(cit_string);
            }

        } else if (type.equals("Region")) {
            // Print Header
            System.out.println(String.format("%-10s %35s %15s %25s ", "City", "Continent", "Region", "Population"));
            // Loop over all cities in the list
            for (City cit : cities) {
                String cit_string =
                        String.format("%-10s %35s %15s %25s",
                                cit.Name, cit.Continent, cit.Region, cit.Population);
                System.out.println(cit_string);
            }

        } else if (type.equals("Country")) {
            // Print Header
            System.out.println(String.format("%-10s %35s %15s %25s %45s ", "City", "Continent", "Region", "Country", "Population"));
            // Loop over all cities in the list
            for (City cit : cities) {
                String cit_string =
                        String.format("%-10s %35s %15s %25s %45s",
                                cit.Name, cit.Continent, cit.Region, cit.Country, cit.Population);
                System.out.println(cit_string);
            }

        }  else {
            // Print Header
            System.out.println(String.format("%-10s %35s %15s %25s %45s %20s ", "City", "Continent", "Region", "Country", "District", "Population"));
            // Loop over all employees in the list
            for (City cit : cities) {
                String cit_string =
                        String.format("%-10s %35s %15s %25s %45s %20s ",
                                cit.Name, cit.Continent, cit.Region, cit.Country, cit.District, cit.Population);
                System.out.println(cit_string);
            }
        }
    }

    public static void printCityPopulation(ArrayList<City> cities) {
        // Print header
        System.out.println(String.format("%-10s %35s ", "City", "Population"));
        // Loop over all cities in the list
        for (City city : cities) {
            String cit_string = String.format("%-10s %35s ",
                                            city.Name, city.Population);
            System.out.println(cit_string);
        }
    }

    /**
     * Prints a list of countries
     * @param countries
     * @param type
     */
    public static void printCountries(ArrayList<Country> countries, String type) {

        if (type.equals("World")) {
            // Print header
            System.out.println(String.format("%-10s %45s ", "Country", "Population"));
            // Loop over all employees in the list
            for (Country cou : countries) {
                String cou_string =
                        String.format("%-10s %45s ",
                                cou.Name, cou.Population);
                System.out.println(cou_string);
            }
        } else if (type.equals("Continent")) {
            // Print Header
            System.out.println(String.format("%-10s %45s %15s ", "Country", "Continent", "Population"));
            // Loop over all employees in the list
            for (Country cou : countries) {
                String cou_string =
                        String.format("%-10s %45s %15s ",
                                cou.Name, cou.Continent, cou.Population);
                System.out.println(cou_string);
            }
        }  else {
            // Print Header
            System.out.println(String.format("%-10s %45s %15s %25s ", "Country", "Continent", "Region", "Population"));
            // Loop over all employees in the list
            for (Country cou : countries) {
                String cou_string =
                        String.format("%-10s %45s %15s %25s ",
                                cou.Name, cou.Continent, cou.Region, cou.Population);
                System.out.println(cou_string);
            }
        }
    }

    /**
     * Gets list of countries
     * @return a Country object
     */
    public Country getCountry() {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population "
                            + "FROM country c "
                            + "ORDER BY 2 DESC "
                            + "LIMIT 1;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            if (rset.next()) {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                return cou;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    /**
     * Prints a list of Languages ranked by total number of speakers
     * @param lang_rank
     */
    public static void printLangRank(ArrayList<Language> lang_rank) {
        // Print header
        System.out.println(String.format("%-10s %10s %10s", "Language", "TotalSpeakers (M)", "PercentOfWorldPop (%)"));
        // Loop over all languages in the list
        for (Language lang : lang_rank) {
            String lang_string =
                    String.format("%-10s %10s %10s",
                            lang.Language, lang.TotalSpeakers, lang.PercentOfWorldPop);
            System.out.println(lang_string);
        }
    }

    public Country getCountry(String code) {

        if (code == null) {
            throw new IllegalArgumentException("code must be specified");
        }

        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, " +
                            "LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2 "
                            + "FROM country c "
                            + " WHERE Code = '" + code + "'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("NAME");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.SurfaceArea = rset.getDouble("SurfaceArea");
                country.IndepYear = rset.getInt("IndepYear");
                country.Population = rset.getInt("Population");
                country.LifeExpectancy = rset.getDouble("LifeExpectancy");
                country.GNP = rset.getDouble("GNP");
                country.GNPOld = rset.getDouble("GNPOld");
                country.LocalName = rset.getString("LocalName");
                country.GovernmentForm = rset.getString("GovernmentForm");
                country.HeadOfState = rset.getString("HeadOfState");
                country.Capital = rset.getInt("Capital");
                country.Code2 = rset.getString("Code2");
                return country;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    /**
     * Languages Spoken Largest To Smallest
     */

    /**
     * Gets list of top 5 languages based on numbers of speakers
     * @return an ArrayList of Language objects
     */
    public ArrayList<Language> getLanguagePopularity() {
        try {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT A.Language, ROUND(SUM((A.Percentage/100) * (B.Population/1000000)),2) AS TotalSpeakers, ROUND((SUM((A.Percentage/100) * (B.Population/1000000))/(SELECT SUM(Population/1000000) from country))*100,2) AS PercentOfWorldPop "
                            + "FROM countrylanguage A "
                            + "LEFT JOIN country B ON A.CountryCode = B.Code "
                            + "WHERE A.Language IN('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') "
                            + "GROUP BY A.Language "
                            + "ORDER BY 2 DESC;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Language> languages = new ArrayList<Language>();
            while (rset.next()) {
                Language lang = new Language();
                lang.Language = rset.getString("Language");
                lang.TotalSpeakers = rset.getDouble("TotalSpeakers");
                lang.PercentOfWorldPop = rset.getDouble("PercentOfWorldPop");
                languages.add(lang);
            }
            // Generate report
            reporter.outputLanguageRanks(languages, "LanguagePopularity.md");
            return languages;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Population details");
            return null;
        }
    }

    private ResultSet getResultSet(Statement statement, String query) throws SQLException {
        return statement.executeQuery(query);
    }
}
