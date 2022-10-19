import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(test-- > 0){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] lists = new int[n+1];
            for(int i = 1; i <= n; i++){
                lists[i] = Integer.parseInt(st.nextToken());
            }

            boolean[] visited = new boolean[n+1];
            int answer = 0;
            for(int i = 1; i <= n; i++){
                if(!visited[i]){
                    findCircle(i, lists, visited);
                    answer++;
                }
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
    static void findCircle(int s, int[] lists, boolean[] visited){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            int next = lists[cur];
            if(!visited[next]){
                visited[next] = true;
                queue.add(next);
            }
        }
    }
}
