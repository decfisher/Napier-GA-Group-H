package com.napier.application.logic;

import com.napier.application.data.City;
import com.napier.application.data.Country;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Exporter {

    private StringBuilder output;

    private Exporter(StringBuilder output) {
        this.output = output;
    }

    public Exporter() {
        this(new StringBuilder());
    }

    public void countryReport(ArrayList<Country> countries, String fileName) {

        if (countries.isEmpty()) {
            throw new IllegalArgumentException("No countries!");
        }

        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("No file name specified!");
        }

        // Print header
        output.append("| Code | Name | Continent | Region | Population | Capital |\r\n");
        output.append("| ---- | ---- | --------- | ------ | ---------- | ------- |\r\n");

        // Loop over all countries in the list
        for (Country country : countries) {
            if (country == null) {
                continue;
            }
            output.append("| " + country.Code + "| " + country.Name + "| " + country.Continent + "| " + country.Region + "| " + country.Population + " | " + country.CapitalCity + " |\r\n");
        }

        if (!(output.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(output, fileName);
        }

        output.setLength(0);
    }

    public void cityReport(ArrayList<City> cities, String fileName) {

        if (cities.isEmpty()) {
            throw new IllegalArgumentException("No cities!");
        }

        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("No file name specified!");
        }

        // Print header
        output.append("| Name | Country | District | Population |\r\n");
        output.append("| ---- | ------- | -------- | ---------- |\r\n");

        // Loop over all countries in the list
        for (City city : cities) {
            if (city == null) {
                continue;
            }
            output.append("| " + city.Name + "| " + city.Country + "| " + city.District + "| " + city.Population + " |\r\n");
        }

        if (!(output.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(output, fileName);
        }

        output.setLength(0);
    }

    public void capitalCityReport(ArrayList<City> capitalCities, String fileName) {

        if (capitalCities.isEmpty()) {
            throw new IllegalArgumentException("No capital cities!");
        }

        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("No file name specified!");
        }

        // Print header
        output.append("| Name | Country | Population |\r\n");
        output.append("| ---- | ------- | ---------- |\r\n");

        // Loop over all countries in the list
        for (City capitalCity : capitalCities) {
            if (capitalCity == null) {
                continue;
            }
            output.append("| " + capitalCity.Name + "| " + capitalCity.Country + "| " + capitalCity.Population + " |\r\n");
        }

        if (!(output.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(output, fileName);
        }

        output.setLength(0);
    }

    public void populationInAndOutReport(ArrayList<Country> populations, String type, String fileName) {

        if (populations.isEmpty()) {
            throw new IllegalArgumentException("No city populations!");
        }

        if (type.isEmpty()) {
            throw new IllegalArgumentException("An option type must be specified!");
        }

        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("No file name specified!");
        }

        // Print header
        output.append("| " + type + " | Total Population | Population In Cities | Population In Cities (%) | Population Out of Cities | Population Out of Cities (%) |\r\n");
        output.append("| --------- | ---------------- | -------------------- | ------------------------ | ------------------------ | ---------------------------- |\r\n");

        String option = "";
        // Loop over all countries in the list
        for (Country population : populations) {
            if (population == null) {
                continue;
            }

            if (type.equals("Continent")) {
                option = population.Continent;
            }

            if (type.equals("Region")) {
                option = population.Region;
            }

            if (type.equals("Country")) {
                option = population.Name;
            }

            output.append("| " + option + " | " + population.Population + " | " + population.InCityPop +
                    " | " + population.InCityPerc + " | " + population.OutCityPop + " | " + population.OutCityPerc + "|\r\n");
        }

        if (!(output.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(output, fileName);
        }

        output.setLength(0);
    }

    public void populationReport(Country country, String fileName) {

        if (country == null) {
            throw new IllegalArgumentException("Country cannot be null object!");
        }

        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("No file name specified!");
        }

        // Print header
        output.append("| Name | Population |\r\n");
        output.append("| ---- | ---------- |\r\n");
        output.append("| " + country.Name + "| " + country.Population + " |\r\n");

        if (!(output.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(output, fileName);
        }

        output.setLength(0);
    }

    /**
     * Helper methods
     */

    /**
     * Generate report and put into "reports" folder
     * @param stringBuilder
     * @param fileName
     */

    private void generateReport(StringBuilder stringBuilder, String fileName) {
        String newFileName = concatString(fileName) + ".md";
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter("./reports/" + newFileName));
            writer.write(stringBuilder.toString());
            writer.close();
            System.out.println("Report \"" + newFileName + "\" generated successfully!");
        } catch (IOException e) {
            System.out.println("Could not generate report \"" + newFileName + "\"!");
            System.out.println(e.getMessage());
        }
    }

    private String concatString(String name) {
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
}
