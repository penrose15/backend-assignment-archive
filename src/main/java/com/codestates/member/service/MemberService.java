package com.codestates.member.service;

import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * V2
 *  - 메서드 구현
 *  - DI 적용
 */
@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        // TODO 이미 존재하는 이메일인지 확인 필요.
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        // TODO should business logic

        // member 객체는 나중에 DB에 업데이트 후, 되돌려 받는 것으로 변경 필요.
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember(long memberId) {
        // TODO should business logic
        return new Member(1, "hgd@gmail.com", "홍길동", "010-1234-5678");
    }

    public List<Member> findMembers() {
        // TODO should business logic

        // TODO member 객체는 나중에 DB에서 조회하는 것으로 변경 필요.
        List<Member> members = List.of(
                new Member(1, "hgd@gmail.com", "홍길동", "010-1234-5678"),
                new Member(2, "lml@gmail.com", "이몽룡", "010-1111-2222")
        );
        return members;
    }

    public void deleteMember(long memberId) {
        // TODO should business logic
    }
}
