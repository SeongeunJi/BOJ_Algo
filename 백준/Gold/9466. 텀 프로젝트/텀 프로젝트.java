import java.io.*;
import java.util.*;
public class Main {
    static boolean[] vis;
    static boolean[] finished;
    static int[] arr;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while(tc --> 0) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            vis = new boolean[n+1];
            finished = new boolean[n+1];
            cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; st.hasMoreTokens(); i++)
                arr[i] = Integer.parseInt(st.nextToken());

            for(int i = 1; i <= n; i++)
                dfs(i);

            bw.write(n-cnt +"\n");
        }
        bw.flush(); bw.close();
    }

    private static void dfs(int cur) {
        if(vis[cur]) return; // 현재 학생이 이미 전에 방문했었다면, 연산 X
        vis[cur] = true; // 첫 방문이라면, true 방문 표시
        int nx = arr[cur]; // 현재 학생이 가르키는 다음 학생 번호
        if(!vis[nx]) dfs(nx); // 다음 학생도 방문하지 않았다면 dfs 돌리기
        else { // 만약 다음 학생을 방문했다면 사이클?
            if(!finished[nx]) { // 사이클이면서도, 사이클 연산이 아니라면 해당 사이클 탐색하여, 사이클 학생 수 연산
                cnt++;
                for(int i = nx; i != cur; i = arr[i]) cnt++;
            }
        }
        finished[cur] = true;
    }
}
