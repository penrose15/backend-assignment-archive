package codestatePrac.simulation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Simul {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //N 을 입력한다
        int n = sc.nextInt();
        sc.nextLine(); // 한줄 넘어간다
        //처음 시작점을 설정한다 x = 1, y = 1
        int x = 1; int y = 1;
        //문자열을 입력하고 split(" ")로 나눈 후 char배열로 만든다(ch[])
        String str = sc.nextLine();
        String[] ch = str.split(" ");
//        for(int i = 0; i<ch.length;i++) {
//            System.out.println(ch[i]);
//        }

        //dx = [-1, 1, 0, 0]을 만들고
        int[] dx = new int[]{-1, 1, 0, 0};
        //dy = [0, 0, -1, 1]을 만든다
        int[] dy = new int[]{0, 0, -1, 1};
        //문자 배열을 하나 만든다 char[] arr = [L, R, U, D]
        String[] arr1 = new String[]{"L", "R", "U", "D"};
        //char 배열과String 배열의 차이로 인해 삽질 함... 결국 String 배열로 다 바꾸고 for 문 안에서 리스트로 바꿔 버린 다음 indexOf 사용하여 해결
        for(int i = 0; i< ch.length;i++) {
        //for문을 아까 만든 char 배열 길이만큼 반복시킨다
            //nx = -1, ny = -1로 설정한다
            int nx = -1; int ny = -1; // 왜 -1로 설정하는 이유를 모르겠다 
            //arr.indexOf(ch[i])을 구하여 arr에 해당하는 인덱스를 구한 후 그 인덱스를
            List<String> arr22 = Arrays.asList(arr1);
            int v = arr22.indexOf(ch[i]);

            //dx[인덱스], dy[인덱스]에 넣어
            //nx = x+dx[인덱스]; ny = y+ dy[인덱스];로 x, y 값을 구한다
            nx = x+dx[v]; ny = y + dy[v];
            //만약 구해진 nx, ny값이 1보다 작거나 N보다 크면 지나간다.
            if(nx<1 || ny<1 || nx>n || ny >n) continue;
            //x = nx, y = ny를 구한다.
            x = nx; y = ny;
        }
        //x, y값을 출력한다.
        System.out.println(x+" "+y);


        }


    }


