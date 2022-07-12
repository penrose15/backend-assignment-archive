package com.codestates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionTest {

    @DisplayName("Assumpttion test")
    @Test
    public void assumptionTest() {
        assumeTrue(System.getProperty("os.name").startsWith("Windows"));
        System.out.println("execute??");
        assertTrue(processOnlyWindowsTask());
    }//특정 조건에서 선택적인 테스트

    private boolean processOnlyWindowsTask() {
        return true;
    }
}
