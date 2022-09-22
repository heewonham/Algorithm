class Solution {
    
    private static final int MAX = 9;
    int answer = MAX, leftover = 0;
    boolean[] weakVisited = null, distVisited = null;
    
    public int solution(int n, int[] weak, int[] dist) {
        
        int weakLength = weak.length;
        leftover = weakLength;
        distVisited = new boolean[dist.length+1];
        weakVisited = new boolean[weakLength*2];

        // weak -> 새로 만들기
        int[] weakDouble = new int[weakLength * 2];
        for(int i = 0; i < weakLength; i++){
            weakDouble[i] = weak[i];
            weakDouble[i+weakLength] = weak[i]+n;
        }

        for(int i = 0; i < weakLength; i++){
            findAllcheck(weakDouble, dist, weakLength, i, 0, 0, weakLength);
        }
        
        if(answer == MAX) return -1;
        return answer;
    }
    void findAllcheck(int[] weak, int[] dist, int weakCnt, int weakIndex, int n, int cnt, int leftover){

        if(leftover <= 0){
            if(cnt < answer) answer = cnt;
            return;
        }
        if(n >= weakCnt) return;

        for(int i = 0; i < dist.length; i++){
            if(distVisited[i]) continue;

            int curLeftover = leftover;
            int curN = n;

            int j = weakIndex;
            for(; j < weakIndex+weakCnt; j++){
                if(weak[weakIndex]+dist[i] < weak[j] || weakVisited[j%weakCnt]) break;
                weakVisited[j%weakCnt] = true;
                curLeftover--;
                curN++;
            }
            distVisited[i] = true;
            findAllcheck(weak, dist, weakCnt, j%weakCnt, curN, cnt+1, curLeftover);
            distVisited[i] = false;
            for(j=j-1; j >= weakIndex; j--){
                weakVisited[j%weakCnt] = false;
            }
        }
    }
}