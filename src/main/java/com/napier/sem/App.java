package com.napier.sem;

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

        //Report report = new Report(a.connection);
        //report.capitalCityReport();

        // Create query object to initialise queries
        Query query = new Query(a.connection);
        //query.getLanguagePopularity();
        query.getLanguagePopularity();

        a.disconnect(); // Disconnect from database
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay)
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
                connection = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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
        if (connection != null)
        {
            try
            {
                // Close connection
                connection.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}