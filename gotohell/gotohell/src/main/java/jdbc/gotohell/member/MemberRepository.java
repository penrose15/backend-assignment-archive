package jdbc.gotohell.member;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    //JDBC 내부적으로 java리플렉션 기술과 proxy기술을 사용하여 MemberRepository 인터페이스의 구현 클래스 객체를 생성
}
