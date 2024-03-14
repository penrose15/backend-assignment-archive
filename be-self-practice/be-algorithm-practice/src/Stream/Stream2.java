package codestatePrac.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b","c");
        Stream<String> listStream = list.stream();
        listStream.forEach(System.out::println);
        System.out.println("==================");
        //배열로부터 스트림을 생성
        Stream<String> stream = Stream.of("a","b","c");
        Stream<String> stream1 = Arrays.stream(new String[]{"a", "b","c"});
        System.out.println("==============");
        IntStream stream2 = IntStream.range(1, 10);

    }
}
