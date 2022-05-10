import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int k = V-B;
        int l = A-B;
        int N = 0 ;
        if(V == A) {
            System.out.println(1);
        }
        else if((V - B)%(A-B) == 0){
            N = (V - B)/(A-B);
        }

        else{
            N = (V - B)/(A-B)+1;
        }
        System.out.println((10-3)/(5-2));
        System.out.println(N);

       /*
       day 1 = A
       day2 = A*2 - B
       day3 = A*3 -2B
       ....
       dayN = N*A - (N-1)B

       N일 낮까지 간 거리 : l = N*A - (N-1)B
                           = N*A - N*B + B
                           = N(A-B) +B
       그러니깐 l 이 V 보다 길면 되는 거이므로

       V > l
       (V - B)/(A-B)의 올림 =  N
       이렇게 구할 수 있다

        */
    }

}
