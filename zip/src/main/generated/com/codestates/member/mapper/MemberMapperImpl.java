package com.codestates.member.mapper;

import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.dto.MemberResponseDto.MemberResponseDtoBuilder;
import com.codestates.member.entity.Member;
import com.codestates.member.entity.Member.MemberBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-10T22:33:43+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        if ( memberPostDto == null ) {
            return null;
        }

        MemberBuilder member = Member.builder();

        member.email( memberPostDto.getEmail() );
        member.name( memberPostDto.getName() );
        member.phone( memberPostDto.getPhone() );

        return member.build();
    }

    @Override
    public MemberResponseDto memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDtoBuilder memberResponseDto = MemberResponseDto.builder();

        if ( member.getMemberId() != null ) {
            memberResponseDto.memberId( member.getMemberId() );
        }
        memberResponseDto.email( member.getEmail() );
        memberResponseDto.name( member.getName() );
        memberResponseDto.phone( member.getPhone() );
        memberResponseDto.memberStatus( member.getMemberStatus() );

        return memberResponseDto.build();
    }

    @Override
    public List<MemberResponseDto> memberToMemberResponseDtos(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberResponseDto> list = new ArrayList<MemberResponseDto>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponseDto( member ) );
        }

        return list;
    }
}
