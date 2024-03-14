package codestatePrac.쓰레드;

public class PriorityThread {
    //우선순위 10이 가장 높고 1이 가장 낮음
    //메인 메서드는 디폴트가 5임
}

class PriorityTest extends Thread {
    PriorityTest(String str) {
        super(str);
    }
    public void run() {
        try {
            for(int i = 0; i<10;i++) {
                Thread.sleep(1000);
                System.out.println(getName()+i+"번째 수행");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}

class Thread45{
    public static void main(String[] args) {
        PriorityTest p1 = new PriorityTest("우선순위가 높은 스레드");
        PriorityTest p2 = new PriorityTest("우선순위가 낮은 스레드");

        p1.setPriority(Thread.MAX_PRIORITY);
        p2.setPriority(Thread.MIN_PRIORITY);
        p1.start();
        p2.start();
        try{
            p1.join();
            p2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("thread end");
    }
}
