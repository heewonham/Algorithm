import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int row = board.length;
        int column = board[0].length;
        
        int[][] sum = new int[row+2][column+2];
        for(int[] s : skill){
            int degree = 0;
            if(s[0] == 1) degree = -(s[5]); // 공격
            else degree = s[5]; // 힐링
            
            // 표시
            sum[s[1]+1][s[2]+1] += degree;
            sum[s[1]+1][s[4]+2] -= degree;
            sum[s[3]+2][s[2]+1] -= degree;
            sum[s[3]+2][s[4]+2] += degree;
        }
        
        // 누적합 구하기
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= column; j++){
                sum[i][j] = sum[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
            }
        }
        
        int answer = 0;
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= column; j++){
                if(board[i-1][j-1] + sum[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}