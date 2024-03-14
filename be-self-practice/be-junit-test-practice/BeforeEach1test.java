package com.codestates;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BeforeEach1test {
    private static Map<String, String> map;

//    @BeforeAll
//    //@BeforeEach
    public static void mapPut() { //init() 는 초기화 시켜줌
        System.out.println("initialize Subway map");
        map = new HashMap<>();
        map.put("BLT","6000");
        map.put("EGG", "5800");
        map.put("CHICKEN", "6500");
    }

    @AfterEach
    public void aaa() {
        map.clear();
        System.out.println("map clear");
    }

    @DisplayName("@BeforeEach test1")
    @Test
    public void beforeEachTest() {
        mapPut();
        map.put("VEGI", "5500");
        System.out.println(map);
        assertDoesNotThrow(() -> getSubway("VEGI"));
    }

    @DisplayName("@BeforeEach Test2")
    @Test
    public void beforeEachTest2() {
        mapPut();
        System.out.println(map);
        assertDoesNotThrow(() -> getSubway("VEGI"));
    }
    private String getSubway(String menu) {
        return map.get(menu).toUpperCase();
    }
}
