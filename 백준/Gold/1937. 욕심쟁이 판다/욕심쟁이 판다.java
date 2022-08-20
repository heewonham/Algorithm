import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] mr = {0,0,1,-1}, mc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // init
        int[][] map = new int[n][n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        int answer = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    visited[i][j] = true;
                    dp[i][j] = 1+findMove(visited, map, dp, n, i, j);
                }
                answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer);
    }

    static int findMove(boolean[][] v, int[][] map, int[][] dp, int n, int r, int c){
        if(dp[r][c] != 0) return dp[r][c];

        int max = 0;
        for(int i = 0; i < 4; i++){
            int nextR = r + mr[i];
            int nextC = c + mc[i];
            if(nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) continue;
            if(map[r][c] < map[nextR][nextC]){
                if(!v[nextR][nextC]){
                    v[nextR][nextC] = true;
                    dp[nextR][nextC] = 1 + findMove(v, map, dp, n, nextR, nextC);
                }
                max = Math.max(max, dp[nextR][nextC]);
            }

        }
        return max;
    }
}