package codestatePrac;

import java.util.ArrayList;

public class Main48{

        public static int subsetSum(int[] set, int bound) {
                // TODO:
                boolean[] arr = new boolean[301];
                int max = 0;
                for(int member : set) {
                        ArrayList<Integer> reach = new ArrayList<>();

                        for(int wanted = 1; wanted<=bound - member; wanted++) {
                                if(arr[wanted]) {
                                        int reached = wanted+member;
                                        reach.add(reached);
                                        if(reached>max) max = reached;
                                }
                        }
                        for(int data : reach) arr[data] = true;
                        if(member<=bound) {
                                arr[member] = true;
                                if(member>max) max = member;
                        }
                }return max;

        }
}
