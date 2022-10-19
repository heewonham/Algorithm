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
                    answer += findCircle(i, lists, visited);
                }
            }
            sb.append((n - answer) + "\n");
        }
        System.out.println(sb);
    }
    static int findCircle(int s, int[] lists, boolean[] visited){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;

        Stack<Integer> stack = new Stack<>();
        int last = s;

        // 모든 연결 찾기
        while(!queue.isEmpty()){
            int cur = queue.poll();
            last = cur;
            stack.add(cur);

            int next = lists[cur];
            if(!visited[next]){
                visited[next] = true;
                queue.add(next);
            }
        }

        // 마지막 도착지랑 같은 순환 찾기
        int cnt = 0;
        last = lists[last];
        while(!stack.empty()){
            int cur = stack.pop();
            cnt++;
            if (cur == last){
                return cnt;
            }
        }
        return 0;
    }
}
