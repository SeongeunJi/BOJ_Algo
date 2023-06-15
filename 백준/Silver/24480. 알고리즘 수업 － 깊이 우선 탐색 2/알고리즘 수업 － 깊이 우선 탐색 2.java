import java.util.*;
import java.io.*;
public class Main {
    static int n, m, r;
    static boolean[] vis;
    static int cnt;
    static int[] ans;
    static List<Set<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        vis = new boolean[n+1];
        ans = new int[n+1];
        for (int i = 0; i <= n; i++) {
            list.add(new TreeSet<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            }));
        }
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int fir = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            list.get(fir).add(two);
            list.get(two).add(fir);
        }
        vis[r] = true;
        ans[r] = ++cnt;
        dfs(r, 1);
        for (int i = 1; i <= n; i++) sb.append(ans[i]).append("\n");
        System.out.println(sb);
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