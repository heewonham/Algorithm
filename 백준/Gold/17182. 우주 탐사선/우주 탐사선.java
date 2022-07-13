import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int INF = 100001;
    static int n = 0, ans = INF;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        // init
        int[][] dist = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 각 거리의 최소값을 구하고
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                for(int k = 0; k < n; k++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        // 모든 경우의 수를 판단의 수 최소를 판단
        findMin(dist, start,1,1 << start, 0);
        System.out.println(ans);
    }
    static void findMin(int[][] dist, int idx, int cnt, int bit, int sum){
        if(cnt == n){
            ans = Math.min(sum, ans);
            return;
        }

        for(int i = 0; i < n; i++){
            if(i == idx) continue;
            if((bit & (1 << i)) == 0){
                findMin(dist, i, cnt+1, (bit | (1<<i)), sum + dist[idx][i]);
            }
        }
    }

}