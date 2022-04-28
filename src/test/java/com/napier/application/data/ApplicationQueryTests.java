package com.napier.application.data;

import com.napier.application.logic.Query;
import com.napier.application.logic.Report;
import com.napier.application.presentation.App;
import org.junit.jupiter.api.*;

class ApplicationQueryTests {
//    static App app;
//    static Report report;
//    static Query query;
//
//    @BeforeAll
//    static void init() {
//        app = new App();
//        app.connect("localhost:33060", 30000);
//        report = new Report(app.getConnection());
//        query = new Query(app.getConnection());
//    }
//
//    //Written tests
//
//    @Test
//    void testTopNPopulatedCapitalCities() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCapitalCityPopulation(0);
//        });
//
//        Assertions.assertEquals("N must be greater than 0", thrown.getMessage());
//    }
//
//    @Test
//    void testTopNPopulatedCapitalCities_Continent1() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCapitalCityPopulation(null,0);
//        });
//
//        Assertions.assertEquals("N must be greater than 0", thrown.getMessage());
//    }
//
//    @Test
//    void testTopNPopulatedCapitalCities_Continent2() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCapitalCityPopulation(null,1);
//        });
//
//        Assertions.assertEquals("Continent must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testTopNPopulatedCapitalCities_Region1() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCapitalCityPopulation(null,null,0);
//        });
//
//        Assertions.assertEquals("N must be greater than 0", thrown.getMessage());
//    }
//
//    @Test
//    void testTopNPopulatedCapitalCities_Region2() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCapitalCityPopulation("Continent", null,1);
//        });
//
//        Assertions.assertEquals("Continent or region must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testTopNPopulatedCapitalCities_Region3() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCapitalCityPopulation(null,"Region",1);
//        });
//
//        Assertions.assertEquals("Continent or region must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetTopNCountryPopulation() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCountryPopulation(0);
//        });
//
//        Assertions.assertEquals("N must be greater than 0", thrown.getMessage());
//    }
//
//    @Test
//    void testTopNCountryPopulation() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCountryPopulation(0,null, null);
//        });
//
//        Assertions.assertEquals("N must be greater than 0", thrown.getMessage());
//    }
//
//    @Test
//    void testTopNCountryPopulation2() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCountryPopulation(1,"queryType", null);
//        });
//
//        Assertions.assertEquals("queryType or name must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testTopNCountryPopulation3() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCountryPopulation(1,null, "name");
//        });
//
//        Assertions.assertEquals("queryType or name must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testTopNCountryPopulation4() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getTopNCountryPopulation(1,null, null);
//        });
//
//        Assertions.assertEquals("queryType or name must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetCountriesByPopulation_Continent() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCountriesByPopulation(null);
//        });
//
//        Assertions.assertEquals("Continent must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetCountriesByPopulation_Region1() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCountriesByPopulation(null,null);
//        });
//
//        Assertions.assertEquals("Continent or Region must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetCountriesByPopulation_Region2() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCountriesByPopulation("Continent",null);
//        });
//
//        Assertions.assertEquals("Continent or Region must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetCountriesByPopulation_Region3() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCountriesByPopulation(null,"Region");
//        });
//
//        Assertions.assertEquals("Continent or Region must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetCountry() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCountry(null);
//        });
//
//        Assertions.assertEquals("code must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetCapitalCitiesByPopulation_Continent() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCapitalCitiesByPopulation(null);
//        });
//
//        Assertions.assertEquals("Continent must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetCapitalCitiesByPopulation_Region1() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCapitalCitiesByPopulation(null,null);
//        });
//
//        Assertions.assertEquals("Continent or Region must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetCapitalCitiesByPopulation_Region2() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCapitalCitiesByPopulation("Continent",null);
//        });
//
//        Assertions.assertEquals("Continent or Region must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetCapitalCitiesByPopulation_Region3() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCapitalCitiesByPopulation(null,"Region");
//        });
//
//        Assertions.assertEquals("Continent or Region must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testLargeToSmallCityPopulation1() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCitiesByPopulation(null,null);
//        });
//
//        Assertions.assertEquals("queryType or name must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testLargeToSmallCityPopulation2() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCitiesByPopulation("queryType",null);
//        });
//
//        Assertions.assertEquals("queryType or name must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testLargeToSmallCityPopulation3() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getCitiesByPopulation(null,"name");
//        });
//
//        Assertions.assertEquals("queryType or name must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetPopulation() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getPopulation(null);
//        });
//
//        Assertions.assertEquals("queryType must be specified", thrown.getMessage());
//    }
//
//    @Test
//    void testGetPopulationOf_Continent() {
//        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
//            query.getPopulationOf(null);
//        });
//
//        Assertions.assertEquals("Continent must be specified", thrown.getMessage());
//    }
}