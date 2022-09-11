import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Main {
    private static final int BOARDCOUNT = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // init
        int[] next = new int[BOARDCOUNT+1], dist = new int[BOARDCOUNT+1];
        for(int i = 1; i <= BOARDCOUNT; i++){
            next[i] = i;
            dist[i] = -1;
        }

        for(int i = 0; i < n+m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            next[start] = end;
        }
        findShortestPath(next, dist);
        System.out.println(dist[BOARDCOUNT]);
    }
    static void findShortestPath(int[] next, int[] dist){
        Queue<Integer> position = new LinkedList<>();
        dist[1] = 0;
        position.add(1);
        
        while (!position.isEmpty()){
            int currentPostion = position.poll();

            for(int i = 1; i <= 6; i++){
                int nextPosition = currentPostion + i;
                if(nextPosition > BOARDCOUNT) continue;
                nextPosition = next[nextPosition];
                if(dist[nextPosition] == -1){
                    position.add(nextPosition);
                    dist[nextPosition] = dist[currentPostion] + 1;
                }
            }
        }
    }
    static void fastMove(Map<Integer, Integer> move, boolean[] visited, Queue<Integer> position, Queue<Integer> count, int currentPostion, int currentCount){
        int movePosition = move.get(currentPostion);
        if(!visited[movePosition]){
            visited[movePosition] = true;
            position.add(movePosition);
            count.add(currentCount);
        }
    }
}
