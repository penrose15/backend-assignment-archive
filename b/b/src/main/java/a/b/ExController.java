package a.b;

import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/excepts")
@Validated
public class ExController {

    @PostMapping
    public ResponseEntity exPost(@Valid @RequestBody ExPostDto exPostDto) {
        return new ResponseEntity<>(exPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/{price}")
    public ResponseEntity exPrice(@PathVariable("price") @Range(min = 100) int price) {
        Map<String, Integer> map = new HashMap<>();
        map.put("itemA", 1000);
        return new ResponseEntity(map, HttpStatus.OK);
    }


}
