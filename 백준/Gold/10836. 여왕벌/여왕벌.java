import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] mr = {0,-1,-1}, mc = {-1,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        plusZero(map, n);
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            for(int j = n-1; j >= 0; j--){
                if(zero != 0) zero--;
                else if(one != 0){
                    one--;
                    map[j][0] += 1;
                }else if(two != 0){
                    two--;
                    map[j][0] += 2;
                }
            }
            for(int j = 1; j < n; j++){
                if(zero != 0) zero--;
                else if(one != 0){
                    one--;
                    map[0][j] += 1;
                }else if(two != 0){
                    two--;
                    map[0][j] += 2;
                }
            }
        }

        // find
        plusSecond(map,n);

        // result
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void plusZero(int[][] map, int n){
        for(int i = n-1; i >= 0; i--){
            map[i][0] += 1;
        }
        for(int i = 1; i < n; i++){
            map[0][i] += 1;
        }
    }
    static void plusSecond(int[][] map, int n){
        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                int a = map[i+mr[0]][j+mc[0]];
                int b = map[i+mr[1]][j+mc[1]];
                int c = map[i+mr[2]][j+mc[2]];
                map[i][j] = findMax(a,b,c);
            }
        }
    }
    static int findMax(int a, int b, int c){
        int ab = Math.max(a,b);
        return Math.max(ab, c);
    }
}