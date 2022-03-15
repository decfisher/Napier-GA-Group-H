package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Produces queries from the database
 */
public class Query {

    private Connection connection;

    public Query(Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets list of countries
     * @return a Country object
     */
    public Country getCountry()
    {
        try
        {
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
            if (rset.next())
            {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                return cou;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
    }

    /**
     * Gets list of countries by their population count
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getCountriesByPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population "
                            + "FROM country c "
                            + "ORDER BY 2 DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is sucessful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                countries.add(cou);
            }
            printCountries(countries, "World");
            return countries;
        }
        catch (Exception e)
        {
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
    public ArrayList<Country> getCountriesByPopulation(String Continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population, Continent "
                            + "FROM country c "
                            + "WHERE continent = '" + Continent + "' "
                            + " ORDER BY 3 DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is sucessful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                cou.Continent = rset.getString("Continent");
                countries.add(cou);
            }
            printCountries(countries, "Continent");
            return countries;
        }
        catch (Exception e)
        {
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
    public ArrayList<Country> getCountriesByPopulation(String Continent, String Region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population, Continent, Region "
                            + "FROM country c "
                            + "WHERE continent = '" + Continent + "' AND Region = '" + Region + "' "
                            + " ORDER BY 4 DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                cou.Continent = rset.getString("Continent");
                cou.Region = rset.getString("Region");
                countries.add(cou);
            }
            printCountries(countries, "Region");
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
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
            System.out.println(String.format("%-10s %10s ", "Country", "Population"));
            // Loop over all employees in the list
            for (Country cou : countries) {
                String cou_string =
                        String.format("%-10s %10s ",
                                cou.Name, cou.Population);
                System.out.println(cou_string);
            }
        } else if (type.equals("Continent")) {
            // Print Header
            System.out.println(String.format("%-10s %10s %10s ", "Country", "Continent", "Population"));
            // Loop over all employees in the list
            for (Country cou : countries) {
                String cou_string =
                        String.format("%-10s %10s %10s ",
                                cou.Name, cou.Continent, cou.Population);
                System.out.println(cou_string);
            }
        }  else {
            // Print Header
            System.out.println(String.format("%-10s %10s %10s %10s ", "Country", "Continent", "Region", "Population"));
            // Loop over all employees in the list
            for (Country cou : countries) {
                String cou_string =
                        String.format("%-10s %10s %10s %10s ",
                                cou.Name, cou.Continent, cou.Region, cou.Population);
                System.out.println(cou_string);
            }
        }
    }

    /**
     * Gets the top N of countries based on population
     * @param n
     * @return an ArrayList of Countries
     */
    public ArrayList<Country> getTopNCountryPopulation(int n) {
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
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.Name = rset.getString("NAME");
                country.Population = rset.getInt("Population");
                countries.add(country);
            }
            System.out.println("Top " + n + " Most Populated Countries in the World");
            printCountries(countries, "World");
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
        try {
            if (queryType.equals("Continent")) {
                // Create an SQL statement
                Statement stmt = connection.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT NAME, continent, population "
                                + "FROM country c "
                                + "WHERE continent = '" + name + "'"
                                + " ORDER BY population DESC"
                                + " LIMIT " + n;

                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next())
                {
                    Country country = new Country();
                    country.Name = rset.getString("NAME");
                    country.Continent = rset.getString("Continent");
                    country.Population = rset.getInt("Population");
                    countries.add(country);
                }
                System.out.println("Top " + n + " Most Populated Countries in " + name);
                printCountries(countries, "Continent");
                return countries;
            } else if (queryType.equals("Region")) {
                // Create an SQL statement
                Statement stmt = connection.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT NAME, region, population "
                                + "FROM country c "
                                + "WHERE region = '" + name + "'"
                                + " ORDER BY population DESC"
                                + " LIMIT " + n;
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
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
                System.out.println("Top " + n + " Most Populated Countries in " + name);
                printCountries(countries, "Region");
                return countries;
            } else {
                throw new Exception();
            }
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
        try {
            if (queryType.equals("Continent")) {
                // Create an SQL statement
                Statement stmt = connection.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT continent, SUM(population) AS Population " +
                                "FROM country c " +
                                "GROUP BY 1 " +
                                "ORDER BY Population DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next())
                {
                    Country country = new Country();
                    country.Continent = rset.getString("Continent");
                    country.Population = rset.getLong("Population");
                    countries.add(country);
                }
                System.out.println("Population in each continent");
                // Print Header
                System.out.println(String.format("%-10s %10s ", "Continent", "Population"));
                // Loop over all countries in the list
                for (Country cou : countries) {
                    String cou_string =
                            String.format("%-10s %10s ",
                                    cou.Continent, cou.Population);
                    System.out.println(cou_string);
                }

            } else if (queryType.equals("Region")) {
                // Create an SQL statement
                Statement stmt = connection.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT region, SUM(population) AS Population " +
                                "FROM country c " +
                                "GROUP BY 1 " +
                                "ORDER BY Population DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next())
                {
                    Country country = new Country();
                    country.Region = rset.getString("Region");
                    country.Population = rset.getLong("Population");
                    countries.add(country);
                }
                System.out.println("Population in each region");
                // Print Header
                System.out.println(String.format("%-10s %10s ", "Region", "Population"));
                // Loop over all countries in the list
                for (Country cou : countries) {
                    String cou_string =
                            String.format("%-10s %10s ",
                                    cou.Region, cou.Population);
                    System.out.println(cou_string);
                }
            } else if (queryType.equals("Country")) {
                // Create an SQL statement
                Statement stmt = connection.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT NAME, SUM(population) AS Population " +
                                "FROM country c " +
                                "GROUP BY 1 " +
                                "ORDER BY Population DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next())
                {
                    Country country = new Country();
                    country.Name = rset.getString("NAME");
                    country.Population = rset.getLong("Population");
                    countries.add(country);
                }
                System.out.println("Population in each region");
                // Print Header
                System.out.println(String.format("%-10s %10s ", "Country", "Population"));
                // Loop over all countries in the list
                for (Country cou : countries) {
                    String cou_string =
                            String.format("%-10s %10s ",
                                    cou.Name, cou.Population);
                    System.out.println(cou_string);
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to execute query");
        }
    }

    /**
     * Gets list of Capital Cities by their population count
     * @return an ArrayList of Country objects
     */
    public ArrayList<Country> getCapitalCitiesByPopulation()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "ORDER BY 2 DESC; ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is sucessful
            // Check one is returned
            ArrayList<Country> cap_cities = new ArrayList<Country>();
            while (rset.next())
            {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                cap_cities.add(cou);
            }
            printCapitalCities(cap_cities, "World");
            return cap_cities;
        }
        catch (Exception e)
        {
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
    public ArrayList<Country> getCapitalCitiesByPopulation(String Continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, co.Continent, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Continent = '"+ Continent + "' "
                            + "ORDER BY 3 DESC; ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is sucessful
            // Check one is returned
            ArrayList<Country> cap_cities = new ArrayList<Country>();
            while (rset.next())
            {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                cou.Continent = rset.getString("Continent");
                cap_cities.add(cou);
            }
            printCapitalCities(cap_cities, "Continent");
            return cap_cities;
        }
        catch (Exception e)
        {
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
    public ArrayList<Country> getCapitalCitiesByPopulation(String Continent, String Region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = connection.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name, co.Continent, co.Region, ci.Population "
                            + "FROM country co "
                            + "LEFT JOIN city ci ON co.Capital = ci.ID "
                            + "WHERE co.Continent = '"+ Continent + "' AND co.Region = '" + Region + "' "
                            + "ORDER BY 4 DESC; ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return query result if query is successful
            // Check one is returned
            ArrayList<Country> cap_cities = new ArrayList<Country>();
            while (rset.next())
            {
                Country cou = new Country();
                cou.Name = rset.getString("NAME");
                cou.Population = rset.getInt("Population");
                cou.Continent = rset.getString("Continent");
                cou.Region = rset.getString("Region");
                cap_cities.add(cou);
            }
            printCapitalCities(cap_cities, "Region");
            return cap_cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City  details");
            return null;
        }
    }

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
}
