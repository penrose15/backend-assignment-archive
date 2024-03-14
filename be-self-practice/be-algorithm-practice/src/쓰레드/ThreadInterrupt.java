package codestatePrac.쓰레드;

public class ThreadInterrupt extends Thread{
    ThreadInterrupt(String str) {
        super(str);//출력 생성자
    }
    public void run() {
        try{
            for(int i = 0; i<10;i++) {
                Thread.sleep(1000);
                System.out.println(getName()+i+"번째 수행");
            }
        } catch (InterruptedException e) {
            System.out.println("스레드 강제 종료");
            return;//인터럽트 걸릴시 수행
        }
    }
}

class Thread55{
    public static void main(String[] args) {
        ThreadInterrupt t =  new ThreadInterrupt("스레드");
        t.start();
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e) {}
        t.interrupt();//쓰레드 일시정지시 interupted exception예외를 발생시킨다
        //이로 인해 쓰레드의 run()을 정상종료 시킨다.

    }
}
