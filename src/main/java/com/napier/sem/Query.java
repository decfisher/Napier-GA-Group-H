package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
            // Return query result if query is sucessful
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



    //overloaded Java method to get Population by Continent
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
                            + " ORDER BY 2 DESC";

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


    //overloaded Java method to get Population by Continent and region
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
                            + " ORDER BY 2 DESC ";

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
        } else if (type.equals("Region")) {
            // Print Header
            System.out.println(String.format("%-10s %10s %10s ", "Country", "Region", "Population"));
            // Loop over all employees in the list
            for (Country cou : countries) {
                String cou_string =
                        String.format("%-10s %10s %10s ",
                                cou.Name, cou.Region, cou.Population);
                System.out.println(cou_string);
            }
        } else {
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

    // Top N populous countries in the world
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

    // Top N populous countries in a continent/region
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

    public void getPopulation(String queryType) {
        try {
            if (queryType.equals("Continent")) {
                // Create an SQL statement
                Statement stmt = connection.createStatement();
                // Create string for SQL statement
                String strSelect =
                        "SELECT continent, COUNT(*) AS 'population' p " +
                                "FROM country c " +
                                "GROUP BY continent " +
                                "ORDER BY p DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next())
                {
                    Country country = new Country();
                    country.Continent = rset.getString("Continent");
                    country.Population = rset.getInt("Population");
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
                        "SELECT region, population "
                                + "FROM country c "
                                + "GROUP BY region"
                                + " ORDER BY population DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next())
                {
                    Country country = new Country();
                    country.Continent = rset.getString("Region");
                    country.Population = rset.getInt("Population");
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
                        "SELECT country, population "
                                + "FROM country c "
                                + " ORDER BY population DESC";
                // Execute SQL statement
                ResultSet rset = stmt.executeQuery(strSelect);
                // Return query result if query is successful
                // Check one is returned
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rset.next())
                {
                    Country country = new Country();
                    country.Continent = rset.getString("NAME");
                    country.Population = rset.getInt("Population");
                    countries.add(country);
                }
                System.out.println("Population in each region");
                // Print Header
                System.out.println(String.format("%-10s %10s ", "Name", "Population"));
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
}
