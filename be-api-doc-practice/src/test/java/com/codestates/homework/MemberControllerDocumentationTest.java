package com.codestates.homework;

import com.codestates.member.controller.MemberController;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.codestates.stamp.Stamp;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.print.attribute.standard.Media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codestates.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerDocumentationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;
    

    @Test
    public void getMemberTest() throws Exception {
        // TODO 여기에 MemberController의 getMember() 핸들러 메서드 API 스펙 정보를 포함하는 테스트 케이스를 작성 하세요.
        //given
        Member member = new Member("hgd@gmail.com","홍길동","010-1111-1111");
        member.setMemberId(1L);
        member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
        member.setStamp(new Stamp());
        Long memberId = member.getMemberId();

        MemberDto.response response =
                new MemberDto.response(1L,
                        "hgd@gmail.com",
                        "홍길동",
                        "010-1111-1111",
                        Member.MemberStatus.MEMBER_ACTIVE,
                        new Stamp());
        given(memberService.findMember(Mockito.any(Long.class))).willReturn(new Member());

        given(mapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/v11/members/{member-id}",memberId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(member.getMemberId()))
                .andExpect(jsonPath("$.data.name").value(member.getName()))
                .andExpect(jsonPath("$.data.phone").value(member.getPhone()))
                .andExpect(jsonPath("$.data.memberStatus").value(member.getMemberStatus().getStatus()))
                .andDo(document("get-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data.phone").type(JsonFieldType.STRING).description("휴대폰 번호"),
                                        fieldWithPath("data.memberStatus").type(JsonFieldType.STRING).description("회원 상태 : 활동중 / 휴면 상태 / 탈퇴 상태"),
                                        fieldWithPath("data.stamp").type(JsonFieldType.NUMBER).description("스탬프 갯수")
                                )
                        )
                        ));

    }

    @Test
    public void getMembersTest() throws Exception {
        // TODO 여기에 MemberController의 getMembers() 핸들러 메서드 API 스펙 정보를 포함하는 테스트 케이스를 작성 하세요.

        //given
        List<Member> list = new ArrayList<>();
        for(int i = 0; i<10;i++) {
            Member member =
                    new Member(
                            String.format("hgd%d@gmail.com",i),
                            String.format("홍길동%d",1),
                            String.format("010-1111-11%02d",i));
            member.setMemberId((long)i);
            member.setStamp(new Stamp());
            list.add(member);
        }


        Pageable pageable = PageRequest.of(0, 10, Sort.by("memberId").descending());
        Page<Member> page = new PageImpl<>(list,pageable,10L);

        List<MemberDto.response> responses = new ArrayList<>();
        for(int i = 0; i<10;i++) {
            MemberDto.response response =
                    new MemberDto.response(i,
                            String.format("hgd%d@gmail.com",i),
                            String.format("홍길동%d",1),
                            String.format("010-1111-11%02d",i),
                            Member.MemberStatus.MEMBER_ACTIVE,
                            new Stamp());
            responses.add(response);
        }
        Page<MemberDto.response> pageResponse = new PageImpl<>(responses,pageable,10L);

        given(memberService.findMembers(Mockito.any(Integer.class), Mockito.any(Integer.class))).willReturn(page);

        given(mapper.membersToMemberResponses(Mockito.anyList())).willReturn(pageResponse.getContent());

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/v11/members")
                                .param("page","1")
                                .param("size","10")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.pageInfo.page").value(1))
                .andExpect(jsonPath("$.pageInfo.size").value(10))
                .andExpect(jsonPath("$.pageInfo.totalElements").value(10))
                .andExpect(jsonPath("$.pageInfo.totalPages").value(1))
                .andDo(document("get-members",
                        getResponsePreProcessor(),
                        requestParameters(
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("크기")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("회원 정보"),
                                        fieldWithPath("data.[]memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.[]email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("data.[]name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data.[]phone").type(JsonFieldType.STRING).description("전화번호"),
                                        fieldWithPath("data.[]memberStatus").type(JsonFieldType.STRING).description("회원 상태 : 활동중 / 휴면 상태 / 탈퇴 상태"),
                                        fieldWithPath("data.[]stamp").type(JsonFieldType.NUMBER).description("스탬프 갯수"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("page"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("size"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("totalElements"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("totalPages")
                                )
                        )
                        ));
    }


    @Test
    public void deleteMemberTest() throws Exception {
        // TODO 여기에 MemberController의 deleteMember() 핸들러 메서드 API 스펙 정보를 포함하는 테스트 케이스를 작성 하세요.

        //given

        given(memberService.findVerifiedMember(Mockito.any(Long.class))).willReturn(new Member());
        doNothing().when(memberService).deleteMember(Mockito.anyLong());

        //when
        ResultActions actions = mockMvc.perform(
                delete("/v11/members/{member-id}",1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isNoContent())
                .andDo(document("delete-member",
                                getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        )
                        ));

    }
}
