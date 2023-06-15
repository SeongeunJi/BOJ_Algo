import java.util.*;
import java.io.*;
public class Main {
    static int v, e;
    static List<Integer>[] list;
    static byte[] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            vis = new byte[v+1];
            list = new List[v+1];
            for (int i = 0; i <= v; i++) list[i] = new ArrayList<>();
            while(e --> 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list[from].add(to);
                list[to].add(from);
            }
            bw.write((bfs()) ? "YES\n" : "NO\n");
        }
        bw.flush();
        bw.close();
    }

    static boolean bfs() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= v; i++) {
            if (vis[i] == 0) {
                q.add(i);
                vis[i] = 1;
            }
            while (!q.isEmpty()) {
                int now = q.poll();
                for (int nx : list[now]) {
                    if (vis[nx] == 0) q.add(nx);
                    if (vis[nx] == vis[now]) return false;
                    if (vis[now] == 1 && vis[nx] == 0) vis[nx] = 2;
                    else if(vis[now] == 2 && vis[nx] == 0) vis[nx] = 1;
                }
            }
        }
        return true;
    }
}