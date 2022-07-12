package com.codestates.homework;

import com.codestates.helper.RandomPasswordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomPasswordGeneratorTest {
    @DisplayName("실습 3: 랜덤 패스워드 생성 테스트")
    @Test
    public void generateTest() {
        // TODO 여기에 테스트 케이스를 작성해주세요.
        RandomPasswordGenerator r = new RandomPasswordGenerator();
        String password= r.generate(2, 5,2,1);
        List<Character> letter = password.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
        int upper = (int) letter.stream().filter(c -> c>=65 && c<=90).count();
        int lower = (int) letter.stream().filter(c -> c>=97 && c<=122).count();
        int number = (int) letter.stream().filter(c-> (c-'0')>=0 && (c-'0')<=9).count();
        int special = (int) letter.stream().filter(c -> c>=33 && c<=47).count();

        assertEquals(upper, 2);
        assertEquals(lower,5);
        assertEquals(number,2);
        assertEquals(special,1);
    }
}
