import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] train = new int[n+1];
        int[] sum = new int[n+1];
        for(int i = 1; i <= n; i++){
            train[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i -1] + train[i];
        }

        int k = Integer.parseInt(br.readLine());
        int[][] dp = new int[4][n+1];
        for(int i = 1; i <= 3; i++){
            for(int j = i * k; j <= n; j++){
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j - k] + (sum[j]-sum[j - k]));
            }
        }
        System.out.println(dp[3][n]);
    }
}