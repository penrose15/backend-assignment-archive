package codestatePrac.람쥐썬더;

import java.lang.reflect.Member;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LamdaConstructorRef {
    public static void main(String[] args) throws Exception{
        Function<String, LamdaConstructor> func1 = LamdaConstructor::new;
        LamdaConstructor lamda = func1.apply("하현우");

        BiFunction<String,String, LamdaConstructor> func2 = LamdaConstructor::new;
        LamdaConstructor lamda2 = func2.apply("하현우","hahyunwoo");


    }
}
