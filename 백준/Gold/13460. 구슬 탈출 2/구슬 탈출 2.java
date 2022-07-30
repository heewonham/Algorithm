import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/***
    1. visited [][][][] 4개로 해서 같은 상황이 발생하지 않도록
    2. red 안움직여도 blue가 이동하면 다른 가능성이 발견될 수 있음을 간과
    3. 10번 이상 움직이면 소용없다.
    관건 : 구슬 영역 지키기, visited, 조건들
 */

public class Main {
    static class Pos {
        int r, c, cnt;
        public Pos() { }
        public Pos(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof Pos){
                return this.r == ((Pos) o).r && this.c == ((Pos) o).c;
            }
            return super.equals(o);
        }
    }
    static int[] mr = {0,0,1,-1}, mc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] map = new String[n];
        Pos blue = new Pos();
        Pos red = new Pos();
        Pos end = new Pos();
        for(int i = 0; i < n; i++){
            map[i] = br.readLine();
            if(map[i].contains("B")){
                int c = map[i].indexOf("B");
                blue.r = i;
                blue.c = c;
            }
            if(map[i].contains("R")){
                int c = map[i].indexOf("R");
                red.r = i;
                red.c = c;
            }
            if(map[i].contains("0")){
                int c = map[i].indexOf("0");
                end.r = i;
                red.c = c;
            }
        }

        Queue<Pos> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][n][m];
        q.add(red);
        q.add(blue);
        while(!q.isEmpty()){
            Pos curRed = q.poll();
            Pos curBlue = q.poll();

            if(curRed.cnt >= 10) continue;

            for(int i = 0 ; i < 4; i++){
                Pos nextRed = new Pos(curRed.r, curRed.c, curRed.cnt + 1);
                Pos nextBlue = new Pos(curBlue.r, curBlue.c, curBlue.cnt + 1);
                boolean redMove = move(map, i, nextRed);
                boolean blueMove = move(map, i, nextBlue);

                if(redMove){ // red - 탈출
                    if(blueMove) continue;  // blue - 탈출 -> continue
                    else{
                        System.out.println(nextRed.cnt);
                        return;// blue - 탈출x -> 정답
                    }
                }else{ // red - 탈출 x,
                    if(nextRed.equals(nextBlue)){ // 순서맞추기
                        moreMove(i, curRed, nextRed, curBlue, nextBlue);
                    }
                    if(blueMove) continue;// red - 안 움직임 + blue 움직 x
                    else{ // blue - 탈출x - queue
                        if(visited[nextRed.r][nextRed.c][nextBlue.r][nextBlue.c]) continue;
                        visited[nextRed.r][nextRed.c][nextBlue.r][nextBlue.c] = true;
                        q.add(nextRed);
                        q.add(nextBlue);
                    }
                }
            }
        }
        System.out.println("-1");
    }
    static boolean move(String[] map, int dir, Pos start){
        int r = start.r;
        int c = start.c;
        while(true){
            if(map[r+mr[dir]].charAt(c+mc[dir]) == '#') break;
            if(map[r+mr[dir]].charAt(c+mc[dir]) == 'O'){
                return true;
            }
            start.r = r = (r+mr[dir]);
            start.c = c = (c+mc[dir]);
        }
        return false;
    }
    static void moreMove(int dir, Pos curRed, Pos nextRed, Pos curBlue, Pos nextBlue){
        if (moveCnt(curRed.r, nextRed.r) > moveCnt(curBlue.r, nextBlue.r)){
            nextRed.r -= mr[dir];
        }
        else if (moveCnt(curRed.r, nextRed.r) < moveCnt(curBlue.r, nextBlue.r)){
            nextBlue.r -= mr[dir];
        }
        else if (moveCnt(curRed.c, nextRed.c) > moveCnt(curBlue.c, nextBlue.c)){
            nextRed.c -= mc[dir];
        }
        else if (moveCnt(curRed.c, nextRed.c) < moveCnt(curBlue.c, nextBlue.c)){
            nextBlue.c -= mc[dir];
        }
    }
    static int moveCnt(int a, int b){
        return Math.abs(a - b);
    }
}