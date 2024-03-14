public class MethodPrac {
    public static void main(String[] args) { //psvm + Enter 치면 바로 나온다(이클립스 망해라)
        MethodEx m = new MethodEx();
        m.printHello();
        m.multiply(3, 6.0);
        m.getNum();

        //이러면 메소드 호출이 가능하다다
    }
}

/*
    메서드 구성 --> 자바 제어자 반환타입  메서드명(매개변수) {
                   메서드 내용//메서드 바디
                   }
*/

class MethodEx {

    public static int add(int x, int y) {  //메서드 시그니쳐
        int result = x + y; //메서드 바디
        return result; // 반환값
    }
    void printHello() {//void를 쓰면 반환 타입이 없는 메서드가 됨
        System.out.println("Hello");
    }
    void printNum(int num) {  //물론 매개변수는 넣을 수 있다
        System.out.println(num);
    }
    int getNum() {
        return 7; //반환 타입은 있어도 매개변수가 없는 메서드를 만들 수 있다

    }
    Double multiply(int x, double y) { //매개변수화 리턴값이 둘다 있음
        double result = x*y;
        return result;
    }

}
