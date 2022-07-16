import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // 1. init
        List<Integer>[] child = new List[n];
        int[] count = new int[n];
        int[] weigth = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            child[i] = new ArrayList<>();
        }

        // 2. input
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            // 건물을 짓는데 걸리는 시간
            weigth[i] = Integer.parseInt(st.nextToken());
            // 내가 필요한 건물들
            while (true) {
                int s = Integer.parseInt(st.nextToken());
                if (s == -1) break;
                child[s - 1].add(i);
                count[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        // 3. 부모인 노드 찾기
        for (int i = 0; i < n; i++) {
            if (count[i] == 0) {
                dp[i] = weigth[i];
                q.add(i);
            }
        }
        
        // 4. 내려가기
        while (!q.isEmpty()) {
            int idx = q.poll();

            for (int i = 0; i < child[idx].size(); i++) {
                int next = child[idx].get(i);
                dp[next] = Math.max(dp[next], dp[idx] + weigth[next]);

                if(--count[next] == 0){
                    q.add(next);
                }
            }
        }

        // 5. answer
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(dp[i] + "\n");
        }
        System.out.println(sb);
    }
}