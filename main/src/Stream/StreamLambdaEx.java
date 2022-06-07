package codestatePrac.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamLambdaEx {
    public static void main(String[] args) throws Exception{
        List<StudentEx> list = Arrays.asList(
                new StudentEx("김철수", 86),
                new StudentEx("김영희", 94)
        );
        //스트림을 이용한 출력
        Stream<StudentEx> s = list.stream();
        s.forEach(t -> {
            String name = t.getName();
            int score = t.getScore();
            System.out.println(name + " - "+score);
        });
        System.out.println("=====================");

        for (StudentEx st : list) {
            String name1= st.getName();
            int score1 = st.getScore();
            System.out.println(name1 + " - "+score1);
        }


    }
}
