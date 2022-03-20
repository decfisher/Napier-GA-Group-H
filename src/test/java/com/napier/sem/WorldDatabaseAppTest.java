package com.napier.sem;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class WorldDatabaseAppTest {
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
    void testTopNCountryPopulation() {
        query.getTopNCountryPopulation(0,null, null);
    }

    @Test
    void testGetCountry() {
        query.getCountry(null);
    }

    @Test
    void testPrintCountries() {
        query.topNPopulatedCapitalCities(null,null,0);
    }

}