import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Cup {
        int a,b,c;
        public Cup(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        // 1. input
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // 2. init
        int[] maxList = {a,b,c};
        boolean[][][] visited = new boolean[a+1][b+1][c+1];
        Queue<Cup> q = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();

        q.add(new Cup(0,0, c));
        visited[0][0][c] = true;
        answer.add(c);

        // 3. execute
        while(!q.isEmpty()){
            Cup cup = q.poll();
            int[] cur = {cup.a, cup.b, cup.c};

            for(int i = 0; i < 3; i++){
                if(cur[i] == 0) continue;
                for(int j = 1; j <= 2; j++){
                    int[] next = {cup.a, cup.b, cup.c};
                    move(next, maxList, i, (i+j)%3);
                    if(!visited[next[0]][next[1]][next[2]]){
                        if(next[0] == 0){
                            answer.add(next[2]); // set 저장
                        }
                        visited[next[0]][next[1]][next[2]] = true;
                        q.add(new Cup(next[0],next[1],next[2]));
                    }
                }
            }
        }

        // 4. output
        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for (Integer val : answer) {
            sb.append(val).append(" ");
        }
        System.out.println(sb);
    }

    static void move(int[] cur, int[] maxList, int idx, int next) {
        int maxMove = Math.min(maxList[next] - cur[next], cur[idx]);
        cur[idx] -= maxMove;
        cur[next] += maxMove;
    }
}