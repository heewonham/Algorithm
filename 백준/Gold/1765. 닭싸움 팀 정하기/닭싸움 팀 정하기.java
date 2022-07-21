import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());


        List<Integer>[] em = new List[n];
        int[] par = new int[n];

        // init
        for(int i = 0; i < n; i++){
            em[i] = new ArrayList<>();
            par[i] = i;
        }

        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean isFriend = st.nextToken().equals("F");
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            if(isFriend){
                union(par, a, b);
            }else{
                em[a].add(b);
                em[b].add(a);
            }
        }

        // 적의 적 찾기
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < em[i].size(); j++){
                int idx = em[i].get(j);
                for(int k = 0; k < em[idx].size(); k++){
                    union(par, i, em[idx].get(k));
                }
            }
        }


        boolean[] visited = new boolean[n];
        int answer = 0;
        for(int i = 0 ; i < n; i++){
            int v = find(par, i);
            if(!visited[v]){
                answer++;
                visited[v] = true;
            }
        }
        System.out.println(answer);
    }
    static boolean union(int[] par, int a, int b){
        int pa = find(par, a);
        int pb = find(par, b);

        if(pa == pb) return true;

        if(pa < pb){
            par[pb] = pa;
        }else{
            par[pa] = pb;
        }
        return true;
    }
    static int find(int[] par, int n){
        if(par[n] == n) return n;
        return par[n] = find(par, par[n]);
    }
}