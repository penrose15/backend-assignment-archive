package codestatePrac.Stream;

import java.util.Arrays;
import java.util.List;

public class 스트림개어려움매핑 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("kimcoding", "javalee", "ingikim", "kimcoding");
        names.stream()
                .map(s -> s.toUpperCase())
                .forEach(n -> System.out.println(n));
    }

}

