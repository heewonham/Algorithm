import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int MAX = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // input
        int[][] table = new int[n][3];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
            table[i][2] = Integer.parseInt(st.nextToken());
        }

        // init
        int[][][] score = new int[n][3][2];
        score[0][0][0] = table[0][0];
        score[0][0][1] = table[0][0];
        score[0][1][0] = table[0][1];
        score[0][1][1] = table[0][1];
        score[0][2][0] = table[0][2];
        score[0][2][1] = table[0][2];

        int max = Math.max(score[0][0][0], Math.max(score[0][1][0], score[0][2][0]));
        int min = Math.min(score[0][0][1], Math.min(score[0][1][1], score[0][2][1]));

        // execute
        for(int j = 1; j < n; j++){
            for(int k = 0; k < 3; k++){
                int cur = table[j][k];
                score[j][k][0] = cur + score[j-1][k][0];
                score[j][k][1] = cur + score[j-1][k][1];

                if(k <= 1){ // 0, 1
                    score[j][k][0] = Math.max(score[j][k][0], cur + score[j-1][k+1][0]);
                    score[j][k][1] = Math.min(score[j][k][1], cur + score[j-1][k+1][1]);
                }
                if(k >= 1){ // 1, 2
                    score[j][k][0] = Math.max(score[j][k][0], cur + score[j-1][k-1][0]);
                    score[j][k][1] = Math.min(score[j][k][1], cur + score[j-1][k-1][1]);
                }
            }
            // 마지막인 경우
            if(j == n-1){
                max = Math.max(score[j][0][0], Math.max(score[j][1][0], score[j][2][0]));
                min = Math.min(score[j][0][1], Math.min(score[j][1][1], score[j][2][1]));
            }
        }

        // output
        System.out.println(max + " " + min);
    }
}