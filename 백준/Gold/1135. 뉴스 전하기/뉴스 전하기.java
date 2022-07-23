import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer>[] child = new List[n];
        for(int i = 0; i < n; i++){
            child[i] = new ArrayList<>();
        }

        // input
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int e = Integer.parseInt(st.nextToken());
            if(e == -1) continue;
            child[e].add(i);
        }
        System.out.println(solve(child, 0));
    }

    static int solve(List<Integer>[] child, int idx){
        if(child[idx].size() == 0) return 0;

        List<Integer> time = new ArrayList<>();
        for(int next : child[idx]){
            time.add(1 + solve(child, next));
        }

        // 가장 오래걸리는 거부터
        Collections.sort(time, (o1, o2) -> {return o2-o1;});
        int max = 0;
        for(int i = 0; i < time.size(); i++){
            int t = time.get(i) + i;
            if (max < t){
                max = t;
            }
        }
        return max;
    }
}