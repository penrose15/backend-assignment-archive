package com.codestates.homework;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerHomeworkTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void postMemberTest() throws Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com",
                "홍길동",
                "010-1234-5678");
        String content = gson.toJson(post);


        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/v11/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        MvcResult result = actions
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
//        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com",
//                "홍길동",
//                "010-1234-5678");
//        String contents = gson.toJson(post);
        Member member = new Member("hgd@gmail.com",
                "홍길동",
                "010-1234-5678");
        member.setStamp(new Stamp());
        memberRepository.save(member);

        //given
        MemberDto.Patch patch = new MemberDto.Patch(1L, "홍길동2", "010-1111-2222", Member.MemberStatus.MEMBER_ACTIVE);
        patch.setMemberId(1L);

        String content = gson.toJson(patch);
        //when
        ResultActions actions = mockMvc.perform(
                patch("/v11/members/{member-id}",1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(patch.getMemberId()))
                .andExpect(jsonPath("$.data.name").value(patch.getName()))
                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()))
                .andExpect(jsonPath("$.data.memberStatus").value(patch.getMemberStatus().getStatus()))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());


    }

    @Test
    void getMemberTest() throws Exception {
        // TODO MemberController의 getMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        //given
        Member member = new Member("hgd@gmail.com",
                "홍길동",
                "010-1234-5678");
        member.setStamp(new Stamp());
        memberRepository.save(member);


        //when
        ResultActions actions = mockMvc.perform(
                get("/v11/members/{member-id}",1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)

        );

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(member.getMemberId()))
                .andExpect(jsonPath("$.data.name").value(member.getName()))
                .andExpect(jsonPath("$.data.phone").value(member.getPhone()))
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.memberStatus").value(member.getMemberStatus().getStatus()))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void getMembersTest() throws Exception {
        // TODO MemberController의 getMembers() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.


        for(int i = 0;i< 30;i++) {
            Member member1 = new Member();
            member1.setMemberId((long)i);
            member1.setPhone(String.format("010-1111-11%02d",i));
            member1.setEmail("hgd"+(i)+"@gmail.com");
            member1.setName("홍길동"+i);
            member1.setStamp(new Stamp());
            memberRepository.save(member1);
        }

        //when
        int page = 1;
        int size = 10;
        ResultActions actions = mockMvc.perform(
                get("/v11/members")
                        .param("page",String.valueOf(page))
                        .param("size",String.valueOf(size))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)

        );

        //then
        MvcResult result = actions
                .andExpect(status().isOk()) //pageinfo.totalPages
                .andExpect(jsonPath("$.pageInfo.totalElements").value(29))
                .andExpect(jsonPath("$.pageInfo.totalPages").value(3))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void deleteMemberTest() throws Exception {
        // TODO MemberController의 deleteMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        Member member = new Member("hgd@gmail.com",
                "홍길동",
                "010-1234-5678");
        member.setStamp(new Stamp());
        memberRepository.save(member);

        //when
        ResultActions actions = mockMvc.perform(
                delete("/v11/members/{member-id}",1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult results = actions.andExpect(status().isNoContent())
                .andReturn();

    }
}
