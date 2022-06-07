package codestatePrac.Brute;

import java.util.Scanner;

public class SelectSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] arr = str.split("");
        int[] num = new int[arr.length];
        for(int i = 0; i< arr.length;i++) {
            num[i] = Integer.parseInt(arr[i]);
        }
        //
        //for int i = 0; i<num.length-1;i++
        for(int i = 0; i<num.length-1;i++) {
            //i+1 부터 끝까지 탐색
            int min = i;
            //min = i
            //for int j = i+1; j< num.length;j++
            for(int j = i+1; j<num.length;j++) {
                //if(num[min] > num[j) 이면 min = j
                if(num[min] > num[j]) min = j;
            }
            //min과 num[i]자리 바꿔야 함 temp = num[i]; num[i] = num[j]; num[j] = temp
            int temp = num[i]; num[i] = num[min]; num[min] = temp;
            //
        }
        for(int k : num) {
            System.out.print(k);
        }
    }

}
