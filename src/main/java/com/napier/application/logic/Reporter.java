package com.napier.application.logic;

import com.napier.application.data.City;
import com.napier.application.data.Country;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Produces reports for the report branch and queries
 */
public final class Reporter {

    public void outputCountries(ArrayList<Country> countries, String type, String fileName) {

        if (type.equals("World")) {
            // Check array is not null
            if (countries.isEmpty()) {
                System.out.println("No countries!");
                return;
            }

            StringBuilder sb = new StringBuilder();
            // Print header
            sb.append("| Country | Population |\r\n");
            sb.append("| ------- | ---------- |\r\n");

            // Loop over all cities in the list
            for (Country country : countries) {
                if (country == null) {
                    continue;
                }
                sb.append("| " + country.Name + " | " + country.Population + " |\r\n");
            }
            // Generate report directory and markdown file
            generateReport(sb, fileName);
        }

        if (type.equals("Continent")) {
            // Check array is not null
            if (countries.isEmpty()) {
                System.out.println("No countries!");
                return;
            }

            StringBuilder sb = new StringBuilder();
            // Print header
            sb.append("| Country | Continent | Population |\r\n");
            sb.append("| ------- | --------- | ---------- |\r\n");

            // Loop over all cities in the list
            for (Country country : countries) {
                if (country == null) {
                    continue;
                }
                sb.append("| " + country.Name + " | " + country.Continent + " | " + country.Population + " |\r\n");
            }
            // Generate report directory and markdown file
            generateReport(sb, fileName);
        }

        if (type.equals("Region")) {
            // Check array is not null
            if (countries.isEmpty()) {
                System.out.println("No countries!");
                return;
            }

            StringBuilder sb = new StringBuilder();
            // Print header
            sb.append("| Country | Continent | Region | Population |\r\n");
            sb.append("| ------- | --------- | ------ | ---------- |\r\n");

            // Loop over all cities in the list
            for (Country country : countries) {
                if (country == null) {
                    continue;
                }
                sb.append("| " + country.Name + " | " + country.Continent + " | " + country.Region + " | " + country.Population + " |\r\n");
            }
            // Generate report directory and markdown file
            generateReport(sb, fileName);
        }
    }

    public void outputCityPopulation(ArrayList<City> cities, String fileName) {
        // Check array is not null
        if (cities.isEmpty()) {
            System.out.println("No cities!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| City | Population |\r\n");
        sb.append("| ---- | ---------- |\r\n");

        // Loop over all cities in the list
        for (City city : cities) {
            if (city == null) {
                continue;
            }
            sb.append("| " + city.Name + " | " + city.Population + " |\r\n");
        }

        // Generate report directory and markdown file
        generateReport(sb, fileName);
    }

    /**
     * Helper functions
     */

    public String concatString(String name) {
        String[] splitter = name.split(" |,");
        StringBuilder catName = new StringBuilder();
        for (String s : splitter) {
            if (s.equals("&") || s.equals("and")) {
                catName.append("And");
            } else if (s.equals("'") || s.equals("’")){
                continue;
            } else {
                catName.append(s);
            }
        }
        return catName.toString();
    }

    private void generateReport(StringBuilder stringBuilder, String fileName) {
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter("./reports/" + fileName));
            writer.write(stringBuilder.toString());
            writer.close();
            System.out.println("Report \"" + fileName + "\" generated successfully!");
        } catch (IOException e) {
            System.out.println("Could not generate report \"" + fileName + "\"!");
            System.out.println(e.getMessage());
        }
    }

}
