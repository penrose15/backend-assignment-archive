package exalted.posset.coffee;

import exalted.posset.exception.BusinessLogicException;
import exalted.posset.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }
    public void verifiedCoffeeCode(String coffeeCode) {
        Optional<Coffee> findCoffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        if(findCoffee.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.COFFEECODE_EXIST);
        }
    }
    public Coffee verifyById(long coffeeId) {
        Optional<Coffee> findCoffee = coffeeRepository.findById(coffeeId);
        Coffee a = findCoffee.orElseThrow(()-> new BusinessLogicException(ExceptionCode.COFFEEID_NOT_FOUND));
        return a;
    }
    public Coffee verifyCode(Coffee coffee) {
        Optional<Coffee> findCoffee = coffeeRepository.findByCoffeeCode(coffee.getCoffeeCode());
        Coffee result = findCoffee.orElseThrow(()->new BusinessLogicException(ExceptionCode.COFFEECODE_NOT_FOUND));
        return result;
    }
    public Coffee createCoffee(Coffee coffee) {
        verifiedCoffeeCode(coffee.getCoffeeCode());
        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(Coffee coffee) {
        Coffee findCoffee = verifyCode(coffee);
        Optional.ofNullable(findCoffee.getKorName()).ifPresent(
                c -> findCoffee.setKorName(c));
        Optional.ofNullable(findCoffee.getEngName()).ifPresent(
                c -> findCoffee.setEngName(c));
        return findCoffee;
    }
    public Coffee getCoffee(long coffeeId) {
        Coffee coffee1 = verifyById(coffeeId);
        return coffee1;
    }
    public List<Coffee> getCoffees() {
        return (List<Coffee>) coffeeRepository.findAll();
    }

    public void deleteCoffee(long coffeeId) {
        Coffee coffee1 = verifyById(coffeeId);
        coffeeRepository.delete(coffee1);
    }
}
