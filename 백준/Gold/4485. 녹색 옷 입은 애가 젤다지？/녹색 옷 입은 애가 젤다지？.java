import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Main {

    private static final int MAX = (125 * 125 * 9);
    private static final int DIRECTION = 4;
    private static final int[] MOVETOROW = {0,0,1,-1}, MOVETOCOLUMN = {1,-1,0,0};

    static class Position{
        int row, column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = 1;
        String answer = "Problem %d: %d";

        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            // 1. input & init
            int[][] map = new int[n][n], dp = new int[n][n];
            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = MAX;
                }
            }

            // 2. find minimum loss
            findMinimunLossPath(map, dp, n, 0, 0);

            System.out.println(String.format(answer, test++, dp[n-1][n-1]));
        }

    }
    static void findMinimunLossPath(int[][] map, int[][] dp, int n, int startRow, int startColumn){

        Queue<Position> path = new LinkedList<>();
        path.add(new Position(startRow,startColumn));
        dp[startRow][startColumn] = map[startRow][startColumn];

        while(!path.isEmpty()){
            Position currentPosition = path.poll();

            for(int i = 0; i < DIRECTION; i++){
                int nextRow = currentPosition.row + MOVETOROW[i];
                int nextColumn = currentPosition.column + MOVETOCOLUMN[i];
                // 범위 체크
                if(nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n) continue;
                // 최소거리면 방문하기
                if(dp[nextRow][nextColumn] > dp[currentPosition.row][currentPosition.column] + map[nextRow][nextColumn]){
                    dp[nextRow][nextColumn] = dp[currentPosition.row][currentPosition.column] + map[nextRow][nextColumn];
                    path.add(new Position(nextRow, nextColumn));
                }
            }
        }
    }
}
