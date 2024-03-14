package exalted.posset.member.mapper;

import exalted.posset.member.Member;
import exalted.posset.member.dto.MemberPatchDto;
import exalted.posset.member.dto.MemberPostDto;
import exalted.posset.member.dto.MemberResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberPostDto memberPostDto);
    Member memberPatchToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);

    List<MemberResponseDto> lists(List<Member> members);
}
