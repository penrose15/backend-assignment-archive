package com.jwt.repository;

import com.jwt.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByUsername(String username);

}
