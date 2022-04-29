package com.napier.application.presentation;

import com.napier.application.logic.Query;
import com.napier.application.logic.Report;
import com.napier.application.logic.SQLQuery;

import java.sql.*;

/**
 * Runs the database application
 */
public class App {
    /**
     * Connection to MySQL database.
     */
    private Connection connection = null;

    public static void main(String[] args) {
        App a = new App(); // Create new Application
        if (args.length < 1) {
            a.connect("localhost:33060", 30000); // Connect to database
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        SQLQuery query = new SQLQuery(a.getConnection());
        // All the countries - largest to smallest
        query.getCountryPopulation();
        query.getCountryPopulation("Continent", "Asia");
        query.getCountryPopulation("Region", "Central Africa");
        // Top N countries
        query.getTopNCountryPopulation(5);
        query.getTopNCountryPopulation(5, "Continent", "Europe");
        query.getTopNCountryPopulation(5, "Region", "Eastern Africa");
        // All the cities - largest to smallest
        query.getCityPopulation();
        query.getCityPopulation("Continent","Europe");
        query.getCityPopulation("Region","Western Europe");
        query.getCityPopulation("Country","United Kingdom");
        query.getCityPopulation("District","England");
        // Top N cities
        query.getTopNCityPopulation(5);
        query.getTopNCityPopulation(5,"Continent","Europe");
        query.getTopNCityPopulation(5,"Region","Western Europe");
        query.getTopNCityPopulation(5,"Country","United Kingdom");
        query.getTopNCityPopulation(5,"District","England");
        // All the capital cities - largest to smallest
        query.getCapitalCityPopulation();
        query.getCapitalCityPopulation("Continent", "North America");
        query.getCapitalCityPopulation("Region", "Southeast Asia");
        // Top N capital cities
        query.getTopNCapitalCityPopulation(5);
        query.getTopNCapitalCityPopulation(5,"Continent", "Oceania");
        query.getTopNCapitalCityPopulation(5,"Region","Southern Europe");
        // Population in and out of cities
        query.getPopulationInAndOutOfCities("Continent");
        query.getPopulationInAndOutOfCities("Region");
        query.getPopulationInAndOutOfCities("Country");
        // Total population
        query.getPopulationOf();
        query.getPopulationOf("Continent", "Europe");
        query.getPopulationOf("Country", "United Kingdom");
        query.getPopulationOf("Region", "Asia", "Southern and Central Asia");
        query.getPopulationOf("District", "United Kingdom", "England");
        query.getPopulationOf("City", "England", "London");
        // Languages spoken
        query.getLanguagePercentage();

        a.disconnect(); // Disconnect from database
    }

    /**
     * Gets connection status for outside classes to utilise
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                connection = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException e) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(e.getMessage());
            }
            catch (InterruptedException e) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

}