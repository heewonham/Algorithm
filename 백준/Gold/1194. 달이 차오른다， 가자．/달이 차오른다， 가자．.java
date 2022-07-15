import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] mr = {0,0,1,-1}, mc = {1,-1,0,0};
    static class Pos{
        int r, c, cnt, keys = 0;
        public Pos(int r, int c, int cnt, int key){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.keys = key;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] map = new String[n];
        Pos start = null;
        for(int i = 0; i < n; i++){
            map[i] = br.readLine();
            if(map[i].contains("0")){
                int c = map[i].indexOf('0');
                start = new Pos(i,c,0,0);
            }
        }
        System.out.println(bfs(map,start,n,m));
    }
    static int bfs(String[] map, Pos s, int n, int m){

        boolean[][][] visited = new boolean[n][m][1<<6];
        Queue<Pos> q = new LinkedList<>();
        q.add(s);

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nr = cur.r + mr[i], nc = cur.c + mc[i];
                // 갈 수 없음
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                char next = map[nr].charAt(nc);
                if(next == '#' || visited[nr][nc][cur.keys]) continue; // 예전에도 똑같은 키를 가지고 방문했었다면 제외
                // 탈출
                if(next == '1') return cur.cnt + 1;

                if(next >= 'a' && next <= 'f'){
                    visited[nr][nc][cur.keys| 1 << next - 'a'] = true;
                    q.add(new Pos(nr, nc, cur.cnt+1, cur.keys | 1 << next -'a'));
                }
                else if(next >= 'A' && next <= 'F'){
                    if((cur.keys & 1 << next-'A') != 0 ){
                        visited[nr][nc][cur.keys| 1 << next - 'A'] = true;
                        q.add(new Pos(nr, nc, cur.cnt + 1, cur.keys));
                    }
                }
                else{
                    visited[nr][nc][cur.keys] = true;
                    q.add(new Pos(nr, nc, cur.cnt+1, cur.keys));
                }
            }
        }
        return -1;
    }
}