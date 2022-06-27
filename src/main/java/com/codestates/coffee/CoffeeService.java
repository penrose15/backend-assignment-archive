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

    public  Coffee updateCoffee(Coffee coffee) {
        Coffee updateCoffee = coffee;
        return updateCoffee;
    }

    public Coffee findCoffee(long coffeeId) {
        Coffee coffee = new Coffee(coffeeId,"아메리카노", "americano", 3000);
        return coffee;
    }
    public List<Coffee> findCoffees() {
        List<Coffee> list = new ArrayList<>();
        list.add(new Coffee(1L, "아메리카노", "Americano", 3000));
        list.add(new Coffee(2L, "카페라뗴", "Caffe Latte", 3500));
        return list;
    }
    public void deletebyId(long coffeeId) {
        System.out.println("삭제되었습니다");
    }
}
