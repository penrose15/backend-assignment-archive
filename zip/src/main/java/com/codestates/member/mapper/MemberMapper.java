package com.codestates.member.mapper;

import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);

    default Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {
        Member member = new Member();
        member.setMemberId(memberPatchDto.getMemberId());
        member.setEmail(memberPatchDto.getEmail());
        member.setPhone(memberPatchDto.getPhone());
        member.setName(memberPatchDto.getName());
        member.setMemberStatus(memberPatchDto.getMemberStatus());

        return member;
    }

    MemberResponseDto memberToMemberResponseDto(Member member);

    List<MemberResponseDto> memberToMemberResponseDtos(List<Member> members);


}
