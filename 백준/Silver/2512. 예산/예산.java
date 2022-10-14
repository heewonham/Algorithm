import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = 0, max = 0, sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] money = new int[n];
        for(int i = 0; i < n; i++){
            money[i] = Integer.parseInt(st.nextToken());
            max = Math.max(money[i], max);
            sum += money[i];
        }
        int maxMoney = Integer.parseInt(br.readLine());

        if(sum <= maxMoney){
            System.out.println(max);
            return;
        }

        // 이분 탐색
        int s = 0, e = max;
        while(s < e){
            int mid = (s+e)/2;
            sum = 0;
            for(int i = 0; i < n; i++){
                sum += Math.min(money[i], mid);
                if(sum > maxMoney){
                    e = mid;
                    break;
                }
            }

            if(sum <= maxMoney){
                answer = Math.max(answer, mid);
                s = mid+1;
            }
        }

        System.out.println(answer);
    }
}
