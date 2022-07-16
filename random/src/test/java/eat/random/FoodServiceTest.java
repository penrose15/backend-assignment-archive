package eat.random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
public class FoodServiceTest {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodService foodService;


    @AfterEach
    public void end() {
        foodRepository.deleteAll();
    }


    @Test
    public void saveTest() {



        Food food = new Food("감자");
        Food food1 = new Food("고구마");
        Food food2 = new Food("당근");
        Food food3 = new Food("오이");
        Food food4 = new Food("양상추");

        Food f = foodService.createFood(food);

        Food f1 = foodRepository.save(food);

        assertThat(f.getName()).isEqualTo(f1.getName());
    }
    @Test
    public void getTest() {
        Food food = new Food("감자");
        Food food1 = new Food("고구마");
        Food food5 = new Food("당근");
        Food food6 = new Food("오이");
        Food food7 = new Food("양상추");

        Food food3 =  foodRepository.save(food);
        Food food4 = foodRepository.save(food1);
        foodRepository.save(food5);
        foodRepository.save(food6);
        foodRepository.save(food7);

        List<Food> list = foodRepository.findAll();


        Food food2 = foodService.getRandomFood();
        Food food8 = foodService.getRandomFood();
        Food food9 = foodService.getRandomFood();
        Food food10 = foodService.getRandomFood();

        for(Food a : list) {
            System.out.println(a.getName());
        }
        System.out.println("");

        System.out.println(food2.getName());
        System.out.println(food8.getName());
        System.out.println(food9.getName());
        System.out.println(food10.getName());

        //assertThat(food2).is;

    }
    @Test
    public void findAllTest() {
        Food food = new Food("감자");
        Food food1 = new Food("고구마");
        Food food5 = new Food("당근");
        Food food6 = new Food("오이");
        Food food7 = new Food("양상추");

        Food food3 =  foodRepository.save(food);
        Food food4 = foodRepository.save(food1);
        foodRepository.save(food5);
        foodRepository.save(food6);
        foodRepository.save(food7);

        List<Food> list = foodRepository.findAll();

        List<Food> list2 = foodService.getFoodList();
        assertThat(list).isEqualTo(list2);


    }

}
