package com.codestates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AssertionNullHamcrestTest {

    @DisplayName("AssertNull() Test")
    @Test
    public void assertNotNullTest() {
        String currencyName = getCryptoCurrency("ETH");

        assertThat(currencyName, is(notNullValue()));
        //assertNotNull(currencyName, "shouldNotBeNull");
    }

    public String getCryptoCurrency(String unit) {
        return CryptoCurrency.map.get(unit);
    }
}
