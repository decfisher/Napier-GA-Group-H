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

    //Declan To Check*******************************************************************************************
    @Test
    void testgetCountriesByPopulation() { query.getCountriesByPopulation();}

    @Test
    void testgetCountriesByPopulation2() { query.getCountriesByPopulation(null);}

    @Test
    void testgetCountriesByPopulation3() { query.getCountriesByPopulation(null,null);}

    @Test
    void testgetTopNCountryPopulation() { query.getTopNCountryPopulation(0);}

    @Test
    void testgetTopNCountryPopulation2() { query.getTopNCountryPopulation(0,null,null);}

    @Test
    void testlargeToSmallCityPopulation3() { query.largeToSmallCityPopulation();}

    @Test
    void testlargeToSmallCityPopulation4() { query.largeToSmallCityPopulation(null,null);}

    @Test
    void testgetCapitalCitiesByPopulation() { query.getCapitalCitiesByPopulation();}

    @Test
    void testgetCapitalCitiesByPopulation2() { query.getCapitalCitiesByPopulation(null);}

    @Test
    void testgetCapitalCitiesByPopulation3() { query.getCapitalCitiesByPopulation(null,null);}

    @Test
    void testtopNPopulatedCapitalCities() { query.topNPopulatedCapitalCities(0);}

    @Test
    void testtopNPopulatedCapitalCities2() { query.topNPopulatedCapitalCities(null,0);}

    @Test
    void testtopNPopulatedCapitalCities3() { query.topNPopulatedCapitalCities(null,null,0);}

    @Test
    void testgetPopulation() { query.getPopulation(null);}

    @Test
    void testgetPopulationOf() { query.getPopulationOf();}

    @Test
    void testgetPopulationOf2() { query.getPopulationOf(null);}

    @Test
    void testgetPopulationOf3() { query.getPopulationOf(null,null);}

    @Test
    void testgetPopulationOf4() { query.getPopulationOf(null,null,null);}

    @Test
    void testgetPopulationOf5() { query.getPopulationOf(null,null,null,null);}

    @Test
    void testgetPopulationOf6() { query.getPopulationOf(null,null,null,null,null);}


}