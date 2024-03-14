package com.codestates.member.service;

import com.codestates.bussinessLogicException.BusinessLogicException;
import com.codestates.bussinessLogicException.ExceptionCode;
import com.codestates.member.MemberRepository;
import com.codestates.member.entity.Member;
import com.codestates.member.entity.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }
    @Transactional(readOnly = true)
    public void findVerifiedEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if(findMember.isPresent()) throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_EXIST);
    }

    public Member createMember(Member member) { //멤버 저장
        findVerifiedEmail(member.getEmail());
        Member saveMember =  memberRepository.save(member);
        createStamp(saveMember.getMemberId());

        return saveMember;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Member updateMember(Member member) {
        long memberId = member.getMemberId();
        Member findmembers = findVerifiedMember(memberId);

        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findmembers.setEmail(email));
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findmembers.setName(name));
        Optional.ofNullable(member.getEmail())
                .ifPresent(phone -> findmembers.setPhone(phone));
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(status -> findmembers.setMemberStatus(status));
        findmembers.setModifiedAt(LocalDateTime.now());

        return memberRepository.save(findmembers);
    }

    public Member findMemberById (long memberId) {
        return findVerifiedMember(memberId);
    }

    @Transactional(readOnly = true)
    public Page<Member> findMembers(int page, int size) {//page 단위로 멤버목록 반환
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.Direction.DESC,"memberId");

        return memberRepository.findAll(pageRequest);
    }

    public void deleteMember(long memberId) {
        Member member = findVerifiedMember(memberId);
        memberRepository.delete(member);
    }

    public void createStamp(long memberId) {
        Member member = findVerifiedMember(memberId);
        Stamp stamp = new Stamp();
        member.setStamp(stamp);
        stamp.setStampCount(0);
        memberRepository.save(member);
    }

    public int findStampCount(long memberId) {
        Member member = findVerifiedMember(memberId);
        if(member.getStamp().getStampCount() == null) {
            member.getStamp().setStampCount(0);
            memberRepository.save(member);
        }
        return member.getStamp().getStampCount();
    }
}
