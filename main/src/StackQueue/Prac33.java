package codestatePrac.StackQueue;

import java.util.*;
import java.util.stream.Collectors;

public class Prac33 {

    public static void main(String[] args) {
        Solution1 s= new Solution1();
        int arr[] = new int[] {7,4,5,6};
        System.out.println(s.queuePrinter(2,10,arr));

    }
}
class Solution1 {
    public int queuePrinter(int bufferSize, int capacities, int[] documents) {
        // TODO:

        List<Integer> list = Arrays.stream(documents).boxed().collect(Collectors.toList());

        Queue<Integer> 문서공간 = new LinkedList<Integer>();
        Queue<Integer> 작업공간 = new LinkedList<Integer>();

        Queue<Integer> 프린트 = new LinkedList<Integer>();

        int a = 문서공간.size();
        int count = 0;
        int sum = 0;
        //List<Integer> list = new ArrayList<>();
        //queue1     = documents 배열
        //queue      = 작업공간 큐
        //queuePrint = 프린터 큐

        for(int i = 0; i<documents.length; i++){
            문서공간.add(list.get(i));
        }
        for(int j = 0; j<documents.length; j++){
            프린트.add(0);
        }

        while(문서공간.size() != 0) {
            if(작업공간.size() == 0) {
                //1초 지남
                //sum = 문서공간.peek(); //document배열의 맨 처음 요소
                작업공간.add(문서공간.poll());
                //count++;
                //프린트.add(작업공간.poll()); // --> 뒤에서 또 할 것이므로 제외
            }

            if(작업공간.size()>=bufferSize) { //현재 작업 공간 수가 더 크면 초만 지나감
                count++;
                작업공간.add(문서공간.poll());
            }

            else if(작업공간.size() < bufferSize) //전체 용량이 여유 있을 경우
            {
                //queue의 현재 요소 사이즈가 capacities보다 작으면
                if(프린트.peek()+작업공간.peek() < capacities)
                {
                    count += (bufferSize);
                    프린트.add(작업공간.poll());
                }
                else
                {
                    count += (bufferSize);
                    작업공간.add(문서공간.poll());
                    프린트.add(작업공간.poll());
                }
            }
        }
        return count;
    }
}