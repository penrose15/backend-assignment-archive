package codestatePrac;

public class Overrride {

    public static void main(String[] args) {
        Bike b= new Bike(); //각각의 타입으로 선언, 각각의 타입으로 객체 생성
        Bicycle bicycle = new Bicycle();
        Java java = new Java();

        b.run();
        bicycle.run();
        java.run();

        Vehicle bike2 = new Bike(); //상위 클래스 타입으로 선언+ 각각 타입으로 객체 생성
        Vehicle bicycle2 = new Bicycle();
        Vehicle java2 = new Java(); //다형성 관련 내용에서 설명한다

        bike2.run();
        bicycle2.run();
        java2.run();
        System.out.println(" ------------------------ ");

        Vehicle[] vehicles = new Vehicle[]{ new Bike(), new Bicycle(), new Java()};

        for (Vehicle vehicle : vehicles) {
            vehicle.run();
        }

    }

}

class Vehicle{
    void run() {
        System.out.println("Vehicle is running");
    }

}

class Bike extends Vehicle{
    void run() {
        System.out.println("bike is running");
    }
}

class Bicycle extends Vehicle {
    void run() {
        System.out.println(" bicycle is running");
    }
}
class Java extends Vehicle{
    void run() {
        System.out.println("Java is running");
    }
}
