package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/coffees")
@Validated
public class CoffeeController {
    private final CoffeeMapper coffeeMapper;
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeMapper coffeeMapper, CoffeeService coffeeService) {
        this.coffeeMapper = coffeeMapper;
        this.coffeeService = coffeeService;
    }

    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        Coffee coffee = coffeeMapper.coffeePostDtoToCoffee(coffeePostDto);
        Coffee response = coffeeService.createCoffee(coffee);
        return new ResponseEntity<>(coffeeMapper.coffeeToCoffeeResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        Coffee coffee = coffeeMapper.coffeePatchDtoToCoffee(coffeePatchDto);
        Coffee response = coffeeService.updateCoffee(coffee);
        return new ResponseEntity<>(coffeeMapper.coffeeToCoffeeResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        // not implementation
        Coffee response = coffeeService.findCoffee(coffeeId);

        return new ResponseEntity<>(coffeeMapper.coffeeToCoffeeResponseDto(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        System.out.println("# get Coffees");

        // not implementation
        List<Coffee> list = coffeeService.findCoffees();
        List<CoffeeResponseDto> response = list.stream()
                .map(i-> coffeeMapper.coffeeToCoffeeResponseDto(i))
                .collect(Collectors.toList());


        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        // No need business logic
        coffeeService.deletebyId(coffeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
