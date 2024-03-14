package com.codestates.coffee.service;

import com.codestates.bussinessLogicException.BusinessLogicException;
import com.codestates.bussinessLogicException.ExceptionCode;
import com.codestates.coffee.CoffeeRepository;
import com.codestates.coffee.entity.Coffee;
import com.codestates.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public void findVerifiedCoffee(long coffeeId) {//커피가 이미 있으면 예외 처리
        Optional<Coffee> findCoffee = coffeeRepository.findById(coffeeId);
        if(findCoffee.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.COFFEE_ALREADY_EXIST);
        }
    }
    public void verifiedExistCoffee(String coffeeCode) {
        Optional<Coffee> coffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        if(coffee.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.COFFEE_ALREADY_EXIST);
        }
    }

    public Coffee verifyCoffee(long coffeeId) {//만약 있으면 커피 리턴
        Optional<Coffee> coffee = coffeeRepository.findById(coffeeId);
        Coffee findCoffee = coffee.orElseThrow(()-> new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));
        return findCoffee;
    }

    public Coffee createCoffee(Coffee coffee) {
        String coffeeCode = coffee.getCoffeeCode().toUpperCase();

        verifiedExistCoffee(coffeeCode);

        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(Coffee coffee) {
        Coffee findCoffee = verifyCoffee(coffee.getCoffeeId());
        Optional.ofNullable(coffee.getKorName())
                .ifPresent(korName -> findCoffee.setKorName(korName));
        Optional.ofNullable(coffee.getEngName())
                .ifPresent(engName -> findCoffee.setEngName(engName));
        Optional.ofNullable(coffee.getPrice())
                .ifPresent(price -> findCoffee.setPrice(price));
        Optional.ofNullable(coffee.getCoffeeStatus())
                .ifPresent(status -> findCoffee.setCoffeeStatus(status));

        return coffeeRepository.save(findCoffee);
    }
    public Coffee findCoffee(long coffeeId) {
        return verifyCoffee(coffeeId);
    }

    public Page<Coffee> findCoffees(int page, int size) {
        return coffeeRepository.findAll(PageRequest.of(page-1,size, Sort.by("coffeeId").descending()));
    }
    public List<Coffee> findCoffeeByOrder(Order order) {
        List<Coffee> coffees = order.getOrderCoffees().stream().map(coffeeOrder -> coffeeOrder.getCoffee()).collect(Collectors.toList());
        return coffees;//얘는 orderservice 쪽에서 orderService와 함께 리스트로 나와야 할것 같음
    }
    public void verifiedCoffee(Order order) {
        List<Coffee> coffees = findCoffeeByOrder(order);
        for(Coffee c : coffees) {
            findCoffee(c.getCoffeeId());
        }
    }

    public void deleteCoffee(long coffeeId) {
        Coffee coffee = findCoffee(coffeeId);
        coffeeRepository.delete(coffee);
    }
}
