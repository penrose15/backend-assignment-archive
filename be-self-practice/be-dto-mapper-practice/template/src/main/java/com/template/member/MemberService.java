package com.template.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    List<Member> list = new ArrayList<>();

    public Member saveMember(Member member) {
        member = memberRepository.save(member);

        return member;
    }
    public Optional<Member> findById(long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member;
    }
//    public Member updateMember(long memberId, Member member) {
//
//    }

    public List<Member> findAll() {
        list = memberRepository.findAll();
//        Iterable<Member> member = memberRepository.findAll();
//        if(member.iterator().hasNext()) {
//            list.add(member.iterator().next());
//        }
        return list;
    }
    public void deleteById(long memberId) {
        memberRepository.deleteById(memberId);
        log.info("{}삭제",memberId);
    }
    public void deleteAll() {
        memberRepository.deleteAll();
        log.info("전체 삭제");
    }
}
