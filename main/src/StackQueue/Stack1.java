package codestatePrac.StackQueue;

import java.util.ArrayList;
import java.util.Stack;

public class Stack1 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        //integer형 스택 선헌

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        //1 <- 2 <- 3 <- 4

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        //4 3 2 1
        //한번에 하나씩만 넣고 뺄 수 있음
        //하나의 입출력 방향(프링글스 같음)
        //예시 : 브라우저 앞뒤로 이동

        Solution s = new Solution();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);

        System.out.println(s.show());
        s.pop();
        s.pop();

        System.out.println(s.show());
        //스택 기능 구현 가능은 하나 너무 불편함....
    }
}

class Solution {
    private ArrayList<Integer> listStack = new ArrayList<Integer>();

    public void push(Integer data) {
        listStack.add(data);
    }

    public Integer pop() {
        if(listStack.size() == 0) {
            return null;
        }else {
            return listStack.remove(listStack.size()-1);
        }
    }

    public int size() {
        return listStack.size();
    }

    public Integer peek() {
        if(listStack.size() == 0) {
            return null;
        }else {
            return listStack.get(listStack.size()-1);
        }
    }

    public String show() {
        return listStack.toString();
    }
    public void clear() { listStack.clear(); }
}

