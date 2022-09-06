import java.util.*; 

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> scores = new HashMap<>();
        
        for(int i = 0; i < survey.length; i++){
            check(survey[i], scores, choices[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        
        String[] result = {"RT", "CF", "JM", "AN"};
        for(int i = 0; i < 4; i++){
            char a = result[i].charAt(0);
            char b = result[i].charAt(1);
            sb.append(find(a,b,scores));
        }
        
        return sb.toString();
    }
    
    void check(String name, Map<Character, Integer> score, int n){
        char types = 'A';
        int sum = 0;
        if(n < 4){
            types = name.charAt(0);
            sum = 4 - n;
        }
        else if(n > 4){
            types = name.charAt(1);
            sum = n - 4;
        }
        score.put(types, (score.getOrDefault(types, 0) + sum));
    }
    char find(char a, char b,  Map<Character, Integer> score){
        int as = score.getOrDefault(a, 0);
        int bs = score.getOrDefault(b, 0);
        if(as > bs) return a;
        else if(as < bs)return b;
        else {
            if(a < b) return a;
            else return b;
        }
    }
}