import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf((n == k) ? 0 : bfs(n, k)));
        bw.flush();
        bw.close();
    }
    private static int bfs(int n, int k) {
        boolean[][] vis = new boolean[2][500001];
        Queue<Integer> q = new LinkedList<>();
        vis[0][n] = true; q.add(n);

        int time = 0;
        while (!q.isEmpty()) {
            int mod = ++time % 2;
            k += time;
            if(k > 500000) return -1;
            int sz = q.size();
            while(sz --> 0) {
                int cx = q.remove();
                for (int nx : new int[]{cx + 1, cx - 1, cx * 2}) {
                    if(nx < 0 || nx > 500000 || vis[mod][nx]) continue;
                    q.add(nx);
                    vis[mod][nx] = true;
                }
            }
            if(vis[mod][k]) return time;
        }
        return -1;
    }
}