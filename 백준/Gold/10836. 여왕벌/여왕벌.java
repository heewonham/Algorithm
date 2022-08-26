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

        int[] map = new int[n+n-1];
        init(map);
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            Integer.parseInt(st.nextToken());

            for (int j = zero; j < zero + one; j++) {
                map[j]++;
            }
            for (int j = zero+one; j < map.length; j++) {
                map[j] += 2;
            }
        }

        // result
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(map[n-i-1] + " ");
            for(int j = 1; j < n; j++){
                sb.append(map[n+j-1] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void init(int[] map){
        for(int i = 0; i < map.length; i++){
            map[i] = 1;
        }
    }
}