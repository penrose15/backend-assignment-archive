package exalted.posset.member;

import exalted.posset.member.dto.MemberPatchDto;
import exalted.posset.member.dto.MemberPostDto;
import exalted.posset.member.dto.MemberResponseDto;
import exalted.posset.member.mapper.MemberMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v10/members")
@Validated
public class MemberController {
    private MemberService memberService;
    private MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity createMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        Member member = memberService.createMember(mapper.memberPostToMember(memberPostDto));
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(member), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable("id") long memberId,
                                       @RequestBody MemberPatchDto memberPatchDto) {
         memberPatchDto.setMemberId(memberId);
         Member member = memberService.updateMember(mapper.memberPatchToMember(memberPatchDto));
         return new ResponseEntity<>(mapper.memberToMemberResponseDto(member), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findMember(@PathVariable("id")long memberId) {
        Member member = memberService.getMember(memberId);
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(member), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity findMembers() {
        List<Member> list = memberService.getMembers();
        List<MemberResponseDto> members = mapper.lists(list);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
