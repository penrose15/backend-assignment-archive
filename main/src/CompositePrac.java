package codestatePrac;

public class CompositePrac {
//포함관계
    public static void main(String[] args) {
        Address address = new Address("서울","헬조선");
        Address address1 = new Address("도쿄","일본");

        Employee e = new Employee(1,"김코딩",address);
        Employee e1 = new Employee(2,"박개발",address1);

        e.showInfo();
        e1.showInfo();

    }
}

class Employee{
    int id;
    String name;
    Address address;

    public Employee(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
        //Address클래스로 city, country항목을 묶어준 다음 Employee에 참조변수를 선언함
    }
    void showInfo() {
        System.out.println(id+ " "+name);
        System.out.println(address.city+ " " +address.country);
    }
}
class  Address {
    String city, country;
    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }
}

