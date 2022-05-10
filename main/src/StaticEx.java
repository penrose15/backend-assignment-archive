public class StaticEx {
    public static void main(String[] args) {
        StaticExample s = new StaticExample();

        System.out.println(s.num);  //인스턴스 변수
        System.out.println(StaticExample.num2); //정적멤버(static이 붙은 변수는 클래스명.변수이름 으로 바로 됨) =>클래스 내에 저장 공간이 있어 바로 되는거임

        //인스턴스 멤버는 반드시 객체를 만든 다음에 변수와 메서드에 접근하여 해당 인스턴스 변수를 사용할 수 있으나 정적 멤버는 바로 사용이 가능하다
        //클래스 변수는 공유 변수의 성질을 가지고 있다
    }

    //1
    //-10

}

class StaticExample {
    int num = 1;
    static int num2 = -10;
}


