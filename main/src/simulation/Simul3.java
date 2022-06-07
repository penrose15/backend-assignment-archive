package codestatePrac.simulation;

public class Simul3 {
    public Integer boardGame(int[][] board, String operation) {
        // TODO:
        //일단 문자열을 쪼개 배열로 만든다
        String[] arr = operation.split("");
        //dx = 0, dy = 0
        int dx = 0; int dy = 0;
        //start = board[dx][dy]
        int start = board[dx][dy];
        //score = 0
        int score = 0;
        //try
        try {
            //for문을 배열길이만큼 돌린다
            for (int i = 0; i < arr.length; i++) {
                //if(L ) dx -=1 score +=board[dx][dy]
                if(arr[i].equals("L")){
                    dy-=1;
                    score +=board[dx][dy];
                }
                //if(R ) dx+=1 score +=board[dx][dy]
                else if (arr[i].equals("R")) {
                    dy+=1;
                    score +=board[dx][dy];
                }
                //if(U ) dy-=1 score +=board[dx][dy]
                else if (arr[i].equals("U")) {
                    dx-=1;
                    score +=board[dx][dy];
                }
                //if(D ) dy+=1 score +=board[dx][dy]
                else if (arr[i].equals("D")) {
                    dx+=1;
                    score +=board[dx][dy];
                }
            }
        }
        //catch(ArrayIndexOutOfBoundsExcetion e)
        catch (ArrayIndexOutOfBoundsException e) {
            //return null
            return null;
        }
        //return score
        return score;
    }
}
class Simul3Test{
    public static void main(String[] args) {
        Simul3 s = new Simul3();
        int[][] board1 = new int[][]{
                {0, 0, 0, 1},
                {1, 1, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 0}
        };
        int output1 = s.boardGame(board1, "RRDLLD");
        System.out.println(output1);
    }
}
