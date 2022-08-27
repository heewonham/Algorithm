import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        StringBuilder sb = new StringBuilder();
        int idx = 0, i = 0;
        while(i < str.length()){
            if(str.charAt(i) == '<'){  // 태그인 경우
                for(int j = i; j < str.length(); j++){
                    sb.append(str.charAt(j));
                    if(str.charAt(j) == '>'){
                        i = j+1;
                        break;
                    }
                }
            }
            else if(str.charAt(i) == ' '){
                sb.append(' ');
                i++;
            }
            else{  // 문자인 경우
                StringBuilder s = new StringBuilder();
                for(int j = i; j <= str.length(); j++){
                    if(j == str.length() || str.charAt(j) == '<' || str.charAt(j) == ' '){
                        s.append(str.substring(i, j));
                        sb.append(s.reverse());
                        i = j;
                        break;
                    }
                }
            }
        }
        System.out.println(sb);
    }
}