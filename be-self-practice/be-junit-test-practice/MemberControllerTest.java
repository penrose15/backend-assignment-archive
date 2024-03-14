package com.codestates;

//import com.google.gson.Gson;
import com.codestates.member.dto.MemberDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    void postMemberTest() throws Exception {
        //given
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com",
                                                    "홍길동",
                                                    "010-1234-5678");

        String content = gson.toJson(post);
        //Gson이라는 Json변환 라이브러리를 이용해서 MemberDto.post 를 JSON 포맷으로 변환 해준다.


        //when
        ResultActions actions = //http Request에 대한 정보
                mockMvc.perform(
                        post("/v11/members") //requestURL 설정
                                .accept(MediaType.APPLICATION_JSON) //리턴 받을 응답 데이터 타입으로 JSON 설정
                                .contentType(MediaType.APPLICATION_JSON) // 서버쪽에서 처리 가능한 Content Type로 Json타입 설정
                                .content(content) // requestBody 데이터를 설정
                );
        //then
        MvcResult result = actions
                .andExpect(status().isCreated()) //입력된 Matcher로 예상되는 기대 결과/ isCreated()는 201 Created
                .andReturn(); //response 데이터 확인 가능
    }
}
