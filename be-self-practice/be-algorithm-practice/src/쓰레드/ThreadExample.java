package codestatePrac.쓰레드;

public class ThreadExample{
    Thread thread  =new Thread( () -> {
        //실행할 코드
        for (int i = 0; i<100; i++) {
            System.out.print(0);
        }
    });
}
//정석은 이렇게 생성함
class ThreadEx implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i<100; i++) {
            System.out.print(1);
        }
    }
}
//class Thread4 {
//    Thread threadex = new Thread(){
//        public void run(){
//
//        }
//    };
//
//}



class ThreadPrac1 {
    public static void main(String[] args) {
        ThreadExample thread1 = new ThreadExample();
        thread1.thread.start();
        Runnable a = new ThreadEx();
        Thread thread2 = new Thread(a);
        thread2.start();
    }
}
