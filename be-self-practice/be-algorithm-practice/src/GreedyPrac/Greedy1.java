package codestatePrac.GreedyPrac;

import java.util.*;
import java.util.stream.Collectors;

public class Greedy1 {
    public int movingStuff(int[] stuff, int limit) {
        // TODO:
        int max = stuff[0];
        //배열 순서 내림차순으로 정렬
        for(int i = 0; i<stuff.length;i++) {
            for(int j = i+1; j<stuff.length;j++){
                if(stuff[i]<stuff[j]) {
                    int temp = stuff[i];
                    stuff[i] = stuff[j];
                    stuff[j] = temp;
                }
            }
        }
        int sum = 0;
        int count = 0;
        List<Integer> list = Arrays.stream(stuff).boxed().collect(Collectors.toList());
        while(list.size()>0) {
            for(int i = 0; i<list.size();i++) {
                int a = limit - list.get(i);
                int b = i;
                list.remove(b);
                for(int j = 0; j<list.size();j++) {
                    if(a >=list.get(j)) {

                        list.remove(j);
                        break;
                    }

                }
                count++;
            }
        }





        return count;

    }
}
class AAAA{
    public static void main(String[] args) {
        Greedy1 g = new Greedy1();
        int a=g.movingStuff(new int[]{60, 73, 80, 87, 103, 109, 119, 123, 128, 129, 136, 146, 153, 168, 182}, 200);
        System.out.println(a);
    }
}
