package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {

    @GetMapping
    public ResponseEntity getCoffee() {


        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffeeById(@PathVariable("coffee-id") long coffeeId) {


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity PostCoffee(@Valid @RequestBody CoffeePostDTO coffeePostDTO) {

        return new ResponseEntity<>(coffeePostDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity PatchCoffee(@PathVariable("coffee-id") @Min(1) long coffeeId,
                                    @Valid @RequestBody CoffeePatchDTO coffeePatchDTO) {
        coffeePatchDTO.setCoffeeId(coffeeId);

        return new ResponseEntity<>(coffeePatchDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }


}
