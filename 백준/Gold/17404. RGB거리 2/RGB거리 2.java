import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import java.util.Arrays;

public class Main {
    static int MAX = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] house = new int[n][3];
        int answer = MAX * 2;
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp
        for(int color = 0; color < 3; color++) {
            int[][] dp = new int[3][n];
            dp[color][0] = house[0][color];
            dp[(color+1)%3][0] = MAX;
            dp[(color+2)%3][0] = MAX;

            for(int i = 1; i < n; i++){
                for(int j = 0; j < 3; j++){ // 색 정하기
                    int e1 = (j+1)%3, e2 = (j+2)%3;
                    int a = dp[e1][i-1] + house[i][j];
                    int b = dp[e2][i-1] + house[i][j];

                    dp[j][i] = Math.min(a,b);

                    if(i == n-1 && j != color){
                        answer = Math.min(answer, dp[j][i]);
                    }
                }
            }
        }
        System.out.println(answer);
    }

}