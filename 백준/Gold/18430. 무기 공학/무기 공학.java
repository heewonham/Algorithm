import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, max = 0;
    static int[] wingR1 = {0,0,0,0}, wingC1 = {1,-1,1,-1};
    static int[] wingR2 = {1,1,-1,-1}, wingC2 = {0,0,0,0};
    static class Pos{
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
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
        bfs(visited, power, map,0,0);
        System.out.println(max);
    }
    static void bfs(boolean[][] visited, int[][][] power, int[][] map, int r,int sum){

        if(sum > max) max = sum;

        for(int i = r; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j]) continue;
                for(int k =0 ; k < 4; k++){
                    Pos pos1 = new Pos(i + wingR1[k],j + wingC1[k]);
                    Pos pos2 = new Pos(i + wingR2[k], j + wingC2[k]);
                    if(isPossible(visited, pos1,pos2)){
                        makeVisited(visited, i, j, pos1, pos2, true);
                        bfs(visited, power, map, i, sum+makePower(power, map, i, j, pos1, pos2, k));
                        makeVisited(visited, i, j, pos1, pos2,false);
                    }
                }
            }
        }
    }
    static boolean isPossible(boolean[][] visited, Pos p1, Pos p2){
        if(p1.r < 0 || p1.r >= n || p1.c < 0 || p1.c >= m) return false;
        if(p2.r < 0 || p2.r >= n || p2.c < 0 || p2.c >= m) return false;
        return !visited[p1.r][p1.c] && !visited[p2.r][p2.c];
    }
    static void makeVisited(boolean[][] visited, int r, int c, Pos p1, Pos p2, boolean bool){
        visited[p1.r][p1.c] = bool;
        visited[r][c] = bool;
        visited[p2.r][p2.c] = bool;
    }
    static int makePower(int[][][] power, int[][] map, int r, int c, Pos p1, Pos p2, int dir){
        if(power[r][c][dir] != 0) return power[r][c][dir];
        return power[r][c][dir] = map[p1.r][p1.c] + map[p2.r][p2.c] + (map[r][c] * 2);
    }
}