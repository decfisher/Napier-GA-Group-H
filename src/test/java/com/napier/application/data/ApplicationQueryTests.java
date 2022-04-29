package com.napier.application.data;

import com.napier.application.logic.Query;
import com.napier.application.logic.Report;
import com.napier.application.logic.SQLQuery;
import com.napier.application.presentation.App;
import org.junit.jupiter.api.*;

class ApplicationQueryTests {
    static App app;
    static Report report;
    static SQLQuery query;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
        report = new Report(app.getConnection());
        //query = new SQLQuery(app.getConnection());
        SQLQuery query = new SQLQuery(app.getConnection());
    }

    //New Tests here
    @Test
    void testgetCountryPopulation1() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCountryPopulation("", "");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetCountryPopulation2() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCountryPopulation("Continent", "");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetCountryPopulation3() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCountryPopulation("", "Europe");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetCountryPopulation4() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCountryPopulation("", "");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetCountryPopulation5() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCountryPopulation("Continent", "Mars");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetTopNCountryPopulation1() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCountryPopulation(0);
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetTopNCountryPopulation2() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCountryPopulation(0, "", "");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetTopNCountryPopulation3() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCountryPopulation(1, "", "");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetTopNCountryPopulation4() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCountryPopulation(1, "Continent", "");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetTopNCountryPopulation5() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCountryPopulation(1, "", "Europe");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetTopNCountryPopulation6() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCountryPopulation(1, "District", "Europe");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    @Test
    void testGetTopNCountryPopulation7() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCountryPopulation(1, "Continent", "Mars");
        });

        Assertions.assertEquals(null, thrown.getMessage());
    }

    //Unit Tests 2
    @Test
    void testGetCityPopulation1() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCityPopulation("", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetCityPopulation2() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCityPopulation("Continent", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetCityPopulation3() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCityPopulation("Region", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetCityPopulation4() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCityPopulation("Country", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetCityPopulation5() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCityPopulation("District", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetCityPopulation6() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCityPopulation("", "Europe");
        });

        Assertions.assertNull(thrown.getMessage());
    }


    @Test
    void testGetCityPopulation7() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCityPopulation("Continent", "Mars");
        });

        Assertions.assertNull(thrown.getMessage());
    }


    @Test
    void testGetTopNCityPopulation1() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCityPopulation(0);
        });

        Assertions.assertNull(thrown.getMessage());
    }


    @Test
    void testGetTopNCityPopulation2() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCityPopulation(0, "", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetTopNCityPopulation3() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCityPopulation(1, "", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetTopNCityPopulation4() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCityPopulation(1, "Continent", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }


    @Test
    void testGetTopNCityPopulation5() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCityPopulation(1, "", "Europe");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetTopNCityPopulation6() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCityPopulation(1, "District", "Europe");
        });

        Assertions.assertNull(thrown.getMessage());
    }


    @Test
    void testGetTopNCityPopulation7() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCityPopulation(1, "Continent", "Mars");
        });

        Assertions.assertNull(thrown.getMessage());
    }



    @Test
    void testGetCapitalCityPopulation1() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCapitalCityPopulation("","");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetCapitalCityPopulation2() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCapitalCityPopulation("Continent","");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetCapitalCityPopulation3() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCapitalCityPopulation("Region","");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetCapitalCityPopulation4() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCapitalCityPopulation("Country","");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetCapitalCityPopulation5() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCapitalCityPopulation("District","");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetCapitalCityPopulation6() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCapitalCityPopulation("","Europe");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetCapitalCityPopulation7() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getCapitalCityPopulation("Continent","Mars");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetTopNCapitalCityPopulation1() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCapitalCityPopulation(0);
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetTopNCapitalCityPopulation2() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCapitalCityPopulation(0, "", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetTopNCapitalCityPopulation3() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCapitalCityPopulation(1, "", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetTopNCapitalCityPopulation4() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCapitalCityPopulation(1, "Continent", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetTopNCapitalCityPopulation5() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCapitalCityPopulation(1, "", "Europe");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetTopNCapitalCityPopulation6() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCapitalCityPopulation(1, "District", "Europe");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetTopNCapitalCityPopulation7() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getTopNCapitalCityPopulation(1, "Continent", "Mars");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetPopulationInAndOutOfCities1() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationInAndOutOfCities("");
        });

        Assertions.assertNull(thrown.getMessage());
    }

    @Test
    void testGetPopulationInAndOutOfCities2() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationInAndOutOfCities("Mars");
        });

        Assertions.assertNull(thrown.getMessage());
    }


    @Test
    void testGetPopulationOf1() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("","");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf2() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("Continent", "");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf3() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("", "Europe");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf4() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("District", "Europe");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf5() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("Continent", "Mars");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf6() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("","","");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf7() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("Region","","");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf8() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("Region","Asia","");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf9() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("","Asia","Middle East");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf10() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("","","Middle East");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf11() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("Region","","Middle East");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf12() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("Region","Asia","Middle Earth");
        });

        Assertions.assertNull(thrown.getMessage());
    }
    @Test
    void testGetPopulationOf13() {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            query.getPopulationOf("Region","Middle East","Asia");
        });

        Assertions.assertNull(thrown.getMessage());
    }


    //Written tests
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