package jdbc.gotohell.member;

import jdbc.gotohell.exception.BusinessLogicException;
import jdbc.gotohell.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member verifyExistEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
        return member.get();
    }
    public Member findVerifyMember(long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member1 = findMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return member1;
    }
    public Member createMember(Member member) {
        member = verifyExistEmail(member.getEmail());
        return memberRepository.save(member);
    }
    public Member updateMember(Member member) {
        Member findMember = findVerifyMember(member.getMemberId());
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findMember.setEmail(email));
        return memberRepository.save(findMember);

    }
    public Member findMember(long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        member.orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return member.get();

    }
    public List<Member> findMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    public void deleteMember(long memberId) {
        Member member = findVerifyMember(memberId);
        memberRepository.delete(member);
    }
}
