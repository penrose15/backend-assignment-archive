package com.email;


import com.email.event.MemberRegistrationApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private final ApplicationEventPublisher publisher;

    public MemberService(MemberRepository memberRepository, ApplicationEventPublisher publisher) {
        this.memberRepository = memberRepository;
        this.publisher = publisher;
    }

    public Member createMember(Member member) {
        Member savedMember =  memberRepository.save(member);
        publisher.publishEvent(new MemberRegistrationApplicationEvent(this,savedMember));

        return savedMember;

    }

    public Member getMember(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        Member findMember = optionalMember.orElseThrow(
                () ->new NoSuchElementException("존재하지 않는 멤버입니다.")
        );
        return findMember;
    }

    public void deleteMember(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        Member findMember = optionalMember.orElseThrow(
                () ->new NoSuchElementException("존재하지 않는 멤버입니다.")
        );
        memberRepository.delete(findMember);
    }

}
