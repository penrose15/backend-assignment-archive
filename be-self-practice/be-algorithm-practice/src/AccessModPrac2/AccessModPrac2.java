package codestatePrac.AccessModPrac2;

import codestatePrac.AccessModPrac.*;

public class AccessModPrac2 {
    //다른 패키지에 있는 경우 접근제어자 차이에 따른 변화 확인

    public static void main(String[] args) {

        Test2 t = new Test2();
        //System.out.println(t.a);
        //System.out.println(t.b);
        //System.out.println(t.c); //상속받은 클래스가 아니므로 접근 불가
        System.out.println(t.d);
    }


}

class Test2 extends AccessModifierPrac{
    public void printEach() {
       // System.out.println(a); 에러 발생
        //System.out.println(b); //에러 발생
        System.out.println(c); //다른 패키지 내 하위 클래스(상속)
        System.out.println(d);
    }

}


