package com.codestates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HellHamcrestTest {

    @DisplayName("Hello Junit Test using hamcrest")
    @Test
    public void assertionTest1() {
        String expected = "Hello, Junit";
        String actual = "Hello, unit";

        assertThat(actual, is(equalTo(expected)));
    }
}
