package com.codestates;

import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest //MemberRepository의 기능을 정상적으로 사용하기 위한 Configuration을 스프링이 자동으로 추가
//@Transactional 에너테이션이 포함되어 있어 하나의 테스트케이스 실행 종료시 자동적으로 롤백해줌
public class DataAccesssLayerTest {

    @Autowired
    private MemberRepository memberRepository; //DI

//    @AfterEach
//    public void clear() {
//        memberRepository.deleteAll();
//    }

    @Test
    public void saveMemberTest() {
        //given
        Member member = new Member();
        member.setEmail("hgd@gmail.com");
        member.setName("홍길동");
        member.setPhone("010-4444-8888");

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertNotNull(savedMember);
        assertTrue(member.getEmail().equals(savedMember.getEmail()));
        assertTrue(member.getName().equals(savedMember.getName()));
        assertTrue(member.getPhone().equals(savedMember.getPhone()));

    }

    @Test
    public void findByEmailTest() {
        //given
        Member member = new Member();
        member.setEmail("hgd@gmail.com");
        member.setName("홍길동");
        member.setPhone("010-1111-6666");

        //when
        memberRepository.save(member); //저장이 잘 되는지 확인
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        assertTrue(findMember.isPresent()); //null인지 검증
        assertTrue(findMember.get().getEmail().equals(member.getEmail()));
        //조회한 회원의 이메일 주소와 테스트 데이터의 이메일과 일치하는지 검증
    }
}
