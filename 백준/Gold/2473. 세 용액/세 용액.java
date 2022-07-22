import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long MAX = 3000000000L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] potion = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            potion[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(potion);
        
        long min = MAX;
        long[] ans = new long[3];
        for(int fix = 0; fix < n-2; fix++){
            int l = fix+1, r = n-1;
            while(l < r){
                long num = potion[fix]+potion[l]+potion[r];
 
                if(Math.abs(num) <= min){
                    min = Math.abs(num);
                    ans[0] = potion[fix];
                    ans[1] = potion[l];
                    ans[2] = potion[r];
                }

                if(num > 0){ // 양수
                    r--;
                }else if (num < 0){ // 음수
                    l++;
                }else{
                    System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
                    return;
                }
            }
        }
        System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
    }
}