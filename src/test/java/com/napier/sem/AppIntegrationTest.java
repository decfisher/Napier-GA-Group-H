package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;
    static Report report;
    static Query query;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
        report = new Report(app.getConnection());
        query = new Query(app.getConnection());
    }

    @Test
    void testCountryPopByContinent() {
        Country country = query.getCountry("ABW");
        assertEquals(country.Name, "Aruba");
        assertEquals(country.Continent, "North America");
        assertEquals(country.Region, "Caribbean");
        assertEquals(country.Capital, 129);
    }
}
