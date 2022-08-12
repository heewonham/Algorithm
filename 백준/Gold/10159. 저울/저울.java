import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[][] conn = new boolean[n+1][n+1];
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            conn[a][b] = true;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(conn[i][k] &&  conn[k][j]){
                        conn[i][j] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            int cnt = 0;
            for(int j = 1; j <= n; j++){
                if(!conn[i][j] && !conn[j][i]) cnt++;
            }
            sb.append((cnt-1)+"\n");
        }

        System.out.println(sb);
    }
}