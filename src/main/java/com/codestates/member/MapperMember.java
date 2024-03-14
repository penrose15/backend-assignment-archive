/*
package com.codestates.member;

import org.springframework.stereotype.Component;
//수작업으로 만든 Mapper
@Component //MemberMapper를 Spring Bean으로 등록하기 위해 추가, 등록된 Bean은 MemberController에서 사용됨
public class MapperMember {

    //MemberPostDto를 Member로 변환
    public Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        return new Member(0L,
                memberPostDto.getEmail(),
                memberPostDto.getName(),
                memberPostDto.getPhone());
    }
    //MemberPatchDto를 Member로 변환
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {
        return new Member(memberPatchDto.getMemberId(),
                null, memberPatchDto.getName(),
                memberPatchDto.getPhone());
    }
    //Member를 MemberResponseDto로 변환
    public MemberResponseDto memberToMemberResponseDto(Member member) {
        return new MemberResponseDto (member.getMemberId(),
                member.getEmail(),
                member.getName(),
                member.getPhone());
    }

}
//MemberController에서 사용하는 DTO 클래스와 Member간에 서로 타입을 변환해주는 Mapper클래스*/
