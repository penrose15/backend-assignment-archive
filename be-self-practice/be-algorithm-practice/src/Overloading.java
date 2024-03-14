public class Overloading {
    public static void main(String[] args) {
        Shape s = new Shape(); // 객체 생성

        s.area();
        s.area(5);
        s.area(10,10);
        s.area(6.0,12.0);
        s.area("칠각형");
        //오버로딩 조건 1.같은 이름의 메서드 명, 2. 매개변수의 개수나 타입을 다르게 해야 함
        //오버로딩의 예시 println()
    }

}

class Shape { //같은 이름의 메서드가 4개, 오버로딩
    public void area() {
        System.out.println("넓이");
    }
    public void area(int r) {
        System.out.println("원 넓이= "+ 3.14*r*r);
    }
    public void area(int w, int l) {
        System.out.println("직사격형의 넓이"+w*l);
    }
    public void area(double b, double h) {
        System.out.println("삼각형 넓이 = "+0.5*b*h);
    }
    public void area(String ss) {
        System.out.println(ss);
    }

}
