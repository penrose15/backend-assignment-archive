package jdbc.gotohell.coffee;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    @Query("SELECT * FROM COFFEE WHERE COFFEE_ID = :coffeeId")
    Optional<Coffee> findByCoffee(Long coffeeId);
    //:coffeeId는 coffeeId 변수 값이 채워지는 동적 쿼리 파라미터이다.
}
