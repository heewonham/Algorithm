import java.util.*;

class Solution {
    int answer = 0;
    public int solution(int[] info, int[][] edges) {
        List<Integer>[] nodes = new List[info.length];
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new ArrayList<>();
        }

        for(int[] edge : edges){
            nodes[edge[0]].add(edge[1]);
        }

        // find
        List<Integer> current = new ArrayList<>();
        current.add(0);
        findMaximumSheep(nodes, info, current,0, 0, 0);
        
        return answer;
    }
    void findMaximumSheep(List<Integer>[] node, int[] info, List<Integer> current, int sheep, int wolf, int idx){

        if(info[idx] == 0) sheep++;
        else wolf++;

        if(sheep <= wolf) return;
        if(answer < sheep) answer = sheep;

        List<Integer> next = new ArrayList<>(current.subList(0,current.size()));
        next.remove((Integer) idx);
        for(int i = 0; i < node[idx].size(); i++){
            next.add(node[idx].get(i));
        }

        for(int i = 0; i < next.size(); i++){
            findMaximumSheep(node, info, next, sheep, wolf, next.get(i));
        }
    }
    
}