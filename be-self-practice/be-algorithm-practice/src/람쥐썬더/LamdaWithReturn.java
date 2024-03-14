package codestatePrac.람쥐썬더;

public interface LamdaWithReturn {
    public int accept(int x, int y);
}

class Ex5{
    public static void main(String[] args) {
        LamdaWithReturn withReturn;
        withReturn = (x,y) -> {
            int result = x+y;
            return result;
        };
        int result1 = withReturn.accept(2,6);
        System.out.println(result1); // 8

        withReturn = (x, y) -> {return x+y;};
        int result2 = withReturn.accept(2,9);
        System.out.println(result2);

        withReturn = (x, y) -> x+y;
        int result3 = withReturn.accept(6,5);
        System.out.println(result3);

        withReturn = (x, y) ->sum(x,y);
        int result4 = withReturn.accept(2,5);
        System.out.println(result4);


    }
    static int sum(int x, int y) {
        return x+y;
    }
}
