package eat.random;

import eat.random.foodDto.FoodDeleteDto;
import eat.random.foodDto.FoodPatchDto;
import eat.random.foodDto.FoodPostDto;
import eat.random.foodDto.FoodResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/food")
public class FoodController {
    private FoodService foodService;
    private FoodMapper mapper;

    public FoodController(FoodService foodService, FoodMapper mapper) {
        this.foodService = foodService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity createFood(@RequestBody @Valid FoodPostDto foodPostDto) {
        Food food = foodService.createFood(mapper.foodPostDtoToFood(foodPostDto));

        return new ResponseEntity<>(mapper.foodToFoodResponseDto(food), HttpStatus.CREATED);
    }

    @PatchMapping("/{food-id}")
    public ResponseEntity updateFood(@PathVariable("food-id") @Positive long id,
                                     @RequestBody @Valid FoodPatchDto foodPatchDto) {
        foodPatchDto.setId(id);
        Food food = foodService.updateFood(mapper.foodPatchDtoToFood(foodPatchDto));

        return new ResponseEntity<>(mapper.foodToFoodResponseDto(food), HttpStatus.OK);
    }

    @GetMapping("/rand/{bld}")
    public ResponseEntity getFoodRandom(@PathVariable("bld") String bld) {
        Food food = foodService.getRandomFood(Food.BLD.valueOf(bld));

        return new ResponseEntity<>(mapper.foodToFoodResponseDto(food),HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity getFoodByPage(@RequestParam int page, @RequestParam int size) {

        Page<Food> pageFood = foodService.findFoods(page, size);
        List<Food> list = pageFood.getContent();
        List<FoodResponseDto> responses = mapper.foodToFoodResponseDtos(list);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteFood(@RequestBody FoodDeleteDto foodDeleteDto) {
        foodService.deleteFood(foodDeleteDto.getName());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
