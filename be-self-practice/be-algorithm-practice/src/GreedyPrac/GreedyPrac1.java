package codestatePrac.GreedyPrac;

import java.util.Scanner;

public class GreedyPrac1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        GreedyAlgo gp = new GreedyAlgo();
        System.out.println(gp.method(num1, num2));

    }
}

class GreedyAlgo {
    public int method(int num1, int num2 ){
        //while( num1 이 1 초과일때)
        //if( num1%num2 ==0) 이라면
        //num1 = num1/num2 이다
        //이후 count를 +1  한다
        //else ( num1%num2 != 0이면)
        //num1에다가 1을 뺀다
        //count +1

/*
        이렇게 해도 되나 만약 숫자가 억단위라면... 컴터가 터진다
        while(num1 != 1) {
            if( num1%num2 ==0) {
                num1 = num1/num2;
                count++;
            }
            else{
                num1 = num1-1;
                count++;
            }
        }*/
        int count = 0;
        while (num1>1){
            int target = (num1/num2)*num2; //target를 설정하여 num2의 배수가 되도록 만듬
            count += num1-target; //나머지는 count에 더해 1을 빼야 하는 만큼 count 가 추가됨
            //만약 num1<num2여서 target이 0이 되면 count에 나머지가 더해짐
            num1 = target; 
            if(num1<num2) { //만약 num1이 더 작으면 while문 빠져 나감
                break;
            }
            num1 = num1/num2;
            count +=1;

        }count += num1-1; //num1이 0이 아니라 1이 될 때까지의 조건이므로 1을 뺌
        return count;
    }
}
