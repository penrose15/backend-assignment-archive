package com.codestates;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTestDefaultStructure {

    //Controller 테스트용 테스트 클래스 기본 구조

   @Autowired
    private MockMvc mockMvc;

    @Test
    public void postMemberTest() {
        //given

        //when

        //then
    }
}
