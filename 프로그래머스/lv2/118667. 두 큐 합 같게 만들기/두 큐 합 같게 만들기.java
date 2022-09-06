import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0, sum2 = 0;
        int idx1 = 0, idx2 = 0, len = queue1.length, answer = 0;
        for(int i = 0; i < len; i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        
        // 예외
        if((sum1+sum2) % 2 != 0) return -1;
        
        // 정답
        while(true){
            if(sum1 == sum2) break;
            if(sum1 == 0 || sum2 == 0) return -1;
            
            long n = 0;
            if(sum1 > sum2){
                if(idx1 < len) n = queue1[idx1];
                else if(idx1 < len+len) n = queue2[idx1 % len];
                else return -1;
                idx1++;
                sum1 -= n;
                sum2 += n;
            }else{
                if(idx2 < len) n = queue2[idx2];
                else if(idx2 < len+len) n = queue1[idx2 % len];
                else return -1;
                idx2++;
                sum2 -= n;
                sum1 += n;
            }
            answer++;
        }
        
        return answer;
    }
}