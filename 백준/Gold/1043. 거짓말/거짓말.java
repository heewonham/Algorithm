import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] par = new int[n+1];
        for(int i = 1; i <= n; i++){
            par[i] = i;
        }

        // 진실을 아는 자
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for(int i = 0; i < t; i++){
            int tmp = Integer.parseInt(st.nextToken());
            par[tmp] = 0;
        }

        // 파티장
        List<Integer>[] party = new List[m];
        for(int i = 0; i < m; i++){
            party[i] = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine());
            int person = Integer.parseInt(st.nextToken());
            party[i].add(Integer.parseInt(st.nextToken()));
            for(int j = 1; j < person; j++){
                party[i].add(Integer.parseInt(st.nextToken()));
                union(par, party[i].get(j-1), party[i].get(j));
            }
        }

        // 파티장 한번 더 돌아서 체크
        int answer = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < party[i].size(); j++){
                if(find(par, party[i].get(j)) == 0){
                    answer++;
                    break;
                }
            }
        }

        System.out.println(m-answer);
    }
    static boolean union(int[] par, int a, int b){
        int fa = find(par, a);
        int fb = find(par, b);

        if(fa == fb) return true;

        if(fa > fb){
            par[fa] = fb;
        }else{
            par[fb] = fa;
        }
        return false;
    }
    static int find(int[] par, int n){
        if(par[n] == n) return n;
        return par[n] = find(par, par[n]);
    }
}