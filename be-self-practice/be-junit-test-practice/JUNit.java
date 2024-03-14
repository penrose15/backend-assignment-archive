package com.codestates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class JUNit {

    @DisplayName("Hello Junit Test")
    @Test
    public void test1() {
        String expected = "hello world";
        String actual = "hell world";

        assertEquals(expected, actual);

    }

    @Test
    public void test2() {
        String currencyName = getCryptoCurrency("BLT");
        assertNotNull(currencyName,"should Not be null");
    }
    private String getCryptoCurrency(String unit) {
        return CryptoCurrency.map.get(unit);
    }


    @DisplayName("throws NullPointerException when map.get()")
    @Test
    public void test3() {
        assertThrows(NullPointerException.class,() -> getCryptoCurrency("luna"));

    }
    @DisplayName("throws NullPointException when map.get()")
    @Test
    public void assertionThrowExceptionTest() {
        Throwable actualException = assertThrows(NullPointerException.class,
                () -> getCryptoCurrency("XRP"));
        assertThat(actualException.getCause(), is(equalTo(null)));
    }


}
