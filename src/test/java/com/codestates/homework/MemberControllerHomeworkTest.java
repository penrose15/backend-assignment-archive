package com.codestates.homework;

import com.codestates.helper.MemberControllerTestHelper;
import com.codestates.helper.StubData;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerHomeworkTest implements MemberControllerTestHelper {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private ResultActions postResultActions;
    private MemberDto.Post post;
    private MvcResult postResult;

    @BeforeEach
    public void init() throws Exception {
        this.post = (MemberDto.Post) StubData.MockMember.get(HttpMethod.POST);
        String content = gson.toJson(post);
        URI uri = getURI();
        this.postResultActions = mockMvc.perform(postRequestBuilder(uri, content));
    }

    @Test
    void postMemberTest() throws Exception {
        // given
        // when
        // then
        MvcResult result = this.postResultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(post.getName()))
                .andExpect(jsonPath("$.data.phone").value(post.getPhone()))
                .andReturn();

//        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void patchMemberTest() throws Exception {
        // TODO MemberController의 patchMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.

        //given
        long memberId = getResponseMemberId();
        MemberDto.Patch patch = (MemberDto.Patch) StubData.MockMember.get(HttpMethod.PATCH);

        String content = gson.toJson(patch);
        URI uri = getURI(memberId);
        //when
        ResultActions actions = mockMvc.perform(
                patchRequestBuilder(uri,content)
        );

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());


    }

    @Test
    void getMemberTest() throws Exception {
        // TODO MemberController의 getMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        //given


        //when
       long memberId = getResponseMemberId();
       URI uri = getURI(memberId);
        //then
        mockMvc.perform(getRequestBuilder(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(this.post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(this.post.getName()))
                .andExpect(jsonPath("$.data.phone").value(this.post.getPhone()));

    }

    @Test
    void getMembersTest() throws Exception {
        // TODO MemberController의 getMembers() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.

        String content = gson.toJson(new MemberDto.Post("hgd2@gmail.com","홍길동3","010-1111-7777"));
        URI uri = getURI();

        mockMvc.perform(patchRequestBuilder(uri,content));

        //when
        String page = "1"; String size = "10";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        ResultActions actions = mockMvc.perform(getRequestBuilder(uri,queryParams));

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();
        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");

        assertThat(list.size(), is(1));


        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void deleteMemberTest() throws Exception {
        // TODO MemberController의 deleteMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        long memberId = getResponseMemberId();
        URI uri = getURI(memberId);

        mockMvc.perform(deleteRequestBuilder(uri))
                .andExpect(status().isNoContent());

    }

    private long getResponseMemberId() {
        long memberId;
        try {
            String responseMember = this.postResultActions
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
            memberId = JsonPath.parse(responseMember).read("$.data.memberId",Long.class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return memberId;
    }
}
