package codestatePrac;

import java.util.Scanner;

public class GetterSetterTest {
    /*
    * 객체 지향의 캡슐화와 동시어 데이텨 변경을 위해 getter, setter를 사용한다.
    * setter : 외부에서 메서드에 접근하여 조건에 맞을 경우 데이터 값을 변경 가능하게 함
    * getter : 외부에서 값을 읽으려 할때 가공해서 외부로 전달
    * */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Worker w = new Worker();

        int count = 5;

        while(count>0) {

            String a = sc.nextLine();
            int b = sc.nextInt();
            int c = sc.nextInt();

            w.setName(a);
            w.setAge(b);
            w.setId(c);

            String name = w.getName();
            System.out.println("근로자의 이름은 " + name);
            int age = w.getAge();
            if (age == 0) {
                System.out.println("잘못된 접근입니다.");
                count--;
            } else {
                System.out.println("근로자의 나이는 " + age);
            }
            int id = w.getId();
            if (id == 0) {
                System.out.println("잘못된 접근입니다.");
                count--;
            } else {
                System.out.println("근로자의 id 는  " + id);
            }

            if(count<=0) {
                System.out.println("5번 이상 틀렸습니다.");
                break;
            }

        }

    }
}
class Worker {
    private String name; //정보의 은닉화, 외부로부터의 접근 제한
    private int age;
    private int id;

    //generate -> getter,setter 쓰면 일일히 안써도 자동으로 만들어줌

    public String getName() { //멤버 변수의 값
        return name;
    }

    public void setName(String name) { //멤버 변수의 값 변경경
       if(name.length()>5 || name.length()<2) return;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 1) return;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if((int)(Math.log10(id)+1) != 4) {
            return;}
        this.id = id;
    }
}
