package codestatePrac.GreedyPrac;

public class Greedy2 {
    public int partTimeJob(int k) {
        // TODO:
        int count =0;
        int sub = 0;
        count += k/500;
        sub = k%500;
        count += sub/100;
        sub = sub%100;
        count += sub/50;
        sub = sub%50;
        count += sub/10;
        sub = sub%10;
        count +=sub/5;
        sub = sub%5;
        count +=sub/1;

        return count;
        //야 이거 맞는거냐
        //ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ


    }
}
class TestCase{
    public static void main(String[] args) {
        Greedy2 g = new Greedy2();
        int a = g.partTimeJob(4512);
        System.out.println(a);
    }
}
