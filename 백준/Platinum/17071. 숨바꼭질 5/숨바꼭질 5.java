import java.io.*;
import java.util.*;
public class Main {
    static int n, k;
    static boolean[][] vis;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        vis = new boolean[2][500001];
        bw.write(String.valueOf((n == k) ? 0 : bfs()));
        bw.flush();
        bw.close();
    }
    private static int bfs() {
        q.add(n);
        vis[0][n] = true;
        int time = 0;
        
        while (!q.isEmpty()) {
            int mod = ++time % 2;
            k += time;
            if(k > 500000) return -1;
            int size = q.size();
            while (size-- > 0) {
                int cx = q.remove();
                for (int nx : new int[]{cx + 1, cx - 1, cx * 2}) {
                    if (nx < 0 || nx > 500000 || vis[mod][nx]) continue;
                    q.add(nx);
                    vis[mod][nx] = true;
                }
            }
            if(vis[mod][k]) return time;
        }
        return -1;
    }
}