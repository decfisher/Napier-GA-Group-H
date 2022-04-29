package com.napier.application.data;

import com.napier.application.logic.SQLQuery;
import com.napier.application.presentation.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;
    static SQLQuery query;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
        query = new SQLQuery(app.getConnection());
    }

    @Test
    void getCountryPopulation_noParameters() {
        ArrayList<Country> countries = query.getCountryPopulation();
        Country country = countries.get(0);
        assertEquals(country.Code, "CHN");
        assertEquals(country.Name,"China");
        assertEquals(country.Continent, "Asia");
        assertEquals(country.Region, "Eastern Asia");
        assertEquals(country.Population,1277558000);
        assertEquals(country.Capital, "Peking");
    }

    @Test
    void getCountryPopulation_ContinentParameter() {
        ArrayList<Country> countries = query.getCountryPopulation("Continent", "Asia");
        Country country = countries.get(0);
        assertEquals(country.Code, "KOR");
        assertEquals(country.Name,"Seoul");
        assertEquals(country.Continent, "Asia");
        assertEquals(country.Region, "Eastern Asia");
        assertEquals(country.Population,9981619);
        assertEquals(country.Capital, "Seoul");
    }

    @Test
    void getCountryPopulation_RegionParameter() {
        ArrayList<Country> countries = query.getCountryPopulation("Region", "Central Africa");
        Country country = countries.get(0);
        assertEquals(country.Code, "COD");
        assertEquals(country.Name,"Kinshasa");
        assertEquals(country.Continent, "Africa");
        assertEquals(country.Region, "Central Africa");
        assertEquals(country.Population,5064000);
        assertEquals(country.Capital, "Kinshasa");
    }

    @Test
    void getTopNCountryPopulation_oneParameter() {
        ArrayList<Country> countries = query.getTopNCountryPopulation(5);
        Country country = countries.get(0);
        assertEquals(country.Code, "KOR");
        assertEquals(country.Name,"Seoul");
        assertEquals(country.Continent, "Asia");
        assertEquals(country.Region, "Eastern Asia");
        assertEquals(country.Population,9981619);
        assertEquals(country.Capital, "Seoul");
    }

    @Test
    void getTopNCountryPopulation_ContinentParameter() {
        ArrayList<Country> countries = query.getTopNCountryPopulation(5,"Continent","Europe");
        Country country = countries.get(0);
        assertEquals(country.Code, "RUS");
        assertEquals(country.Name,"Moscow");
        assertEquals(country.Continent, "Europe");
        assertEquals(country.Region, "Eastern Europe");
        assertEquals(country.Population,8389200);
        assertEquals(country.Capital, "Moscow");
    }
    @Test
    void getTopNCountryPopulation_RegionParameter() {
        ArrayList<Country> countries = query.getTopNCountryPopulation(5,"Region","Eastern Africa");
        Country country = countries.get(0);
        assertEquals(country.Code, "ETH");
        assertEquals(country.Name,"Addis Abeba");
        assertEquals(country.Continent, "Africa");
        assertEquals(country.Region, "Eastern Africa");
        assertEquals(country.Population,2495000);
        assertEquals(country.Capital, "Addis Abeba");
    }

