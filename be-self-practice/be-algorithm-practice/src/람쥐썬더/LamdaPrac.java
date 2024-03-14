package codestatePrac.람쥐썬더;

//매개변수와 리턴값이 없는 람다식
@FunctionalInterface
public interface LamdaPrac {
    public void accept();
}

class MyfunctionalExample{
    public static void main(String[] args) {
        LamdaPrac ex;
        ex = () -> {
            String str = "Calling Method, First";
            System.out.println(str);
        };
        ex.accept();

        ex = () -> System.out.println("Calling Method, Second");
        ex.accept();
    }
}
