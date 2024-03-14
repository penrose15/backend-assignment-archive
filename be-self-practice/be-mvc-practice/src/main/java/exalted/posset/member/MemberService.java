package exalted.posset.member;

import exalted.posset.exception.BusinessLogicException;
import exalted.posset.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public void verifyMember(long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXIST);
        }
    }
    public Member verifiedMemberById(long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Member member1 =member.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return member1;

    }
    public Member createMember(Member member) {
        verifyMember(member.getMemberId());
        return memberRepository.save(member);
    }
    public Member updateMember(Member member) {
        Member member1 = verifiedMemberById(member.getMemberId());
        Optional.ofNullable(member1.getEmail())
                .ifPresent(email -> member1.setEmail(email));
        Optional.ofNullable(member1.getName())
                .ifPresent(name -> member1.setName(name));
        Optional.ofNullable(member1.getPhone())
                .ifPresent(phone-> member1.setPhone(phone));
        return memberRepository.save(member1);
    }
    public Member getMember(long memberId) {
        Member member1 = verifiedMemberById(memberId);
        return member1;
    }
    public List<Member> getMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    public void deleteMember(long memberId) {
        Member member = verifiedMemberById(memberId);
        memberRepository.delete(member);
    }
}
