package codestatePrac.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Sample {
    public static void main(String[] args) {
        int [] numbers = {1,2,3,4,5};
        int [] data = Arrays.stream(numbers)
                .filter((a) ->a%2==1)
                .map((a) ->a*2)
                .toArray();

        int[] number = {1,-2,3,-5,8,3};
        int[] result = Arrays.stream(number)
                .filter((a) ->a>0)
                .toArray();


    }
}
