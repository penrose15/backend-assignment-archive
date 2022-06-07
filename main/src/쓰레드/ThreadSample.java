package codestatePrac.쓰레드;

import java.util.ArrayList;

public class ThreadSample extends Thread{
    int s;

    public ThreadSample(int s) {
        this.s = s;
    }

    public void run() {
        System.out.println(this.s + "thread start");
        try{
            Thread.sleep(1000);//시작과 종료 사에이 1초의 간격이 생기게 함
        }catch (Exception e) {
            System.out.println(this.s + "thread end");
        }
    }

    public static void main(String[] args) {
//        ThreadSample sample = new ThreadSample();
//        sample.start(); //실행시 sample 객체의 run 메서드가 수행된다.


//        for(int i = 0; i<10;i++) {
//            //총 10개의 쓰레드를 생성하여 실행
//            Thread t = new ThreadSample(i);
//            t.start();//쓰레드가 순서에 상관없이 동시에 실행된다
//        }
//        System.out.println("main end");//main 메서드가 끝나도 쓰레드는 시작된다.
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i<10;i++) {
            Thread t = new ThreadSample(i);
            t.start();
            threads.add(t);
        }
        for(int i = 0; i<threads.size(); i++) {
            Thread t = threads.get(i);
            try{
                t.join();
            }catch (Exception e) {//쓰레드 종료될 때 까지 기다림

            }
        }
        System.out.println("main end");

    }
}
