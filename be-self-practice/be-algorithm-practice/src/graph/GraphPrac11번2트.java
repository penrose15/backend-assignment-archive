package codestatePrac.graph;

import java.util.Scanner;

public class GraphPrac11번2트 {
//    public boolean getDirections(int[][] matrix, int from, int to) {
//        // TODO:
//        boolean[] a= new boolean[matrix.length];
//    }
public static void main(String[] args) {
    GraphPrac11번2트 aa = new GraphPrac11번2트();

    System.out.println(aa.a(20));
}

    public int a (int n) {
        if(n == 0) return 0;
        else if(n ==1 || n ==2) return 1;
        else {
            return a(n-1)+a(n-2);
        }

    }
}
