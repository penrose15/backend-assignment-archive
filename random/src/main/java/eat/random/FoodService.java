package eat.random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.BadAttributeValueExpException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class FoodService {
    private final FoodRepository foodRepository;


    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food createFood(Food food) { //food 생성
        verifiedExistFood(food.getName()); //이미 있으면 예외 던지기
        return foodRepository.save(food);
    }
    public Food updateFood(Food food) { //food 수정
        Food findFood = verifyFoodById(food.getId());
        verifiedExistFoodByName(food.getName()); //DB에 있는 이름으로 바꾸려 하면 예외 던짐
        Optional.ofNullable(food.getName())
                .ifPresent(name -> findFood.setName(name));
        return findFood;
    }
    public Page<Food> findFoods(int page, int size) { //food 리스트 조회
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<Food> pageFood = foodRepository.findAll(pageRequest);

        return pageFood;
    }

    public void deleteFood(String name) { //이름으로 삭제
        String foodName = Findword.processWord(name);

        try{
            Food food = verifyFoodByName(name);
            foodRepository.delete(food);
        }catch (NoSuchElementException e) {
            Food food = verifyFoodByName(foodName);
            foodRepository.delete(food);
        }


    }

    public Food getRandomFood() { //랜덤으로 음식 하나 뽑기
        long count =  foodRepository.count();
        int random = (int)(Math.random()*(int)count) + 1;
        Optional<Food> optionalFood = foodRepository.findById((long)random);
        Food food = optionalFood.orElseThrow(()-> new NoSuchElementException("#"));

        return food;
    }

    public void verifiedExistFood(String name) { //food 존재 여부 확인(있으면 예외 던지기)
        Optional<Food> foodOptional = foodRepository.findByName(name);
        if(foodOptional.isPresent()) {
            throw new IllegalStateException("이미 존재하고 있는 음식입니다.");
        }

    }



    public List<Food> getFoodList() { //리스트 형태로 모든 음식들 찾아내기
        List<Food> list = foodRepository.findAll();
        if(list.size() == 0) {
            throw new NoSuchElementException("저장된 음식이 없습니다.");
        }

        return list;
    }

    public Food verifyFoodById(long id) { //food id로 찾기
        Optional<Food> foodOptional = foodRepository.findById(id);
        Food food = foodOptional.orElseThrow(() -> new NoSuchElementException("존재하지 않는 음식입니다."));
        return food;
    }
    public Food verifyFoodByName(String name) { //food name으로 찾기

        try{
            Optional<Food> food = foodRepository.findByName(name);
            Food food1 = food.orElseThrow(() -> new NoSuchElementException("존재하지 않는 음식"));
            return food1;
        }catch (NoSuchElementException e) {
            String foodName = Findword.processWord(name);
            Optional<Food> food = foodRepository.findByName(foodName);
            Food food1 = food.orElseThrow(()->new NoSuchElementException());
            return food1;
        }
    }

    public void verifiedExistFoodByName(String name) { //음식이 리스트에 있는지 확인
        Food food = verifyFoodByName(name);
        if(food != null) {
            throw new RuntimeException("이미 있는 음식입니다"); //추후 다른 예외로 처리하기
        }
    }

    }

    class Findword {

        public static String processWord(String foodName) {
            String word = foodName.replaceAll(" ","");

            return word;
        }

    }



