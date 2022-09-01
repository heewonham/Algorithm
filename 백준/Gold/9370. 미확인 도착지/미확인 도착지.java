import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int MAX = 200000000;
    public static class Vertex {
        int v, w;
        public Vertex(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(test-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            // n - 교차로, m - 도로, candidate - 도착 후보지
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] candidate = new int[Integer.parseInt(st.nextToken())];

            // s 출발점 , g~h 사이 도로 지나감
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            // init
            List<Vertex>[] graph = new List[n+1];
            int[] dist = new int[n+1];
            for(int i = 1; i <= n; i++){
                graph[i] = new ArrayList<>();
                dist[i] = MAX;
            }

            // 도로 input
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                int plusW = (v1 == g && v2 == h) || (v1 == h && v2 == g) ? (w * 2) - 1 : (w * 2);
                graph[v1].add(new Vertex(v2, plusW));
                graph[v2].add(new Vertex(v1, plusW));
            }

            // 도착 후보지 입력
            for(int i = 0; i < candidate.length; i++){
                candidate[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(candidate);

            // 다익스트라
            diak(graph, dist, s);

            // result 해당 값이 gh를 걸쳐지나갔는지를 확인하는 작업 필요
            for(int i = 0; i < candidate.length; i++){
                int end = candidate[i];
                if(dist[end] % 2 == 1){
                    sb.append(end + " ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void diak(List<Vertex>[] graph, int[] weight, int s) {
        // 다익스트라
        PriorityQueue<Vertex> q = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        weight[s] = 0;
        boolean[] visited = new boolean[weight.length];

        q.add(new Vertex(s, 0));
        while(!q.isEmpty()){
            Vertex cur = q.poll();
            int idx = cur.v;

            if(visited[idx]) continue;
            visited[idx] = true;
            for(int i = 0; i < graph[idx].size(); i++){
                int nextV = graph[idx].get(i).v;
                int nextW = graph[idx].get(i).w;
                if(weight[nextV] >= cur.w+nextW){
                    weight[nextV] = cur.w+nextW;
                    q.add(new Vertex(nextV, weight[nextV]));
                }
            }
        }
    }
}