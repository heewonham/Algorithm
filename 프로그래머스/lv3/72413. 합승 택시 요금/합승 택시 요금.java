import java.util.*;

class Solution {
    private static final int MAX = 20000005;
    class Node{
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
         List<Node>[] connection = new List[n+1];
        for(int i = 1; i <= n; i++) connection[i] = new ArrayList<>();
        for(int[] fare : fares){
            connection[fare[0]].add(new Node(fare[1], fare[2]));
            connection[fare[1]].add(new Node(fare[0], fare[2]));
        }

         /***
         * 각자 최단 거리 더한 값과
         * 일정한 노드까지 같이간 경로 더허가 비교
         */
        int[] distS = new int[n+1], distA = new int[n+1], distB = new int[n+1];
        for(int i = 1; i <= n; i++){
            distS[i] = MAX;
            distA[i] = MAX;
            distB[i] = MAX;
        }

        findShortestPath(connection, distS, n, s);
        findShortestPath(connection, distA, n, a);
        findShortestPath(connection, distB, n, b);
        int answer = distS[a] + distS[b];

        for(int i = 1 ; i <= n; i++){
            if(s == i) continue;
            answer = Math.min(answer, (distS[i] + distA[i] + distB[i]));
        }

        return answer;
    }
    void findShortestPath(List<Node>[] connection, int[] dist, int n, int s){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {return o1.weight - o2.weight;});
        pq.add(new Node(s, 0));
        dist[s] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int node = current.vertex;
            int weight = current.weight;

            for(int i = 0; i < connection[node].size(); i++){
                Node next = connection[node].get(i);
                if(dist[next.vertex] > weight + next.weight){
                    dist[next.vertex] = weight + next.weight;
                    pq.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }
    }
}