package codestatePrac.쓰레드;

public class MultiThread {
    public static void main(String[] args) {
        Bank t1 = new Bank("ATM");
        Bank t2 = new Bank("은행");

        t1.start();
        t2.start();
    }
}

class Account {
    int balance = 1000;
    public void withdraw(int money) {
        if(balance >= money) {
            try{
                Thread.sleep(1000);
            }catch (Exception e) {}
            balance -=money;
        }
    }
}

class Bank extends Thread{
    static Account obj = new Account();
    public Bank(){}
    public Bank(String name) {super(name);}
    public void run(){
        //무한 반복
        while (true) {
            //찾는 금액은 랜덤
            //멀티 스레드
            synchronized (obj) { //스레드들이 객체를 공유하며 작업하는 경우가 생길 때 공유하는 객체가 서로의 작업에
                //영향을 미치면 안되므로 이를 방지하기 위해 사용된다.
                int money = (int) (Math.random() * 3 + 1) * 100;
                if (obj.balance >= money) {
                    System.out.println(getName() + " : 원본의 balance" + obj.balance);
                    System.out.println(getName() + " : 찾는 금액" + money);
                    obj.withdraw(money);
                    System.out.println(getName() + " : 수정된 balance: " + obj.balance);
                } else {
                    System.out.println("잔액 부족");
                    break;
                }
            }
        }
    }
}
