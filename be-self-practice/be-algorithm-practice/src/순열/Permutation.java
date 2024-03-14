package codestatePrac.순열;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
    public static ArrayList<String[]> combinationLoop() {///for 문을 이용한 조합
        String[] lookup = new String[] {"A", "B", "C", "D", "E"};
        ArrayList<String[]> result = new ArrayList<>();

        for(int i = 0; i<lookup.length;i++) {
            for(int j = i+1; j< lookup.length;j++) {
                for(int k= j+1; k< lookup.length;k++) {
                    String[] input = new String[]{lookup[i], lookup[j], lookup[k]};
                        result.add(input);
                }
            }
        }return result;
    }
    public static ArrayList<String[]> permutationLoop() { //for을 이용한 순열열
       String[] lookup = new String[]{"A", "B", "C", "D", "E"};
        ArrayList<String[]> result = new ArrayList<>();

        for(int i = 0; i< lookup.length;i++) {
            for(int j = 0; j< lookup.length;j++) {
                for (int k=0; k< lookup.length;k++) {
                    if(i == j || j==k||  k == i) continue;
                    String[] input = new String[]{lookup[i], lookup[j], lookup[k]};
                    result.add(input);

                }
            }
        }return result;
    }

    public static ArrayList<String> reculsion(List<String> arr, int n, int r) {
        ArrayList<String> result = new ArrayList<>();
        if(r == 0) {
            return result;
        }
        for(int i = 0; i<n; i++) {
            result.add(arr.remove(i));
            reculsion(arr, n-1, r-1);
            arr.add(i, result.remove(result.size()-1));
        }
        return result;
    }
}
