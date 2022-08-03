import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, max = 0;
    static int[] wingR1 = {0,0,0,0}, wingC1 = {1,-1,1,-1};
    static int[] wingR2 = {1,1,-1,-1}, wingC2 = {0,0,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // init
        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int [][][] power = new int[n][m][4];
        boolean[][] visited = new boolean[n][m];
        bfs(visited, power, map,0,0,0);
        System.out.println(max);
    }
    static void bfs(boolean[][] visited, int[][][] power, int[][] map, int r, int c, int sum){

        if(sum > max) max = sum;

        for(int i = r; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j]) continue;
                for(int k =0 ; k < 4; k++){
                    if(isPossible(visited, i,j,k)){
                        makeVisited(visited, i, j, k, true);
                        bfs(visited, power, map, i,j, sum+makePower(power, map, i,j,k));
                        makeVisited(visited, i, j, k, false);
                    }
                }
            }
        }
    }
    static boolean isPossible(boolean[][] visited, int r, int c, int dir){
        int r1 = r + wingR1[dir], c1 = c + wingC1[dir];
        int r2 = r + wingR2[dir], c2 = c + wingC2[dir];
        if(r1 < 0 || r1 >= n || c1 < 0 || c1 >= m) return false;
        if(r2 < 0 || r2 >= n || c2 < 0 || c2 >= m) return false;
        return !visited[r1][c1] && !visited[r2][c2];
    }
    static void makeVisited(boolean[][] visited, int r, int c, int dir, boolean bool){
        int r1 = r + wingR1[dir], c1 = c + wingC1[dir];
        int r2 = r + wingR2[dir], c2 = c + wingC2[dir];
        visited[r1][c1] = bool;
        visited[r][c] = bool;
        visited[r2][c2] = bool;
    }
    static int makePower(int[][][] power, int[][] map, int r, int c, int dir){
        if(power[r][c][dir] != 0) return power[r][c][dir];
        int r1 = r + wingR1[dir], c1 = c + wingC1[dir];
        int r2 = r + wingR2[dir], c2 = c + wingC2[dir];

        return power[r][c][dir] = map[r1][c1] + map[r2][c2] + (map[r][c] * 2);
    }
}