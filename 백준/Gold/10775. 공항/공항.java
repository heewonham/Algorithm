import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        // init
        int[] par = new int[g+1];
        int[] order = new int[p+1];
        for(int i = 1; i <= g; i++)
            par[i] = i;

        // input
        for(int i = 1; i <= p; i++){
            order[i] = Integer.parseInt(br.readLine());
        }

        // output
        int answer = 0;
        boolean[] visited = new boolean[g+1];
        visited[0] = true;
        for(int i = 1 ; i <= p; i++){
            int idx = findParent(par, order[i]);
            if(visited[idx]) break;
            visited[idx] = true;
            par[idx] = idx-1;
            answer++;
        }
        System.out.println(answer);
    }
    static int findParent(int[] par, int n){
        if(par[n] == n) return n;
        return par[n] = findParent(par, par[n]);
    }
}