package codestatePrac.InterFacePrac;

public class InterFaceTest implements InterFacePrac{
    int id;
    String name;
    int math;
    int korean;
    int english;
    int average;

    int[] score = new int[]{math, korean, english};
    public InterFaceTest(int id, int math,int korean, int english) {
        this.id = id;
        this.korean = korean;
        this.math = math;
        this.english = english;
    }

    @Override
    public int average() {
        average = (math+korean+english)/3;
        System.out.println(id+"님의 평균은"+average);
        return average;
    }

    @Override
    public boolean isPassed() {
        if(average >60) {
            System.out.println(id+"님은 통과하셨습니다.");
            return true;
        }
        else{
            System.out.println(id+"님은 떨여졌습니다.");
        return false;}
    }

}
class Test{
    public static void main(String[] args) {
        InterFaceTest a = new InterFaceTest(12,88,55,44);
        a.average();
        a.isPassed();
    }
}
