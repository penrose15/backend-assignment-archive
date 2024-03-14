package exalted.posset.coffee;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    public Optional<Coffee> findByCoffeeCode(String coffeeCode);
}
