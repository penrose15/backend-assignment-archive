package codestatePrac.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InputOutput {
    public static void main(String[] args) {
 /*       List<Integer> list = Arrays.asList(1,2,3,4,5);
        Stream<Integer> intStream = list.stream(); // list를 데이터 소스로 하는 새로운 스트림을 생성
        intStream.forEach(System.out::print);
        //스트림이 이미 닫힘(최종연산으로 인해) intStream.forEach(System.out::print);
        //스트림은 일회용이다.

        //객체 배열로부터 스트림 생성하기
        String[] str = {"a", "b", "c"};
        //Stream<String> str = Arrays.Stream(new String[]{"a", "b", "c"});
        Stream<String> strArray = Stream.of(str);
        strArray.forEach(System.out::print);
        IntStream arr = Arrays.stream(new int[]{1, 2, 3});
        //arr.forEach(System.out::println);
        System.out.println("count : "+arr.count());  //count 최종연산임
        //IntStream은 average(), count().. 이 가능하나
        //Stream<T>는 숫자 이외에도 여러 타입의 스트림이 가능해야 하므로 숫자 스트림에만 사용할 수 있는 sum(), average()를 넣지 않음
*/
        //난수 스트림
        IntStream intStream1 = new Random().ints(); // 무한 스트림
        intStream1.limit(5).forEach(System.out::print);

        //지정된 범위의 난수를 요소로 갖는 스트림을 생성하는 메서드(Random클래스)
        IntStream intStream3 = new Random().ints(10,0,100);
        //intStream3.forEach(System.out::print);

        //특정범위의 정수를 요소로 갖는 스트림 생성하기
        IntStream a = IntStream.range(1,10);  //1,2,3,4,5,6,7,8,9
        IntStream b = IntStream.rangeClosed(1,10);  //1,2,3,4,5,6,7,8,9,10

        //람다식을 소스로 하는 스트림 생성하기
        Stream<Integer> evenStream = Stream.iterate(0, n->n+2);
        evenStream.limit(5).forEach(System.out::print);
        //초깃값을 0으로 넣음 0,2,4,6,8......(무한 스트림)

        //generate는 초깃값이 필요 없다
        Stream<Double> random2 = Stream.generate(Math::random);
        //random2.limit(8).forEach(n->System.out.print(n));
        Stream<Integer> dd = Stream.generate(() ->1); // 1,1,1,1,1...




    }
}
