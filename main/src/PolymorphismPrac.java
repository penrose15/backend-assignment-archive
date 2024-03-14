package codestatePrac;

public class PolymorphismPrac {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.buyFlower(new Rose(3,"red",true));
        System.out.println("현재 잔액은"+customer.money+" 입니다");
        customer.buyFlower(new MistFlower(5,"white",false));
        System.out.println("현재 잔액은"+customer.money+" 입니다");
        customer.buyFlower(new SunFlower(5,true));
        System.out.println("현재 잔액은"+customer.money+" 입니다");
    }
}

class Flower{
    int price;
    String color;
    int count;
    boolean bouquet;
    int totalPrice;


    void totalPrice(int count, int price) {
        totalPrice = count*price;
    }
    void priceChange(boolean bouquet) {
        if(bouquet == true) {
            this.price = price + 5000;
        }
        else{
            this.price = price;
        }

    }

    public Flower(int count, boolean bouquet,int price) {
        this.price = price;
        this.count = count;
        this.bouquet = bouquet;

    }

}
class Customer{
    int money = 100000;
    void buyFlower(Flower flower) {
        money = money - (flower.price*flower.count);
    }

}
class Rose extends Flower{
    //int price = 3000;

    public Rose(int count, String color,boolean bouquet) {
        super(count, bouquet, 3000);
        this.color = color;
    }


}
class MistFlower extends Flower{
    //int price = 500;

    public MistFlower(int count,String color, boolean bouquet) {
        super(count, bouquet, 500);
        this.color = color;
    }
}
class SunFlower extends Flower{
    //int price = 3000;

    public SunFlower(int count, boolean bouquet) {
        super(count, bouquet,3000);
    }
}
