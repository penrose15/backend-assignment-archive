package codestatePrac.GreedyPrac;

import java.util.Scanner;

public class GreedyPrac2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        GreedyAlgo2 ga = new GreedyAlgo2();
        System.out.println(ga.method(num));
    }
}

class GreedyAlgo2 {
    public int method(String num) {
        //문자열을 쪼개 숫자배열로 만든다
        String[] str = num.split("");
        int[] arr = new int[num.length()];
        for(int i = 0; i<str.length;i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        //int result = 1을 할당한다
        int result = 1;
        //for문으로 배열의 길이만큼 반복 시킨다
        for(int i = 0; i< arr.length;i++) {
            //만약 배열[i]가 0이라면
            if(arr[i] <=1 || result<=1) { //만약 둘 중 하나가 1보다 작으면 더하는게 이득이고
                //result에 더하고
                result += arr[i];
            }
            //그 외에는 곱한다.
            else { //둘다 2보다 크면 곱한다.
                result *=arr[i];
            }
        }
        //result를 반환한다.
        return result;

    }
}
