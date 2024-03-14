package codestatePrac.쓰레드;

import java.util.ArrayList;

public class RunnableSample implements Runnable{
    int a;

    public RunnableSample(int a) {
        this.a = a;
    }

    @Override
    public void run() {
        System.out.println(this.a + "thread start");
        try {
            Thread.sleep(1000);
        }catch (Exception e) {
            System.out.println("thread end");
        }

    }

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i<10; i++) {
            Thread t = new Thread(new RunnableSample(i));
            t.start();//run 메서드 대기 시킴
            threads.add(t); //arraylist 에 추가
        }

        for(int i = 0; i< threads.size();i++) {
            Thread t = threads.get(i);
            try{
                t.join(); // 쓰레드 졸료된 후 로직을 수행해야  한다면 join
            }catch (Exception e) {

            }
        }
        System.out.println("main end");
    }

}
