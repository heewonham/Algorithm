import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Main {
    static class Position{
        private int node, mm;

        public Position(int node, int mm) {
            this.node = node;
            this.mm = mm;
        }

        public int getNode() {
            return node;
        }

        public int getMm() {
            return mm;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n+1];
        List<Position>[] range = new List[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            items[i] = Integer.parseInt(st.nextToken());
            range[i] = new ArrayList<>();
        }


        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int path = Integer.parseInt(st.nextToken());
            range[v1].add(new Position(v2, path));
            range[v2].add(new Position(v1, path));
        }

        int max = 0;
        for(int i = 1; i <= n; i++){
            max = Math.max(max, findItemCount(range, items, n, m, i));
        }

        System.out.println(max);
    }

    static int findItemCount(List<Position>[] range, int[] items, int n, int m, int start){
        PriorityQueue<Position> course = new PriorityQueue<>((o1, o2) -> {return o1.getMm() - o2.getMm();});
        course.add(new Position(start, 0));
        boolean[] visited = new boolean[n+1];
        int count = 0;

        while (!course.isEmpty()) {
            Position currentPosition = course.poll();
            int currentNode = currentPosition.getNode();
            int currentMM = currentPosition.getMm();

            if(visited[currentNode]) continue;
            visited[currentNode] = true;
            count += items[currentNode];

            for(int i = 0; i < range[currentNode].size(); i++){
                Position nextPosition = range[currentNode].get(i);
                if(currentMM + nextPosition.getMm() <= m){
                    course.add(new Position(nextPosition.getNode(), currentMM + nextPosition.getMm() ));
                }
            }
        }

        return count;
    }
}
