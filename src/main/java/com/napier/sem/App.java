package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/**
 * Runs the database application
 */
public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        //Country cou = a.getCountry();
        // Extract employee salary information
        //ArrayList<Country> countries = a.getCountriesByPopulation();
        //ArrayList<Country> countries = a.getCountriesByPopulation("Antarctica");
        ArrayList<Country> countries = a.getCountriesByPopulation("Asia", "Eastern Asia");
        //a.displayCountry(cou);
        //a.printCountries(countries);


        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
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
            Statement stmt = con.createStatement();
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
            Statement stmt = con.createStatement();
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
            Statement stmt = con.createStatement();
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
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT NAME, population, Continent, Region "
                            + "FROM country c "
                            + "WHERE continent = '" + Continent + "' AND Region = '" + Region + "' "
                            + " ORDER BY 2 DESC ";

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


    public static void printCountries(ArrayList<Country> countries, String type)
    {

        if (type == "World")
        {
            // Print header
            System.out.println(String.format("%-10s %10s ", "Country", "Population"));
            // Loop over all employees in the list
            for (Country cou : countries)
            {
                String cou_string =
                        String.format("%-10s %10s ",
                                cou.Name, cou.Population);
                System.out.println(cou_string);
            }

        } else if (type == "Continent")
        {
            // Print Header
            System.out.println(String.format("%-10s %10s %10s ", "Country", "Continent", "Population"));
            // Loop over all employees in the list
            for (Country cou : countries)
            {
                String cou_string =
                        String.format("%-10s %10s %10s ",
                                cou.Name, cou.Continent, cou.Population);
                System.out.println(cou_string);
            }

        } else
        {
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
}