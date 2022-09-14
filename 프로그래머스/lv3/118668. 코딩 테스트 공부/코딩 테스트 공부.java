import java.util.*;

class Solution {
    private static final int TIMEMAX = 350, MAX = 150;
    class Status{
        int alp, cop, time;

        public Status(int alp, int cop, int time) {
            this.alp = alp;
            this.cop = cop;
            this.time = time;
        }
    }
    public int solution(int alp, int cop, int[][] problems) {
         // sort
        int[][] time = new int[MAX+1][MAX+1];
        Arrays.sort(problems, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        // init
        for(int i = 0; i < MAX+1; i++){
            for(int j = 0; j < MAX+1; j++){
                time[i][j] = TIMEMAX;
            }
        }

        return findMinimumTime(problems, time, alp, cop);
    }
    int findMinimumTime(int[][] problems, int[][] time, int startAlp, int startCop){

        PriorityQueue<Status> pq = new PriorityQueue<>(
                (o1, o2) -> { return o1.time - o2.time; }
        );
        pq.add(new Status(startAlp, startCop, 0));

        while(!pq.isEmpty()){
            Status current = pq.poll();
            int curAlp = current.alp;
            int curCop = current.cop;

            boolean flag = true;
            int nextAlp = 0, nextCop = 0;
            for(int i = 0; i < problems.length; i++){
                if(problems[i][0] > curAlp || problems[i][1] > curCop){
                    flag = false;
                    continue;
                }
                nextAlp = Math.min(curAlp + problems[i][2], 150);
                nextCop = Math.min(curCop + problems[i][3], 150);
                int nextTime = current.time + problems[i][4];
                if(time[nextAlp][nextCop] > nextTime){
                    time[nextAlp][nextCop] = nextTime;
                    pq.add(new Status(nextAlp, nextCop, nextTime));
                }
            }

            if(flag) return current.time;

            nextAlp = Math.min(curAlp + 1, MAX);
            nextCop = Math.min(curCop + 1, MAX);
            // 1. alp + 1
            if(time[nextAlp][curCop] > current.time + 1){
                time[nextAlp][curCop] = current.time + 1;
                pq.add(new Status(nextAlp, curCop, current.time+1));
            }
            // 2. cop + 1
            if(time[curAlp][nextCop] > current.time + 1){
                time[curAlp][nextCop] = current.time + 1;
                pq.add(new Status(curAlp, nextCop, current.time+1));
            }

            // 3. problems

        }

        return -1;
    }
}