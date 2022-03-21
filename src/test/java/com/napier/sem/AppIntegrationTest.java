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

    //James Integration Tests for story #14
    @Test
    void getCountriesByPopulation0param()
    {
        ArrayList<Country> countries = query.getCountriesByPopulation();
        Country cou = countries.get(0);
        assertEquals(cou.Name,"China");
        assertEquals(cou.Population,1277558000);


    }
    @Test
    void getCountriesByPopulation1param()
    {
        ArrayList<Country> countries = query.getCountriesByPopulation("Asia");
        Country cou = countries.get(0);
        assertEquals(cou.Name,"China");
        assertEquals(cou.Population,1277558000);
        assertEquals(cou.Continent,"Asia");

    }

    @Test
    void getCountriesByPopulation2param()
    {
        ArrayList<Country> countries = query.getCountriesByPopulation("Asia","Eastern Asia");
        Country cou = countries.get(0);
        assertEquals(cou.Name,"China");
        assertEquals(cou.Population,1277558000);
        assertEquals(cou.Continent,"Asia");
        assertEquals(cou.Region,"Eastern Asia");

    }
    //James Integration Tests for story #21
    @Test
    void getPopulationOf0param()
    {
        Country country = query.getPopulationOf();
        assertEquals(country.Name, "World");
        assertEquals(country.Population, 6078749450L);
    }
    @Test
    void getPopulationOf1param()
    {
        Country country = query.getPopulationOf("Asia");
        assertEquals(country.Name, "Asia");
        assertEquals(country.Population, 3705025700L);
    }
    @Test
    void getPopulationOf2param()
    {
        Country country = query.getPopulationOf("Asia","Eastern Asia");
        assertEquals(country.Name, "Eastern Asia");
        assertEquals(country.Population, 1507328000);
    }
    @Test
    void getPopulationOf3param()
    {
        Country country = query.getPopulationOf("Asia","Eastern Asia","China");
        assertEquals(country.Name, "China");
        assertEquals(country.Population, 1277558000);
    }
    @Test
    void getPopulationOf4param()
    {
        Country country = query.getPopulationOf("Asia","Eastern Asia","China","Peking");
        assertEquals(country.Name, "Peking");
        assertEquals(country.Population, 7472000);
    }
    @Test
    void getPopulationOf5param()
    {
        Country country = query.getPopulationOf("Asia","Eastern Asia","China","Peking","Peking");
        assertEquals(country.Name, "Peking");
        assertEquals(country.Population, 7472000);
    }
}
