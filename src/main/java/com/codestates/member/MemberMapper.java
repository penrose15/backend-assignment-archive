package com.codestates.member;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") //알아서 스프링 빈으로 등록시켜줌
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
}
