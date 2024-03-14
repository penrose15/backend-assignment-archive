package com.codestates.member;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-27T22:53:47+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        if ( memberPostDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( memberPostDto.getMemberId() );
        member.setEmail( memberPostDto.getEmail() );
        member.setName( memberPostDto.getName() );
        member.setPhone( memberPostDto.getPhone() );

        return member;
    }

    @Override
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {
        if ( memberPatchDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( memberPatchDto.getMemberId() );
        member.setEmail( memberPatchDto.getEmail() );
        member.setName( memberPatchDto.getName() );
        member.setPhone( memberPatchDto.getPhone() );

        return member;
    }

    @Override
    public MemberResponseDto memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        long memberId = 0L;
        String email = null;
        String name = null;
        String phone = null;

        memberId = member.getMemberId();
        email = member.getEmail();
        name = member.getName();
        phone = member.getPhone();

        MemberResponseDto memberResponseDto = new MemberResponseDto( memberId, email, name, phone );

        return memberResponseDto;
    }
}
