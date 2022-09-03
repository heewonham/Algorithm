import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static void init(long[] burger, long[] patty, int n){
        int i;
        burger[0] = 1;
        patty[0] = 1;
        for(i = 1; i <= n; i++){
            burger[i] = 1 + burger[i-1] + 1 + burger[i-1] + 1;
            patty[i] = patty[i-1] + 1 + patty[i-1];
        }
    }
    static long ate(long[] burger, long[] patty, int n, long x){
        if(n == 0) return x;
        if(x == 1) return 0;
        else if(x <= burger[n-1] + 1) return ate(burger, patty, n-1, x-1);
        else if(x == 1 + burger[n-1] + 1) return patty[n-1] + 1;
        else if(x <= burger[n-1] + 1 + burger[n-1] + 1)
            return patty[n-1] + 1 + ate(burger, patty, n-1, x - (1 + burger[n-1] + 1));
        else return patty[n-1] + 1 + patty[n-1];
    }

    public static void main(String[] agrs) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        long[] burger = new long[n+1], patty = new long[n+1];
        init(burger, patty, n);
        System.out.println(ate(burger, patty, n, x));
    }
}
