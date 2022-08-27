import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 1. input
        Stack<int[]> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            int number = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()){
                int[] pop = stack.peek();
                if(pop[1] < number){
                    stack.pop();
                }else{
                    sb.append(pop[0] + " ");
                    break;
                }
            }
            if(stack.isEmpty()) sb.append("0 ");
            stack.push(new int[]{i+1, number});
        }

        // 3. output
        System.out.println(sb);
    }
}