package codestatePrac.graph;

import java.util.Arrays;

public class GraphPrac11DFS {
    public boolean getDirections(int[][] matrix, int from, int to) {
        // TODO: 일단 문제는 풀었으나... 아직 굳이 새로운 2차원 배열을 만들어야 하는지 모르겠음...ㅜㅜ
        //boolean[] visited = new boolean[matrix.length];
        //visited[from] = true;
        //1. 새로운 배열을 만든다
        int[][] newArr = new int[matrix.length][];
        for(int i = 0; i< matrix.length;i++) {
            newArr[i] = Arrays.copyOf(matrix[i],matrix[i].length); //여기 부분 이해 안됨 결국 matrix와 똑같은 배열 아닌가...? 왜 내가 했을때는 안되지
        }
        if(from == to) return  true;
        for(int i = 0; i< matrix.length;i++) {

            if(matrix[from][i] == 1 ) {
                matrix[from][i] =0;
                getDirections( matrix, i, to);

            }
        }
        return false;

    }
}
