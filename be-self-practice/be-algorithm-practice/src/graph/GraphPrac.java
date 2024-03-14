package codestatePrac.graph;

public class GraphPrac {
    public int[][] createMatrix(int[][] edges) {
        // TODO:
        int edgesMax = 0;
        for(int i = 0; i< edges.length;i++) {
            for(int j = 0; j< edges[i].length;j++) {
                if(edgesMax<edges[i][j]) {
                    edgesMax = edges[i][j];
                }
            }
        }
        int[][] arr = new int[edgesMax+1][edgesMax+1];

        for(int i = 0; i<edgesMax+1;i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            if(edges[i][2] == 0) {
                arr[from][to] = 1;
                arr[to][from] = 0;
            }
            else if(edges[i][2] == 1) {
                arr[from][to] = 1;
                arr[to][from] = 1;
            }
        }
        return arr;
    }

}
