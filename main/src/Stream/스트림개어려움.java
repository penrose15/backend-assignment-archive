package codestatePrac.Stream;

import java.util.Arrays;
import java.util.List;

public class 스트림개어려움 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("김코딩", "이자바", "김인기", "김코딩");

        names.stream()
                .distinct() //중복 제거
                .forEach(n -> System.out.println(n));
        System.out.println();

        names.stream()
                .filter(n ->n.startsWith("김")) //필터링
                .forEach(n -> System.out.println(n));
        System.out.println();

        names.stream()
                .distinct()
                .filter(m -> m.startsWith("김"))
                .forEach(d -> System.out.println(d));

    }
}
