import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // init
        List<Integer>[] child = new List[n+1];
        List<Integer>[] par = new List[n+1];
        for(int i = 1; i <= n; i++){
            child[i] = new ArrayList<>();
            par[i] = new ArrayList<>();

        }

        // input
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            child[a].add(b);
            par[b].add(a);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            boolean[] visited = new boolean[n+1];
            int up = find(par, visited, i);
            visited = new boolean[n+1];
            int down = find(child, visited, i);
            int ans = n-(up + down)-1;
            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }
    static int find(List<Integer>[] conn, boolean[] v, int idx){

        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        int cnt = 0;
        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i = 0; i < conn[cur].size(); i++){
                int next = conn[cur].get(i);
                if(!v[next]){
                    v[next] = true;
                    q.add(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}