//    @Test
//    void getCountriesByPopulation1param()
//    {
//        ArrayList<Country> countries = query.getCountriesByPopulation("Asia");
//        Country cou = countries.get(0);
//        assertEquals(cou.Name,"China");
//        assertEquals(cou.Population,1277558000);
//        assertEquals(cou.Continent,"Asia");
//
//    }
//
//    @Test
//    void getCountriesByPopulation2param()
//    {
//        ArrayList<Country> countries = query.getCountriesByPopulation("Asia","Eastern Asia");
//        Country cou = countries.get(0);
//        assertEquals(cou.Name,"China");
//        assertEquals(cou.Population,1277558000);
//        assertEquals(cou.Continent,"Asia");
//        assertEquals(cou.Region,"Eastern Asia");
//
//    }
//
//    @Test
//    void getPopulationOf3param()
//    {
//        Country country = query.getPopulationOf("Asia","Eastern Asia","China");
//        assertEquals(country.Name, "China");
//        assertEquals(country.Population, 1277558000);
//    }
//
//    @Test
//    void getPopulationOf4param()
//    {
//        Country country = query.getPopulationOf("Asia","Eastern Asia","China","Peking");
//        assertEquals(country.Name, "Peking");
//        assertEquals(country.Population, 7472000);
//    }
//
//    @Test
//    void getPopulationOf5param()
//    {
//        Country country = query.getPopulationOf("Asia","Eastern Asia","China","Peking","Peking");
//        assertEquals(country.Name, "Peking");
//        assertEquals(country.Population, 7472000);
//    }
//
//    //James Integration Tests for story #5
//    //getCapitalCitiesByPopulation()
//    @Test
//    void getCapitalCitiesByPopulation() {
//        ArrayList<Country> countries = query.getCapitalCitiesByPopulation();
//        Country cou = countries.get(0);
//        assertEquals(cou.Name,"Seoul");
//        assertEquals(cou.Population,9981619);
//    }
//
//    @Test
//    void getCapitalCitiesByPopulation1() {
//        ArrayList<Country> countries = query.getCapitalCitiesByPopulation("Asia");
//        Country cou = countries.get(0);
//        assertEquals(cou.Name,"Seoul");
//        assertEquals(cou.Population,9981619);
//        assertEquals(cou.Continent,"Asia");
//    }
//
//    @Test
//    void getCapitalCitiesByPopulation2() {
//        ArrayList<Country> countries = query.getCapitalCitiesByPopulation("Asia","Southeast Asia");
//        Country cou = countries.get(0);
//        assertEquals(cou.Name,"Jakarta");
//        assertEquals(cou.Population,9604900);
//        assertEquals(cou.Continent,"Asia");
//        assertEquals(cou.Region,"Southeast Asia");
//    }
//
//    // Integration tests TP final queries
//        @Test
//    void topNPopulatedCapitalCities() {
//        ArrayList<Country> countries = query.getTopNCapitalCityPopulation(1);
//        Country cou = countries.get(0);
//        assertEquals(cou.Name,"Seoul");
//        assertEquals(cou.Population,9981619);
//    }
//
//    @Test
//    void topNPopulatedCapitalCities1() {
//        ArrayList<Country> countries = query.getTopNCapitalCityPopulation("Asia",1);
//        Country cou = countries.get(0);
//        assertEquals(cou.Name,"Seoul");
//        assertEquals(cou.Population,9981619);
//        assertEquals(cou.Continent,"Asia");
//    }
//
//    @Test
//    void topNPopulatedCapitalCities2() {
//        ArrayList<Country> countries = query.getTopNCapitalCityPopulation("Asia", "Southeast Asia", 1);
//        Country cou = countries.get(0);
//        assertEquals(cou.Name,"Jakarta");
//        assertEquals(cou.Population,9604900);
//        assertEquals(cou.Continent,"Asia");
//        assertEquals(cou.Region,"Southeast Asia");
//    }
//
//    @Test
//    void largeToSmallCityPopulation() {
//        ArrayList<City> cities = query.getCitiesByPopulation();
//        City cit = cities.get(0);
//        assertEquals(cit.Name,"Mumbai (Bombay)");
//        assertEquals(cit.Population,10500000);
//    }
//
//    @Test
//    void largeToSmallCityPopulation1() {
//        ArrayList<City> cities = query.getCitiesByPopulation("Continent", "Asia");
//        City cit = cities.get(0);
//        assertEquals(cit.Name,"Mumbai (Bombay)");
//        assertEquals(cit.Population,10500000);
//        assertEquals(cit.Continent,"Asia");
//    }
//
//    @Test
//    void largeToSmallCityPopulation2() {
//        ArrayList<City> cities = query.getCitiesByPopulation("Region", "Southeast Asia");
//        City cit = cities.get(0);
//        assertEquals(cit.Name, "Jakarta");
//        assertEquals(cit.Population, 9604900);
//        assertEquals(cit.Region, "Southeast Asia");
//    }
//
//    @Test
//    void largeToSmallCityPopulation3() {
//        ArrayList<City> cities = query.getCitiesByPopulation("District", "Maharashtra");
//        City cit = cities.get(0);
//        assertEquals(cit.Name, "Mumbai (Bombay)");
//        assertEquals(cit.Population, 10500000);
//        assertEquals(cit.District, "Maharashtra");
//    }
//
//    @Test
//    void largeToSmallCityPopulation4() {
//        ArrayList<City> cities = query.getCitiesByPopulation("Country", "India");
//        City cit = cities.get(0);
//        assertEquals(cit.Name, "Mumbai (Bombay)");
//        assertEquals(cit.Population, 10500000);
//        assertEquals(cit.Country, "India");
//
//    }
}
// tests over
