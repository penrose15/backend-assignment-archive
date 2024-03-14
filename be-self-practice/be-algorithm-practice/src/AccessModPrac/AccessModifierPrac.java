package codestatePrac.AccessModPrac;

public class AccessModifierPrac {
    /*
    * 자바에서 제어자는 클래스, 필드, 메서드, 생성자 등에 부가적인 의미를 부여한다.
    * 제어자는 접근제어자(public, protected, default, private)가 있고
    * 기타 제어자(static, final, abstract, native,transient, synchronized)가 있다.
    * 접근 제어자는 캡술화를 구현하기 위한 핵심적인 방법중 하나이다
    * 접근 제어자를 통해 클래스 외부로의 불필요한 데이터 노출을 방지 할 수 있다.
    * private : 동일 클래스 에서만 접근 가능
    * (default) : 동일 패키지 내에서만 접근 가능
    * protected : 동일 패키지, 다른 패키지의 하위 클래스에서 접근 가능
    * public : 싹 다 가능
    * */
    private int a = 1;
    int b = 2;     //디폴트
    protected int c = 3;
    public int d = 4;

    public void printEach() { //동일 클래스라 에러 발생 x
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

}

class Test {
    public static void main(String[] args) {
        AccessModifierPrac t = new AccessModifierPrac();

        //System.out.println(t.a);  동일 클래스가 아니라 에러
        System.out.println(t.b);
        System.out.println(t.c);
        System.out.println(t.d);
    }

}
