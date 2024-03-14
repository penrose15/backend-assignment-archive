package codestatePrac.Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class 예시flatMap {
    public static void main(String[] args) {
        Stream<String[]> stringArraysStream = Stream.of(
                new String[]{"hello", "world", "java"},
                new String[]{"code", "states"});

        stringArraysStream.flatMap(Arrays::stream).forEach(System.out::println);
    }
    //flatMap는 스트림을 반환한다. map()는 Stream<Stream>을 반환하면 flatMap()는 Stream을 리턴한다.
}
class Sorted{ //요소정렬 (기본 오름차순)
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");
        list.stream()
                .sorted()
                .forEach(n -> System.out.println(n));
        System.out.println();

        list.stream()
                .sorted(Comparator.reverseOrder()) //내림차순
                .forEach(n -> System.out.println(n));


    }
}
