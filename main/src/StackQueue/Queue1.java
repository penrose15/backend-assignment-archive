package codestatePrac.StackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Queue1 {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1); //queue에 값 1 추가
        queue.add(2);
        queue.add(3);
        queue.add(4);
        /*출력 방향 <---------------<입력방향 (queue.add(데이터);)
        *           1 <- 2 <- 3 <- 4
        *               <----------------<
        * 들어간 순서대로 1번이 가장 먼저 들어가고 4번이 마지막으로 들어감
        * */
        System.out.println(queue.poll()); //queue에 첫번째 값을 반환하고 제거
        System.out.println(queue.poll()); //queue에 첫번째 값을 반환하고 제거
        System.out.println(queue.poll()); //queue에 첫번째 값을 반환하고 제거
        System.out.println(queue.poll()); //queue에 첫번째 값을 반환하고 제거

        /*출력 방향 <---------------<입력방향 (queue.poll();)
         *           1 <- 2 <- 3 <- 4
         *               <----------------<
         * 1부터 출력됨
         * */
    }
}
