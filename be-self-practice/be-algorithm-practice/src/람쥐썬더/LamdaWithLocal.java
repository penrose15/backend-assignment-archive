package codestatePrac.람쥐썬더;

//매개변수가 있는 람쥐식
public interface LamdaWithLocal {
    public void accept(int x);
}

class MyfunetionalExample1{
    public static void main(String[] args) {
        LamdaWithLocal withLocal;
        withLocal = (x) -> {
            int result = x*5;
            System.out.println(result);
        };
        withLocal.accept(2);

        withLocal = (x) -> System.out.println(x*5);
        withLocal.accept(3);

    }

}
