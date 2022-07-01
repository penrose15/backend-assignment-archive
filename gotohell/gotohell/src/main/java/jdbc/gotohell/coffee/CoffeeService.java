package jdbc.gotohell.coffee;

import jdbc.gotohell.exception.BusinessLogicException;
import jdbc.gotohell.exception.ExceptionCode;
import jdbc.gotohell.order.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    private CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }
    public void verifyExistCoffee(String coffeeCode) {
        Optional<Coffee> coffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        coffee.orElseThrow(()-> new BusinessLogicException(ExceptionCode.COFFEE_CODE_EXISTS));
    }
    public Coffee findVerifiedCoffee(long coffeeId) {
        Optional<Coffee> findCoffee = coffeeRepository.findByCoffee(coffeeId);
        findCoffee.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));

        return findCoffee.get();
    }

    public Coffee createCoffee(Coffee coffee) {
        String coffeeCode = coffee.getCoffeeCode().toUpperCase();
        verifyExistCoffee(coffeeCode);

        return coffeeRepository.save(coffee);
    }
    public Coffee updateCoffee(Coffee coffee) {
        Coffee findCoffee = findVerifiedCoffee(coffee.getCoffeeId());
        Optional.ofNullable(findCoffee.getKorName()).ifPresent(
                engName -> findCoffee.setKorName(engName)
        );
        Optional.ofNullable(findCoffee.getEngName()).ifPresent(
                engName -> findCoffee.setEngName(engName)
        );
        return findCoffee;
    }
    public Coffee findCoffee(long coffeeId) {
        return findVerifiedCoffee(coffeeId);
    }

    public List<Coffee> findCoffees() {
        return (List<Coffee>) coffeeRepository.findAll();
    }
    public List<Coffee> findOrderedCoffee(Order order) {
        return order.getOrderCoffee().stream()
                .map(i->findCoffee(i.getCoffeeId()))
                .collect(Collectors.toList());
    }
    public void deleteCoffee(long coffeeId) {
        Coffee coffee = findVerifiedCoffee(coffeeId);
        coffeeRepository.delete(coffee);
    }
}
