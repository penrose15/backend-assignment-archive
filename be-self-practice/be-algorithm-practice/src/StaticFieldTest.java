public class StaticFieldTest {
    public static void main(String[] args) {
        StaticField s = new StaticField();
        StaticField s2 = new StaticField();

        s.num1 = 100;
        s2.num1 = 1000;

        System.out.println(s.num1); //100
        System.out.println(s2.num1); //1000

        s.num2 = 100000;
        s2.num2 = 15000;  //값공유가 일어나 가장 마지막으로 할당된 15000이 출력된다

        System.out.println(s.num2);   //15000
        System.out.println(s2.num2);  //15000

    }
}

class StaticField {
    int num1 = 10;
    static int num2 = 15;

}
