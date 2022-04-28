package com.napier.application.logic;

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
