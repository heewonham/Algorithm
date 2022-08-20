import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // init
        List<Integer>[] up = new List[n+1];
        List<Integer>[] down = new List[n+1];
        for(int i = 1; i <= n; i++){
            up[i] = new ArrayList<>();
            down[i] = new ArrayList<>();
        }

        // input
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            up[a].add(b);
            down[b].add(a);
        }

        // find
        int answer = 0;
        for(int i = 1; i <= n; i++){
            int cnt = find(n, i, up) + find(n, i, down);
            if(cnt == n-1) answer++;
        }

        System.out.println(answer);
    }
    static int find(int n, int idx, List<Integer>[] lists){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();

        q.add(idx);
        visited[idx] = true;
        int cnt = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i = 0; i < lists[cur].size(); i++){
                int next = lists[cur].get(i);
                if(!visited[next]){
                    visited[next] = true;
                    cnt++;
                    q.add(next);
                }
            }
        }

        return cnt;
    }
}