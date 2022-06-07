package codestatePrac.Brute;

public class BrutePrac {
    public static void main(String[] args) {
        BrutePrac b = new BrutePrac();
        boolean answer =b.Method("abcabcdacd", "abcd");
        System.out.println(answer);
    }


    public boolean Method(String a, String pattern) {
        //길이가 n인 전체 문자열과 길이가 m인 문자열 패턴을 포함하는지 검색

        //먼저 둘다 배열로 만들어버리기
        String[] arr = a.split("");
        String[] patternArr = pattern.split("");
        //int count = 0;
        int count = 0;
        //for 문 돌리기 i=0; i-pattern.length
        for(int i = 0; i< arr.length-patternArr.length;i++) {
            //count = 0;
            count = 0;
            //for문 안에 하나 더 돌리기
            for(int k = 0; k< patternArr.length;k++) {
                //pattern[j]와 a[i]가 일치한지 확인
                if(patternArr[k].equals(arr[i+k]) ) count++;
                //pattern[j+1] == a[i+1]
                //pattern[j+2] == a[i+2]
                //pattern[j+3] == a[i+3]
                //pattern[j+4] == a[i+4]
                // -> j는 패턴의 길이만큼 반복해야 하므로 a[i+j]이어야 함
                //일치할 때마다 count++한다
            }
            if(count == patternArr.length) return true;
            //만약 count == pattern이면 return true
        }
        return false;
    }
}
