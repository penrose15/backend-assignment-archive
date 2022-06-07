package codestatePrac.람쥐썬더;

public interface MethodReference {

    public int instanceMethod(int x, int y);
}
class Calculator{
    int a(int a, int b) {
        return a+b;
    }
    public static int staticMethod(int x, int y) {
        return x*y;
    }

}

class Methodtest{
    public static void main(String[] args) {
        MethodReference m;


        m = Math::max;
        System.out.println(m.instanceMethod(4,8));

        //instance Method
        Calculator calc = new Calculator();
        m = calc::a;
        System.out.println(m.instanceMethod(6,7));

        //static method
        m = Calculator::staticMethod;
        System.out.println(m.instanceMethod(4,8));



    }
        }
