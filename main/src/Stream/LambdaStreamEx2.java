package codestatePrac.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LambdaStreamEx2 {
    public static void main(String[] args) {
        List<MemberEx> list = Arrays.asList(
                new MemberEx(39, "male", "basic"),
                new MemberEx(35, "female", "advance"),
                new MemberEx(29, "male", "basic"),
                new MemberEx(44, "female", "intermediate"),
                new MemberEx(39, "male", "basic")

        );
        double stream = list.stream()
                .filter(m->m.getGender() == "male" && m.getClassName() == "basic")
                .mapToInt(MemberEx::getAge)
                .count();
        System.out.println(stream);




    }
}
