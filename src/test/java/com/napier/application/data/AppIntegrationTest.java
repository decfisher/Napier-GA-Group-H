package com.napier.application.data;

import com.napier.application.logic.Query;
import com.napier.application.logic.Report;
import com.napier.application.presentation.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
//    @Test
//    void getPopulationOf0param()
//    {
//        Country country = query.getPopulationOf();
//        assertEquals(country.Name, "World");
//        assertEquals(country.Population, 6078749450L);
//    }
//    @Test
//    void getPopulationOf1param()
//    {
//        Country country = query.getPopulationOf("Asia");
//        assertEquals(country.Name, "Asia");
//        assertEquals(country.Population, 3705025700L);
//    }
//    @Test
//    void getPopulationOf2param()
//    {
//        Country country = query.getPopulationOf("Asia","Eastern Asia");
//        assertEquals(country.Name, "Eastern Asia");
//        assertEquals(country.Population, 1507328000);
//    }

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

    //TP updated Integration Tests for new capital city query
    //getCapitalCitiesByPopulation()
    @Test
    void getCapitalCityPopulation() {
        ArrayList<City> cities = query.getCapitalCityPopulation();
        City cit = cities.get(0);
        assertEquals(cit.Name,"Seoul");
        assertEquals(cit.Country,"South Korea");
        assertEquals(cit.Population,9981619);
    }

    @Test
    void getCapitalCityPopulation1() {
        ArrayList<City> cities = query.getCapitalCityPopulation("Continent", "Asia");
        City cit = cities.get(0);
        assertEquals(cit.Name,"Seoul");
        assertEquals(cit.Country,"South Korea");
        assertEquals(cit.Population,9981619);
    }

    @Test
    void getCapitalCityPopulation2() {
        ArrayList<City> cities = query.getCapitalCityPopulation("Region","Southeast Asia");
        City cit = cities.get(0);
        assertEquals(cit.Name,"Jakarta");
        assertEquals(cit.Country,"Indonesia");
        assertEquals(cit.Population,9604900);
    }

    // Tom updated Integration Tests for new capital city query
    @Test
    void getTopNCapitalCityPopulation() {
        ArrayList<City> cities = query.getTopNCapitalCityPopulation(1);
        City cit = cities.get(0);
        assertEquals(cit.Name,"Seoul");
        assertEquals(cit.Country,"South Korea");
        assertEquals(cit.Population,9981619);
    }

    @Test
    void getTopNCapitalCityPopulation1() {
        ArrayList<City> citiies = query.getTopNCapitalCityPopulation("Continent", "Asia", 1);
        City cit = cit.get(0);
        assertEquals(cit.Name,"Seoul");
        assertEquals(cit.Country,"South Korea");
        assertEquals(cit.Population,9981619);
    }

    @Test
    void getTopNCapitalCityPopulation2() {
        ArrayList<City> cities = query.getTopNCapitalCityPopulation("Region", "Southeast Asia", 1);
        City cit = cities.get(0);
        assertEquals(cit.Name,"Jakarta");
        assertEquals(cit.Country,"Indonesia");
        assertEquals(cit.Population,9604900);
    }

    @Test
    void largeToSmallCityPopulation() {
        ArrayList<City> cities = query.getCitiesByPopulation();
        City cit = cities.get(0);
        assertEquals(cit.Name,"Mumbai (Bombay)");
        assertEquals(cit.Population,10500000);
    }

    @Test
    void largeToSmallCityPopulation1() {
        ArrayList<City> cities = query.getCitiesByPopulation("Continent", "Asia");
        City cit = cities.get(0);
        assertEquals(cit.Name,"Mumbai (Bombay)");
        assertEquals(cit.Population,10500000);
        assertEquals(cit.Continent,"Asia");
    }

    @Test
    void largeToSmallCityPopulation2() {
        ArrayList<City> cities = query.getCitiesByPopulation("Region", "Southeast Asia");
        City cit = cities.get(0);
        assertEquals(cit.Name, "Jakarta");
        assertEquals(cit.Population, 9604900);
        assertEquals(cit.Region, "Southeast Asia");
    }

    @Test
    void largeToSmallCityPopulation3() {
        ArrayList<City> cities = query.getCitiesByPopulation("District", "Maharashtra");
        City cit = cities.get(0);
        assertEquals(cit.Name, "Mumbai (Bombay)");
        assertEquals(cit.Population, 10500000);
        assertEquals(cit.District, "Maharashtra");
    }

    @Test
    void largeToSmallCityPopulation4() {
        ArrayList<City> cities = query.getCitiesByPopulation("Country", "India");
        City cit = cities.get(0);
        assertEquals(cit.Name, "Mumbai (Bombay)");
        assertEquals(cit.Population, 10500000);
        assertEquals(cit.Country, "India");

    }
}
// tests over
