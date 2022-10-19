import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());

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
            System.out.println((n - answer));
        }
    }
    static int findCircle(int next, int[] lists, boolean[] visited){

        Stack<Integer> stack = new Stack<>();
        int last = next;
        stack.add(next);

        while(!visited[next]){
            visited[next] = true;
            stack.add(next);
            last = next;
            next = lists[next];
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
