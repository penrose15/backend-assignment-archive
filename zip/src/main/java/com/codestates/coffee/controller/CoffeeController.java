package com.codestates.coffee.controller;

import com.codestates.coffee.dto.CoffeePatchDto;
import com.codestates.coffee.dto.CoffeePostDto;
import com.codestates.coffee.entity.Coffee;
import com.codestates.coffee.mapper.CoffeeMapper;
import com.codestates.coffee.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v11/coffees")
@Validated
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper mapper) {
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }
    @PostMapping
    public ResponseEntity createCoffee(@RequestBody @Valid CoffeePostDto coffeePostDto) {
        Coffee coffee = coffeeService.createCoffee(mapper.coffeePostDtoToCoffee(coffeePostDto));

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity updateCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                       @RequestBody @Valid CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);
        Coffee coffee = coffeeService.updateCoffee(mapper.coffeePatchDtoToCoffee(coffeePatchDto));

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity findCoffeee(@PathVariable("coffee-id") @Positive long coffeeId) {
        Coffee coffee = coffeeService.findCoffee(coffeeId);
        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findCoffeees(@RequestParam @Positive int page,
                                       @RequestParam @Positive int size) {
        List<Coffee> coffeeList = coffeeService.findCoffees(page, size).getContent();
        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDtos(coffeeList), HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffeee(@PathVariable("coffee-id") @Positive long coffeeId) {
        coffeeService.deleteCoffee(coffeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
