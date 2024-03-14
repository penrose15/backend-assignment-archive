public class Cartest {

//    Cartest c = new Cartest(); // 클래스 명 참조변수명 = new 생성자();
//    //참조변수 선언 후 바로 인스턴스 생성, 객체의 주소를 참조 변수에 저장
//
//    //참조변수 : 실제 대이터가 위히해있는 힙 메모리의 *주소*
    public static void main(String[] args){
        Car tesla = new Car("모델 3", "빨강"); //객체 생성

        System.out.println("내차의 모델을"+tesla.model+"이고 색은 "+tesla.color+" 이다"); //필드 호출
        tesla.power();  //메서드 호출
        tesla.accelerate();
        tesla.stop();
    }

}
class Car {
    public String model;  //필드 선언
    public String color;

    public Car(String model, String color){//인스턴스 초기화를 위한 생성자 함수
        this.model = model;
        this.color = color;
    }
    void power(){System.out.println("시동을 걸음");}  //메서드 선언
    void accelerate(){System.out.println("더 빠르게");}
    void stop(){System.out.println("멈춰");}


}
