package com.template.member;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {
    //CrudRepository<Member, Long> : Member엔티티 클래스 객체에 담긴 데이터를
    //데이터베이스 테이블에 생성/수정 가능하게 만듬
    //Long은 Member의 식별자의 데이터 타입을 의미
    List<Member> findAll();

    //CrudRepository가 save()를 지원해줌
}
