package codestatePrac;

public class InheritsPrac {
    public static void main(String[] args) {
        Person p = new Person();
        p.name = "김코딩";
        p.age = 24;
        p.learn();
        p.eat();
        p.walk();
        System.out.println(p.name);

        Dancer d = new Dancer();
        d.name = "dancer";
        d.age = 59;
        d.learn();
        d.learn();
        d.walk();
        d.dancing();
        System.out.println(d.name);

        Singer guck = new Singer();
        guck.age = 42;
        guck.name = "하현우";
        guck.bandName = "국카스텐";
        guck.singing();
        guck.playguitar();
        System.out.println(guck.name);



    }


}

class Person {

    String name;
    int age;



    void learn() {
        System.out.println("공부를 합니다.");
    }

    void walk() {
        System.out.println("걷습니다.");
    }

    void eat() {
        System.out.println("밥을 먹습습니다.");

    }
}

class Dancer extends Person {
    String groupName;

    void dancing() {
        System.out.println("춤을 춥니다.");
    }
    }


class Singer extends Person {

    String bandName;


    void singing() {
        System.out.println("노래합니다.");
    }
    void playguitar() {
        System.out.println("기타를 칩니다.");
    }
}

