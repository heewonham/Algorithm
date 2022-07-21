import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] mr = {0,0,1,-1}, mc = {1,-1,0,0};
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
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        // 1. init
        int[][] map = new int[n][n];
        int[][] road = new int[n][n];
        for(int i = 0 ; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int r1 =  Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 =  Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int pivot1R = r2-r1, pivot1C = c2-c1;
            int pivot2R = r1-r2, pivot2C = c1-c2;
            for(int j = 0 ; j < 4; j++){
                if(pivot1R == mr[j] && pivot1C == mc[j]){
                    road[r1-1][c1-1] |= 1 << j;
                }
                if(pivot2R == mr[j] && pivot2C == mc[j]){
                    road[r2-1][c2-1] |= 1 << j;
                }
            }
        }

        List<Pos> cow = new ArrayList<>();
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int r1 =  Integer.parseInt(st.nextToken())-1;
            int c1 = Integer.parseInt(st.nextToken())-1;
            cow.add(new Pos(r1,c1));
            map[r1][c1] = 1;
        }

        int answer = 0;
        for(int i = 0; i < k; i++){
            answer += bfs(road, map, n, cow.get(i), k);
        }

        System.out.println(answer/2);
    }
    static int bfs(int[][] road, int[][] map, int n, Pos start, int cnt){
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        q.add(start);
        visited[start.r][start.c] = true;

        while (!q.isEmpty()){
            Pos cur= q.poll();

            if(map[cur.r][cur.c] == 1){
                cnt--;
            }

            for(int i = 0; i < 4; i++){
                int nr = cur.r + mr[i];
                int nc = cur.c + mc[i];
                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                int bit = 1 << i;
                if(!visited[nr][nc] && (bit & road[cur.r][cur.c]) == 0){
                    visited[nr][nc] = true;
                    q.add(new Pos(nr,nc));
                }
            }
        }
        return cnt;
    }
}