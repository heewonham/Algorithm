import java.util.*;

class Solution {
    private static final int MAX = 10000001;
    class Position{
        private int node, intensity;
        
        public Position(int node, int intensity){
            this.node = node;
            this.intensity = intensity;
        }
        
        public int getNode(){
            return node;
        }
        
        public int getIntensity(){
            return intensity;
        }
    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Position>[] connection = new List[n+1];
        for(int i = 1; i <= n; i++){
            connection[i] = new ArrayList<>();
        }
        
        for(int[] path : paths){
            connection[path[0]].add(new Position(path[1], path[2]));
            connection[path[1]].add(new Position(path[0], path[2]));
        }
        
        return findMinimumIntesityAndGate(connection, gates, summits, n);
    }
    int[] findMinimumIntesityAndGate(List<Position>[] connection, int[] gates, int[] summits, int n){
        boolean[] visited = new boolean[n+1]; 
        int node = MAX, intensity = MAX, max = 0;
        PriorityQueue<Position> course = new PriorityQueue<>((o1, o2) -> {
            return o1.intensity - o2.intensity; 
        });
        
        // 시작 게이트 모두 넣기
        for(int gate : gates){
            course.add(new Position(gate, 0));
        }
        
        while(!course.isEmpty()){
            Position current = course.poll();
            int currentNode = current.getNode();
            int currentIntensity = current.getIntensity();

            max = Math.max(max, currentIntensity);
            
            // 방문표시 
            if(visited[currentNode]) continue;
            visited[currentNode] = true;
            
            // 목적지 도착
            if(isSummits(summits, currentNode)){
                if(max < intensity){
                    intensity = max;
                    node = currentNode;
                } else if(max == intensity && currentNode < node){
                    node = currentNode;
                }
                continue;
            }
            
            // 연결점 추가
            // 내가 목적지면 그 다음 목적지면 안됨
            for(int i = 0; i < connection[currentNode].size(); i++){
                Position next = connection[currentNode].get(i);
                course.add(new Position(next.getNode(), next.getIntensity()));
            }
        }
        
        int[] answer = {node, intensity};
        return answer;
    }
    boolean isSummits(int[] summits, int node){
        for(int summit : summits){
            if(summit == node) return true;
        }        
        return false;
    }
}