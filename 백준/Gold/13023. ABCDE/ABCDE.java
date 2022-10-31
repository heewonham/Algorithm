import javax.swing.plaf.basic.BasicBorders;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // init
        List<Integer>[] friends = new List[n];
        for(int i = 0; i < n; i++){
            friends[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }

        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            visited[i] = true;
            if(isABCED(friends, visited, i, 1)){
                System.out.println(1);
                return;
            }
            visited[i] = false;
        }
        System.out.println(0);
    }
    static boolean isABCED(List<Integer>[] f, boolean[] visited, int s, int cnt){
        if(cnt >= 5) return true;

        for(int i = 0; i < f[s].size(); i++){
            int next = f[s].get(i);
            if(!visited[next]){
                visited[next] = true;
                if(isABCED(f, visited, next,cnt+1)) {
                    return true;
                }
                visited[next] = false;
            }
        }
        return false;
    }
}
