import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 1. init
        List<Integer>[] map = new List[n];
        int[] par = new int[n];
        for(int i = 0 ; i < n; i++){
            map[i] = new ArrayList<>();
            par[i] = i;
        }

        // 2. input
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n; j++){
                int c = Integer.parseInt(st.nextToken());
                if(c == 1){
                    map[i].add(j);
                }
            }
        }

        int[] trip = new int[m];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            trip[i] = Integer.parseInt(st.nextToken())-1;
        }

        // find par
        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < map[i].size(); j++){
                union(par, i, map[i].get(j));
            }
        }

        int s = par[trip[0]];
        for(int i = 1; i < m; i++){
            if(s != par[trip[i]]){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    static void union(int[] par, int a, int b){
        int parA = find(par, a);
        int parB = find(par, b);

        if(parA == parB) return;

        if(parA < parB){
            par[parB] = parA;
        }else{
            par[parA] = parB;
        }
    }
    static int find(int[] par, int n){
        if(par[n] == n) return n;
        return par[n] = find(par, par[n]);
    }
}