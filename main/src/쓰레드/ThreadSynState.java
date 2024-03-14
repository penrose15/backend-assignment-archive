package codestatePrac.쓰레드;

//쓰레드 동기화 및 상태
//쓰레드가 사용중인 객체를 다른 쓰레드가 변경할 수 없도록 하기 위해서는
//스레드 작업이 끝날 떄까지 객체에 잠금을 걸어 다른 쓰레드가 사용할 수 없도록 해야 함
//두개의 스레드가 교대로 번갈아 가며 실행해야 하는 경우 사용된다

//start() 는 스레드를 바로 실행시키지 않고 대기 상태로 올려놓는 메서드이다
//OS 스케줄러에 따라 스레드가 실행되었다가 실행 대기 상태를 반복한다
//그러나 실행대기상태가 아닌 일시정지 상태로 가게 만들 수 있는데
//다른 쓰레드가 신호를 줘야만 한다(notify(), notifyAll())
//이러면 일시정지 상태에서 실행대기 상태로 넘어간다

//스레드 상태를 알기 위해 쓰는 메서드가 getState() 이다
public class ThreadSynState extends Thread{
    private Thread targetThread;

    public ThreadSynState(Thread targetThread) {
        this.targetThread = targetThread;
    }
    //생성자를 매개값으로 받은 타겟 스레드를 0.5초 주기로 출력합니다.
    public void run() {
        while (true) {
            Thread.State state = targetThread.getState();
            System.out.println("타겟 스레드 상태 : "+state);

            if(state == State.NEW) {
                targetThread.start();
            }
            if(state == State.TERMINATED) {
                break;
            }
            try{
                Thread.sleep(500);
            }catch (Exception e){}
        }
    }
}
class TargetThread extends Thread{
    //첫번째 for 문에서 10억번 반복해서 Runnable 상태를 유지하고
    public void run() {
        for( long i=0; i<1000000000; i++){}
        try{ //sleep()를 호출하여 TIME_Waiting상태를 유지함
            Thread.sleep(1500);
        }catch (Exception  e){}
        for(long i = 0; i<1000000000; i++) {}
        //다시 10억번 반복해서 runnable상태 유지
    }
}

class Main5{
    public static void main(String[] args) {
        ThreadSynState synState = new ThreadSynState(new TargetThread());
        synState.start();
    }
}
