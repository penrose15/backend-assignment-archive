package com.template.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@Validated
@RequestMapping("/member")
@RestController
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;


    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping
    public ResponseEntity createMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        Member member = memberMapper.memberPostDtoToMember(memberPostDto);
        member = memberService.saveMember(member);
        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(member), HttpStatus.CREATED);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity updateMember(@PathVariable("memberId") @Min(1L) long memberId,
                                       @Valid @RequestBody MemberPatchDto memberPatchDto) {
        Member member  = memberMapper.memberPatchDtoToMember(memberPatchDto);
        member = memberService.saveMember(member);
        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(member), HttpStatus.OK);
    }
    @GetMapping("/{memberId}")
    public ResponseEntity findMember(@PathVariable("memberId") @Min(1L) long memberId) {
        Optional<Member> member = memberService.findById(memberId);
        Member member1 = member.get();
        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(member1),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity findMembers() {
        List<Member> list = memberService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") long memberId) {
        memberService.deleteById(memberId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
