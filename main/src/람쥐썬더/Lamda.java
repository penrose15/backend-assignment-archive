package codestatePrac.람쥐썬더;

interface MyLambda{
    int max(int x,int y);
}

public class Lamda {
    public static void main(String[] args) {
        //기존의 익명 함수
        System.out.println(new MyLamdaFunction() {
            public int max(int a, int b) {
                return a>b ? a : b;
            }
        }.max(3,5));

        //람다식을 이용한 익명함수
        MyLambda a = (int x, int y) -> x > y ? x : y;
        System.out.println(a.max(3,8));
    }
}
