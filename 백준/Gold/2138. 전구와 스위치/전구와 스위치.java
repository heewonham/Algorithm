import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int min = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] before = br.readLine().toCharArray();
        String after = br.readLine();

        // 0으로 시작할때
        simualte(before, after, 0, 0);

        // 1으로 시작할때
        simualte(before, after, 1, 0);

        System.out.println(min);
    }
    static void push(char[] be, int idx){
        if(idx-1 >= 0) be[idx-1] = (be[idx-1] == '0')? '1' : '0';
        be[idx] = (be[idx] == '0')? '1' : '0';
        if(idx+1 < be.length) be[idx+1] = (be[idx+1] == '0')? '1' : '0';
    }
    static void simualte(char[] be, String aft, int idx, int cnt){
        // idx -2가 원하는 값이 아니면 나가기
        if(idx-2 >= 0){
            if(be[idx-2] != aft.charAt(idx-2)) return;
        }

        // 마지막 idx인 경우 -> 버튼 여부에 따라 체크
        if(idx >= be.length){
            // after과 같은지
            if(String.valueOf(be).equals(aft)){
                if(min == -1 || min > cnt){
                    min = cnt;
                }
            }
            return;
        }

        // 누른다.
        push(be, idx);
        simualte(be, aft, idx+1, cnt+1);

        // 원상 복귀 + 안누른다.
        push(be, idx);
        simualte(be, aft, idx+1, cnt);
    }
}