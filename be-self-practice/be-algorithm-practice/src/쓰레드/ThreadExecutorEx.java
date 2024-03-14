package codestatePrac.쓰레드;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadExecutorEx {
    /*//필요에 따라 새로운 쓰레드를 생성하며, 이전에 생성했던 쓰레드가 존재한다면 이를 재사용함
    ExecutorService executorService = Executors.newCachedThreadPool();
    //고정된 개수의 쓰레드를 생성하고, 모든 쓰레드가 작업중이라면 task queue에 작업 적제한다
    ExecutorService executorService2 = Executors.newFixedThreadPool(8);*/
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(6);
        //최대 6개의 스레드 풀을 생성

        for (int i = 0; i<10; i++) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    ThreadPoolExecutor tp = (ThreadPoolExecutor) ex;
                    //스레드 풀 갯수 확인
                    int poolSize = tp.getPoolSize();
                    //스레드 풀에 있는 해당 스레드 이름
                    String threadName = Thread.currentThread().getName();
                    System.out.println("스레드 풀"+poolSize+"스레드 이름"+threadName);
                }
            };
            //스레드 풀 작업처리요청
            ex.execute(run);
            //ex.submit(run);

            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        ex.shutdown();
    }

}
