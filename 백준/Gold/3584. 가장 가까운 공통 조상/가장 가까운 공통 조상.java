import javax.swing.plaf.basic.BasicBorders;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(test-- > 0){
            int n = Integer.parseInt(br.readLine());

            int[] parent = new int[n+1];
            for(int i = 0; i < n-1; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                parent[b] = a;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int findNode1 = Integer.parseInt(st.nextToken());
            int findNode2 = Integer.parseInt(st.nextToken());

            sb.append(findSameParent(parent, findNode1, findNode2) + "\n");
        }

        System.out.println(sb);
    }
    static int checkLevel(int n, int[] parent){
        int level = 0;
        int l = n;
        while(parent[l] != 0){
            level++;
            l = parent[l];
        }
        return level;
    }
    static int findSameParent(int[] parent, int n1, int n2){
        int level1 = checkLevel(n1, parent);
        int level2 = checkLevel(n2, parent);
        int node1 = n1;
        int node2 = n2;

        while(level1 != level2){
            if(level1 > level2){
                node1 = parent[node1];
                level1--;
            }else{
                node2 = parent[node2];
                level2--;
            }
        }

        while(node1 != node2){
            node1 = parent[node1];
            node2 = parent[node2];
        }

        return node1;
    }
}
