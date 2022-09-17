import java.util.*;

class Solution {
    int[] mr = { 1, -1, 0, 0}, mc = {0, 0, 1, -1};
    class Position {
        int r, c, move, v = 1;
        public Position(int r, int c, int move, int v) {
            this.r = r;
            this.c = c;
            this.move = move;
            this.v = v;
        }
    }
    public int solution(int[][] board, int r, int c) {
        int count = 0;
        for(int i = 0 ; i < 4; i++){
            for(int j = 0; j < 4; j++){
                count = Math.max(count, board[i][j]);
            }
        }
        return finalPath(board, count, r,c);
    }
    
    int finalPath(int[][] card, int n, int r, int c){

        PriorityQueue<Position> pq = new PriorityQueue<>((o1, o2) -> {return o1.move - o2.move;});
        pq.add(new Position(r, c,n*2, 0));

        // 모든 곳을 방문했을 경우
        int end = 0;
        for(int i = 1; i <= n; i++){
            end |= (1 << i);
        }

        while(!pq.isEmpty()){
            Position cur = pq.poll();

            if(cur.v == end) return cur.move;

            // 현재 위치가 0인 경우 혹은 visited 방문한 곳인 경우 새로운 포지션 찾기
            int number = card[cur.r][cur.c];
            if(number == 0 || (1 << number & cur.v) > 0){
                for(int i = 1; i <= n; i++){
                    if((1 << i & cur.v) == 0){ // 아직 방문하지 않은 경우
                        Position next = findMinimumPath(card, i, cur.r, cur.c, cur.v);
                        pq.add(new Position(next.r, next.c, (next.move + cur.move), cur.v));
                    }
                }
            }
            // 0이 아니면서 아직 방문하지 않았다면 그 방문지 찾기
            else{
                Position next = findMinimumPath(card, number, cur.r, cur.c, cur.v);
                pq.add(new Position(next.r, next.c, (next.move + cur.move), (cur.v | 1 << number)));
            }
        }

        return -1;
    }
    Position findMinimumPath(int[][] card, int flag, int r, int c, int v) {

        boolean[][] visited = new boolean[4][4];
        PriorityQueue<Position> q = new PriorityQueue<>((o1, o2) -> {return o1.move - o2.move;});

        q.add(new Position(r, c, 0, v));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Position current = q.poll();

            // ctrl
            for (int i = 0; i < 2; i++) {
                int nextColumn = moveToColumnWithCtrl(card, current.r, current.c, mr[i], current.v);
                if (nextColumn != current.c && !visited[current.r][nextColumn]) {
                    if (card[current.r][nextColumn] == flag) return new Position(current.r, nextColumn,current.move + 1, current.v);
                    visited[current.r][nextColumn] = true;
                    q.add(new Position(current.r, nextColumn, current.move + 1, current.v));
                }
                int nextRow = moveToRowWithCtrl(card, current.r, current.c, mr[i], current.v);
                if (nextRow != current.r && !visited[nextRow][current.c]) {
                    if (card[nextRow][current.c] == flag) return new Position(nextRow,current.c,current.move + 1, current.v);
                    visited[nextRow][current.c] = true;
                    q.add(new Position(nextRow, current.c, current.move + 1, current.v));
                }
            }

            // 한칸씩 이동
            for (int i = 0; i < 4; i++) {
                int nextRow = current.r + mr[i], nextColumn = current.c + mc[i];
                if (nextRow < 0 || nextRow >= 4 || nextColumn < 0 || nextColumn >= 4) continue;
                if (!visited[nextRow][nextColumn]) {
                    if (card[nextRow][nextColumn] == flag)
                        return new Position(nextRow, nextColumn,current.move + 1, current.v);;
                    visited[nextRow][nextColumn] = true;
                    q.add(new Position(nextRow, nextColumn, current.move + 1, current.v));
                }
            }
        }
        return null;
    }
    static int moveToColumnWithCtrl(int[][] card, int row, int column, int dir, int v){
        int nextColumn = column + dir;
        while(true){
            if(nextColumn < 0) return 0;
            if(nextColumn >= 4) return 3;

            if(card[row][nextColumn] != 0 && (1 << card[row][nextColumn] & v) == 0) return nextColumn;
            nextColumn += dir;
        }
    }
    static int moveToRowWithCtrl(int[][] card, int row, int column, int dir, int v){
        int nextRow = row + dir;
        while(true){
            if(nextRow < 0) return 0;
            if(nextRow >= 4) return 3;

            if(card[nextRow][column] != 0 && (1 << card[nextRow][column] & v) == 0) return nextRow;
            nextRow += dir;
        }
    }
    
}