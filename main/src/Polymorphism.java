package codestatePrac;

public class Polymorphism {
    /*
    * 다형성이란 한 타입의 참조변수를 통해 여러 타입의 객체를 참조할 수 있도록 만든 것을 의미한다.
    * 상위 클래스 타입의 참조변수를 통해 하위 클래스의 객체를 참조할 수 있도록 허용해 준것이다.
    *
    * */


    public static void main(String[] args) {
        Coffee1 coffee = new Coffee1();
        Cappuccino1 cappuccino = new Cappuccino1();
        Coffee1 latte = new Latte1(); //객체 타입과 참조변수의 타입의 불일치
        //Americano1 americano1 = new Coffee1(); 하위 클래스 타입으로 상위 클래스의 객체 참조는 불가능하다.

        coffee.coffeeInfo();
        cappuccino.coffeeInfo();
        latte.coffeeInfo();
        //latte.milk(); 상위 클래스에 있는 참조변수로만 접근이 가능하므로 불가
        System.out.println("--------------------------------------------");

        Americano1 americano = new Americano1();
        Coffee1 coffee2 = (Coffee1) americano; // 상위 클래스 Coffee1타입으로 변환(생략 가능)
        // ==> Coffee1 coffee2 = new Americano1();
        coffee2.coffeeInfo();
        Americano1 americano2 = (Americano1) americano; //다시 하위 클래스 Americano1타입으로 변환(생략x)
        americano2.coffeeInfo();
        System.out.println("--------------------------------------------");

        //참조 변수 instance of 타입  =>  Coffee1 coffee = new Americano1(); 에서
        //Coffee1 coffee 이 부분을 확인하여 서로 상속관계인지를 확인하는 것이다

        System.out.println(latte instanceof Coffee1);
        System.out.println(coffee instanceof Coffee1);
        System.out.println(americano2 instanceof Coffee1);
        System.out.println(coffee2 instanceof Americano1);  //얘도 Americano1로 타입 변환이 가능하다
        System.out.println(coffee instanceof Americano1);  //얘도 Americano1로 타입 변환이 가능하다
        System.out.println(latte instanceof Americano1); //클래스 사이에 상속 관계가 존재하는가?


        //System.out.println(coffee2 instanceof Americano1); 얘는 true 가 나오는데
        // System.out.println(coffee instanceof Americano1); 얘는 false 가 나오는 이유

        /*Coffee1 coffee = new Coffee();
        * Coffee1 coffee2 = new Americano1();
        *
        * instanceof 는 참조변수가 검사한 타입으로 타입변환이 가능한지 검사하는 것
        *
        * coffee의 타입을 Americano1로 변환하면 Americano1 coffee = new Coffee1();
        * 로 바꾸는 것이 되므로 당연히 false 가 나옴
        * 그러나 coffee2는 객체가 하위 클래스 이므로 타입 변환이 가능하다.
        * */

    }


}

class Coffee1{
    public void coffeeInfo() {
        System.out.println("커피를 주문합니다.");
    }
}
class Americano1 extends Coffee1{
    public void coffeeInfo() {
        System.out.println("아메리카노를 주문합니다.");
    }
}
class Latte1 extends Coffee1{
    public void coffeeInfo() {
        System.out.println("카페라떼를 주문합니다.");
    }
    public void milk() {
        System.out.println("우유를 넣습니다.");
    }
}
class Cappuccino1 extends Coffee1{
    public void coffeeInfo() {
        System.out.println("카푸치노를 주문합니다.");
    }
    public void milkForm() {
        System.out.println("우유거품을 많이 넣습니다.");
    }
}
