import java.util.*;
import java.io.*;
public class Main {
    static List<Set<Integer>> list = new ArrayList<>();
    static int[] ans;
    static int cnt;
    static int n, m, r;
    static boolean[] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        vis = new boolean[n+1];
        ans = new int[n+1];
        for (int i = 0; i <= n; i++) list.add(new TreeSet<>());
        while(m --> 0) {
            st = new StringTokenizer(br.readLine());
            int fir = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            list.get(fir).add(two);
            list.get(two).add(fir);
        }
        vis[r] = true;
        ans[r] = ++cnt;
        dfs(r, 1);
        for(int i = 1; i <= n; i++) bw.write(ans[i] + "\n");
        bw.flush();
        bw.close();
    }

    static void dfs(int start, int level) {
        if(level == n) return;
        for (int i : list.get(start)) {
            if (!vis[i]) {
                ans[i] = ++cnt;
                vis[i] = true;
                dfs(i, level+1);
            }
        }
    }
}