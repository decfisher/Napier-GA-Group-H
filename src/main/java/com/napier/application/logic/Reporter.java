package com.napier.application.logic;

import com.napier.application.data.City;
import com.napier.application.data.Country;
import com.napier.application.data.Language;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Produces reports for the report branch and queries
 */
public final class Reporter {

    public boolean outputCountries(ArrayList<Country> countries, String type, String fileName) {
        // Check option strings are not null
        if (type == null || fileName == null) {
            System.out.println("Input cannot be null!");
            return false;
        }

        // Check array is not null
        if (countries.isEmpty()) {
            System.out.println("No countries!");
            return false;
        }

        // Create output string builder
        StringBuilder sb = new StringBuilder();

        if (type.equals("World")) {
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
        }

        if (type.equals("Continent")) {
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
        }

        if (type.equals("Region")) {
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
        }

        if (!(sb.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(sb, fileName);
            return true;
        }

        return false;
    }

    public boolean outputCities(ArrayList<City> cities, String type, String fileName) {
        // Check array is not null
        if (cities.isEmpty()) {
            System.out.println("No cities!");
            return false;
        }

        // Create output string builder
        StringBuilder sb = new StringBuilder();

        if (type.equals("World")) {
            // Print header
            sb.append("| Country | Population |\r\n");
            sb.append("| ------- | ---------- |\r\n");

            // Loop over all cities in the list
            for (City city : cities) {
                if (city == null) {
                    continue;
                }
                sb.append("| " + city.Name + " | " + city.Population + " |\r\n");
            }
        }

        if (type.equals("Continent")) {
            // Print header
            sb.append("| Country | Continent | Population |\r\n");
            sb.append("| ------- | --------- | ---------- |\r\n");

            // Loop over all cities in the list
            for (City city : cities) {
                if (city == null) {
                    continue;
                }
                sb.append("| " + city.Name + " | " + city.Continent + " | " + city.Population + " |\r\n");
            }
        }

        if (type.equals("Region")) {
            // Print header
            sb.append("| Country | Continent | Region | Population |\r\n");
            sb.append("| ------- | --------- | ------ | ---------- |\r\n");

            // Loop over all cities in the list
            for (City city : cities) {
                if (city == null) {
                    continue;
                }
                sb.append("| " + city.Name + " | " + city.Continent + " | " + city.Region + " | " + city.Population + " |\r\n");
            }
        }

        if (type.equals("Country")) {
            // Print header
            sb.append("| Country | Continent | Region | Country | Population |\r\n");
            sb.append("| ------- | --------- | ------ | ------- | ---------- |\r\n");

            // Loop over all cities in the list
            for (City city : cities) {
                if (city == null) {
                    continue;
                }
                sb.append("| " + city.Name + " | " + city.Continent + " | " + city.Region + " | " + city.Country + " | " + city.Population + " |\r\n");
            }
        }

        if (type.equals("District")) {
            // Print header
            sb.append("| Country | Continent | Region | Country | District | Population |\r\n");
            sb.append("| ------- | --------- | ------ | ------- | -------- | ---------- |\r\n");

            // Loop over all cities in the list
            for (City city : cities) {
                if (city == null) {
                    continue;
                }
                sb.append("| " + city.Name + " | " + city.Continent + " | " + city.Region + " | " + city.Country + " | " + city.District + " | " + city.Population + " |\r\n");
            }
        }

        if (!(sb.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(sb, fileName);
            return true;
        }

        return false;
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

    public void outputCityPopulationInAndOut(ArrayList<Country> countries, String type, String fileName) {
        // Check array is not null
        if (countries.isEmpty()) {
            System.out.println("No cities!");
            return;
        }

        // Create output string builder
        StringBuilder sb = new StringBuilder();

        if (type.equals("Continent")) {
            // Print header
            sb.append("| Continent | Total Population | In City | Out Of City |\r\n");
            sb.append("| --------- | ---------------- | ------- | ----------- |\r\n");

            // Loop over all cities in the list
            for (Country country : countries) {
                if (country == null) {
                    continue;
                }
                sb.append("| " + country.Continent + " | " + country.Population + " | " + country.InCityPop + " | " + country.OutCityPop + " |\r\n");
            }
        }

        if (type.equals("Region")) {
            // Print header
            sb.append("| Region | Total Population | In City | Out Of City |\r\n");
            sb.append("| ------ | ---------------- | ------- | ----------- |\r\n");

            // Loop over all cities in the list
            for (Country country : countries) {
                if (country == null) {
                    continue;
                }
                sb.append("| " + country.Region + " | " + country.Population + " | " + country.InCityPop + " | " + country.OutCityPop + " |\r\n");
            }
        }

        if (type.equals("Country")) {
            // Print header
            sb.append("| Country | Total Population | In City | Out Of City |\r\n");
            sb.append("| ------- | ---------------- | ------- | ----------- |\r\n");

            // Loop over all cities in the list
            for (Country country : countries) {
                if (country == null) {
                    continue;
                }
                sb.append("| " + country.Name + " | " + country.Population + " | " + country.InCityPop + " | " + country.OutCityPop + " |\r\n");
            }
        }

        if (!(sb.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(sb, fileName);
        }
    }

    public boolean outputCapitalCities(ArrayList<Country> capitalCities, String type, String fileName) {
        // Check array is not null
        if (capitalCities.isEmpty()) {
            System.out.println("No capital cities!");
            return false;
        }

        StringBuilder sb = new StringBuilder();

        if (type.equals("World")) {
            // Print header
            sb.append("| Capital City | Population |\r\n");
            sb.append("| ------------ | ---------- |\r\n");

            // Loop over all cities in the list
            for (Country capitalCity : capitalCities) {
                if (capitalCity == null) {
                    continue;
                }
                sb.append("| " + capitalCity.Name + " | " + capitalCity.Population + " |\r\n");
            }
        }

        if (type.equals("Continent")) {
            // Print header
            sb.append("| Capital City | Continent | Population |\r\n");
            sb.append("| ------------ | --------- | ---------- |\r\n");

            // Loop over all cities in the list
            for (Country capitalCity : capitalCities) {
                if (capitalCity == null) {
                    continue;
                }
                sb.append("| " + capitalCity.Name + " | " + capitalCity.Continent + " | " + capitalCity.Population + " |\r\n");
            }
        }

        if (type.equals("Region")) {
            // Print header
            sb.append("| Capital City | Continent | Region | Population |\r\n");
            sb.append("| ------------ | --------- | ------ | ---------- |\r\n");

            // Loop over all cities in the list
            for (Country capitalCity : capitalCities) {
                if (capitalCity == null) {
                    continue;
                }
                sb.append("| " + capitalCity.Name + " | " + capitalCity.Continent + " | " + capitalCity.Region + " | " + capitalCity.Population + " |\r\n");
            }
        }

        if (!(sb.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(sb, fileName);
            return true;
        }

        return false;
    }

    public void outputPopulation(ArrayList<Country> countries, String type, String fileName) {
        // Check array is not null
        if (countries.isEmpty()) {
            System.out.println("No countries!");
            return;
        }

        StringBuilder sb = new StringBuilder();

        if (type.equals("Continent")) {
            // Print header
            sb.append("| Continent | Population |\r\n");
            sb.append("| --------- | ---------- |\r\n");

            // Loop over all cities in the list
            for (Country country : countries) {
                if (country == null) {
                    continue;
                }
                sb.append("| " + country.Continent + " | " + country.Population + " |\r\n");
            }
        }

        if (type.equals("Region")) {
            // Print header
            sb.append("| Region | Population |\r\n");
            sb.append("| ------ | ---------- |\r\n");

            // Loop over all cities in the list
            for (Country country : countries) {
                if (country == null) {
                    continue;
                }
                sb.append("| " + country.Region + " | " + country.Population + " |\r\n");
            }
        }

        if (type.equals("Country")) {
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
        }

        if (!(sb.length() == 0)) {
            // Generate report directory and markdown file
            generateReport(sb, fileName);
        }
    }

    public void outputPopulation(Country country, String type, String fileName) {

        if (country == null) {
            System.out.println("No country!");
            return;
        }

        StringBuilder sb = new StringBuilder();

        if (type.equals("World")) {
            // Print header
            sb.append("|   | Population |\r\n");
            sb.append("| - | ---------- |\r\n");
            sb.append("| " + country.Name + " | " + country.Population + " |\r\n");
        }

        if (type.equals("Continent")) {
            // Print header
            sb.append("| Continent | Population |\r\n");
            sb.append("| --------- | ---------- |\r\n");
            sb.append("| " + country.Continent + " | " + country.Population + " |\r\n");
        }

        if (type.equals("Region")) {
            // Print header
            sb.append("| Region | Population |\r\n");
            sb.append("| ------ | ---------- |\r\n");
            sb.append("| " + country.Region + " | " + country.Population + " |\r\n");
        }

        if (type.equals("Country")) {
            // Print header
            sb.append("| Country | Population |\r\n");
            sb.append("| ------- | ---------- |\r\n");
            sb.append("| " + country.Name + " | " + country.Population + " |\r\n");
        }

        if (type.equals("District")) {
            // Print header
            sb.append("| District | Population |\r\n");
            sb.append("| -------- | ---------- |\r\n");
            sb.append("| " + country.Name + " | " + country.Population + " |\r\n");
        }

        if (type.equals("City")) {
            // Print header
            sb.append("| City | Population |\r\n");
            sb.append("| ---- | ---------- |\r\n");
            sb.append("| " + country.Name + " | " + country.Population + " |\r\n");
        }

        if (!(sb.length() == 0)) {
            generateReport(sb, fileName);
        }
    }

    public void outputLanguageRanks(ArrayList<Language> languageRanks, String fileName) {
        // Check array is not null
        if (languageRanks.isEmpty()) {
            System.out.println("No languages!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Language | Total Speakers (M) | Percent Of World Population (%) |\r\n");
        sb.append("| -------- | ------------------ | ------------------------------- |\r\n");

        for (Language language : languageRanks) {
            if (language == null) {
                continue;
            }
            sb.append("| " + language.Language + " | " + language.TotalSpeakers + " | " + language.PercentOfWorldPop + " |\r\n");
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
