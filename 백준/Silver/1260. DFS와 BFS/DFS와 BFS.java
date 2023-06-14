import java.io.*;
import java.util.*;
public class Main {
    static List<ArrayList<Integer>> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static boolean[] vis;
    static int n, m, v;
    static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) list.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            list.get(one).add(two);
            list.get(two).add(one);
            set.add(one); set.add(two);
        }
        vis = new boolean[n+1];
        for(int i = 1; i <= n; i++) Collections.sort(list.get(i));
        vis[v] = true;
        sb.append(v).append(" ");
        dfs(v, 1);
        bfs();
        System.out.println(sb);
    }

    static void dfs(int start,int level) {
        if (level == set.size()) {
            return;
        }
        for (int i : list.get(start)) {
            if (!vis[i]) {
                sb.append(i).append(" ");
                vis[i] = true;
                dfs(i, level+1);
            }
        }
    }
    static void bfs() {
        vis = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        sb.append("\n").append(v).append(" ");
        vis[v] = true;
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int i : list.get(cur)) {
                if(!vis[i]) {
                    q.add(i);
                    sb.append(i).append(" ");
                    vis[i] = true;
                }
            }
        }
    }
}