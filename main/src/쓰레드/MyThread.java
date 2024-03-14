package codestatePrac.쓰레드;

public class MyThread implements Runnable{
    @Override
    public void run() { //Runnable 인터페이스의 추상메서드 런 구현
        /*작업내용*/
        for(int i = 0; i< 500;i++) {
            System.out.print(Thread.currentThread().getName());
        }//Thread.currentThread(). 현재 실행중인 쓰레드 반환
    }
    //쓰레드 생성후 실행
    /*Runnable r = new MyThread();
    Thread r2 = new Thread(r);
    r2.start();
    * */
}
class MyThread2 extends Thread { //쓰레드 클래스 상속
    public void run() { //쓰레드가 수행할 작업 수행
        for(int i = 0; i<500; i++) {
            System.out.print(this.getName()); //this생략 가능
        }
    }
}
class RunEx {
    public static void main(String[] args) {
        MyThread2 t1 = new MyThread2(); //클래스 로 상속된 쓰레드 호풀법

        Runnable r = new MyThread();
        Thread t2 = new Thread(r); //인터페이스 쓰레드 호출법

        t1.start();;  //쓰레드 생성후 start()를 호출해야 쓰레득가 작업 실행함
        t2.start();  //스타트 먼저 한다고 먼저 시작 안됨 OS 스케줄러에 따라 실행됨
        //OS에 종속적인 것중 하나임

        /*
        * 왜 작성하는 건 run인데 start로 실행하는가
        *
        * start()를 호출해야 새로운 호출 스택이 생성되어 거기에 run()이 실행된다
        * run()을 호출하면 스택이 새로 만들어 지지 않아서 main()과 run()이 독립적으로 실행이 되지 않는다다        *
        * */
    }
}