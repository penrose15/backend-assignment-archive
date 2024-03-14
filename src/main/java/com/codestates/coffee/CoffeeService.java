package com.codestates.coffee;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeService {

    public Coffee createCoffee(Coffee coffee) {
        Coffee createCoffee = coffee;
        return createCoffee;
    }
    public Coffee updateCoffee(Coffee coffee) {
        Coffee updateCoffee = coffee;
        return updateCoffee;
    }
    public Coffee findCoffee(long coffeeId) {
        Coffee coffee = new Coffee(coffeeId, "아메리카노","Americano",2500);
        return coffee;
    }
    public List<Coffee> findCoffees() {

        List<Coffee> list = new ArrayList<>();
        list.add(new Coffee(1L, "아메리카노", "Americano", 2500));
        list.add(new Coffee(2L, "카라멜 라떼", "Caramel Latte", 5000));

        return list;
    }

    public void deleteCoffee(long coffeeId) {

    }
}
