package codestatePrac.순열;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DD {
    public ArrayList<Integer[]> newChickenRecipe(int[] stuffArr, int choiceNum) {
        // TODO:
        List<Integer> list = Arrays.stream(stuffArr).boxed().collect(Collectors.toList());

        if (list.size() < choiceNum) {
            return null;
        }
        for (int i = 0; i<list.size();i++) {
            String str = String.valueOf(list.get(i));
            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    count++;
                }
            }
            if (count >= 3) {
                list.remove(list.get(i));
            }
        }
        if (list.size() == 0) {
            return null;
        }

        Integer[] stuffbox = list.toArray(new Integer[list.size()]);
        ArrayList<Integer[]> result = new ArrayList<>();
        boolean[] visited = new boolean[stuffbox.length+1];


        return youAreChicken(stuffbox, new Integer[]{}, choiceNum, result, visited);

    }

    public ArrayList<Integer[]> youAreChicken(Integer[] stuff, Integer[] beer, int choiceNum, ArrayList<Integer[]> result, boolean[] visited){
        if(choiceNum == 0){
            result.add(beer);
            return result;
        }


        for(int i = 0; i<stuff.length;i++) {
            Integer cake = stuff[i];

            if(!visited[i]) {
                visited[i] = true;
                Integer[] bread = Arrays.copyOf(beer, beer.length + 1);
                bread[bread.length - 1] = cake;

                youAreChicken(stuff, bread, choiceNum - 1, result, visited);
                visited[i] = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DD aaa = new DD();
        ArrayList<Integer[]> output1 = aaa.newChickenRecipe(new int[]{11, 1, 10, 1111111111, 10000}, 4);
        for (Integer[] integers : output1) {
            System.out.println(Arrays.toString(integers));
        }

    }
}
