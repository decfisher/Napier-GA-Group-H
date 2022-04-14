package com.napier.application.data;

import com.napier.application.logic.Reporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ApplicationReporterTests {
    static Reporter reporter;

    @BeforeAll
    static void init() {
        reporter = new Reporter();
    }

    // Written tests

    @Test
    void givenEmptyCountryArray_whenGeneratingReport_thenReturnFalse() {
        
    }

    @Test
    void givenEmptyCityArray_whenGeneratingReport_thenReturnFalse() {

    }
}
