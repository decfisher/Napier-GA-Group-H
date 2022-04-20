package com.napier.application.data;

import com.napier.application.logic.Reporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ApplicationReporterTests {
    static Reporter reporter;

    @BeforeAll
    static void init() {
        reporter = new Reporter();
    }

    // IF ADDING UNIT TEST FOR A METHOD IN REPORTER CLASS, MAKE SURE THE METHOD OUTPUTS A BOOLEAN VALUE AND NOT VOID!
    // LOOK AT THE FIRST COUPLE OF METHODS TO GET AN IDEA - ONLY MANAGED TO UPDATE FIRST COUPLE METHODS

    /**
     * Country Reporting Methods
     */

    @Test
    void givenEmptyCountryArray_whenGeneratingReport_thenReturnFalse() {
        // Setup
        ArrayList<Country> countries = new ArrayList<>();
        String type = "World";
        String fileName = "testCountries.md";

        // Action
        boolean output = reporter.outputCountries(countries, type, fileName);

        // Output
        Assertions.assertFalse(output);
    }

    @Test
    void givenNullInputStrings_whenGeneratingReport_thenReturnFalse() {
        // Setup
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.Name = "France";
        country.Population = 67390000;
        countries.add(country);

        // Action
        boolean nullTypeOutput = reporter.outputCountries(countries, null, "testCountries.md");
        boolean nullFileNameOutput = reporter.outputCountries(countries, "World", null);

        // Output
        Assertions.assertFalse(nullTypeOutput);
        Assertions.assertFalse(nullFileNameOutput);
    }

    @Test
    void givenIncorrectTypeString_whenGeneratingReport_thenReturnFalse() {
        // Setup
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.Name = "France";
        country.Population = 67390000;
        countries.add(country);

        // Action
        boolean output = reporter.outputCountries(countries, "ThisIsATest", "testCountries.md");

        // Output
        Assertions.assertFalse(output);
    }

    /**
     * City Reporting Methods
     */

    @Test
    void givenEmptyCityArray_whenGeneratingReport_thenReturnFalse() {
        // Setup
        ArrayList<City> cities = new ArrayList<>();
        String type = "World";
        String fileName = "testCities.md";

        // Action
        boolean output = reporter.outputCities(cities, type, fileName);

        // Output
        Assertions.assertFalse(output);
    }

    /**
     * Capital City Reporting Methods
     */

    @Test
    void givenEmptyCapitalCityArray_whenGeneratingReport_thenReturnFalse() {
        // Setup
        ArrayList<Country> capitalCities = new ArrayList<>();
        String type = "World";
        String fileName = "testCapitalCities.md";

        // Action
        boolean output = reporter.outputCapitalCities(capitalCities, type, fileName);

        // Output
        Assertions.assertFalse(output);
    }
}
