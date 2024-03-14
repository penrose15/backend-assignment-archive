package codestatePrac.graph;

import java.util.LinkedList;
import java.util.Queue;

public class GraphPrac11 {
    public boolean getDirections(int[][] matrix, int from, int to) {
        // TODO:
        int max = matrix.length;
        boolean[] visited = new boolean[max];
        visited[from] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);

        while(queue.size()>0) {
            int newFrom = queue.poll();//0 1
            if(newFrom == to) {
                return true;
            }
            for(int i = 0; i<matrix.length;i++) {
                if(matrix[newFrom][i] == 1 && !visited[i]) {//1
                    queue.add(i);
                    visited[i] = true;
                }
            }


        }return  false;

    }
}
