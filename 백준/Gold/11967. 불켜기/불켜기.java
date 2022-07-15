import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] mr = {0,0,1,-1}, mc = {1,-1,0,0};
    static class Pos{
        private List<Integer> rList = new ArrayList<>();
        private List<Integer> cList = new ArrayList<>();
        int getSize(){ return rList.size(); }
        int getR(int i){ return rList.get(i); }
        int getC(int i){ return cList.get(i); }
        void setR(int i){ rList.add(i); }
        void setC(int i){ cList.add(i); }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Pos[][] pos = new Pos[n][n];
        // init
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                pos[i][j] = new Pos();
            }
        }

        // input
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            pos[r][c].setR(Integer.parseInt(st.nextToken())-1);
            pos[r][c].setC(Integer.parseInt(st.nextToken())-1);
        }

        int[][] room = new int[n][n];
        System.out.println(lightOn(n, pos, room));
    }
    static int lightOn(int n, Pos[][] pos, int[][] room){
        int answer = 1;
        boolean[][] visited = new boolean[n][n];
        Queue<Integer> q = new LinkedList<>();

        visited[0][0] = true;
        room[0][0] = 1;
        q.add(0); q.add(0);

        while(!q.isEmpty()){
            int cr = q.poll();
            int cc = q.poll();

            for(int i = 0; i < pos[cr][cc].getSize(); i++){
                int nr = pos[cr][cc].getR(i);
                int nc =  pos[cr][cc].getC(i);
                // 불 킬 수 있는 곳은 불키기
                if(room[nr][nc] == 0){
                    room[nr][nc] = 1;
                    answer++;
                }
            }

            // 예전에 방문할 수 없었지만 지금은 방문 가능한가?
            bfs(n, room, visited, q);
        }
        return answer;
    }
    static void bfs(int n, int[][] room, boolean[][] originVisited, Queue<Integer> originQ){

        boolean[][] visited = new boolean[n][n];
        Queue<Integer> q = new LinkedList<>();
        visited[0][0] = true;
        q.add(0); q.add(0);

        while(!q.isEmpty()){
            int curR = q.poll();
            int curC = q.poll();
            if(!originVisited[curR][curC]){
                originVisited[curR][curC] = true;
                originQ.add(curR);
                originQ.add(curC);
            }

            for(int i = 0 ; i <4; i++){
                int nr = curR + mr[i];
                int nc = curC + mc[i];
                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if(!visited[nr][nc] && room[nr][nc] == 1){
                    visited[nr][nc] = true;
                    q.add(nr);
                    q.add(nc);
                }
            }
        }
    }
}