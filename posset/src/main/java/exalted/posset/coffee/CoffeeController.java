package exalted.posset.coffee;

import exalted.posset.coffee.dto.CoffeePatchDto;
import exalted.posset.coffee.dto.CoffeePostDto;
import exalted.posset.coffee.dto.CoffeeResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v10/coffee")
@Validated
public class CoffeeController {
    private CoffeeService coffeeService;
    private CoffeeMapper coffeeMapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper coffeeMapper) {
        this.coffeeService = coffeeService;
        this.coffeeMapper = coffeeMapper;
    }

    @PostMapping
    public ResponseEntity createCoffee(@RequestBody @Valid CoffeePostDto coffeePostDto) {
        Coffee coffee = coffeeService.createCoffee(coffeeMapper.coffeePostToCoffee(coffeePostDto));
        return new ResponseEntity<>(coffeeMapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity updateCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                       @RequestBody @Valid CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);
        Coffee coffee = coffeeService.updateCoffee(coffeeMapper.coffeePatchToCoffee(coffeePatchDto));
        return new ResponseEntity<>(coffeeMapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {
        Coffee coffee = coffeeService.getCoffee(coffeeId);
        return new ResponseEntity<>(coffeeMapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        List<Coffee> coffee = coffeeService.getCoffees();
        List<CoffeeResponseDto> coffeeResponseDtoList = coffeeMapper.coffeeToCoffeeResponseDtos(coffee);
        return new ResponseEntity<>(coffeeResponseDtoList,HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity delete(@PathVariable("coffee-id") @Positive long coffeeId) {
        coffeeService.deleteCoffee(coffeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
