package com.example.jwtAndOAuth2.member.repository;

import com.example.jwtAndOAuth2.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("select m.refreshToken from Member m where m.id = :id")
    String getRefreshTokenById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Member m set m.refreshToken =:token where m.id =:id ")
    void updateRefreshToken(@Param("id") Long id, @Param("token") String token);
}
