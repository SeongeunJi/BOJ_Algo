import java.io.*;
import java.util.*;
public class Main {
    static int n, m, r;
    static boolean[] vis;
    static int[] ans;
    static int cnt;
    static List<Set<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        vis = new boolean[n+1];
        ans = new int[n+1];
        for(int i = 0; i <= n; i++) list.add(new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }));
        while(m --> 0) {
            st = new StringTokenizer(br.readLine());
            int fir = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            list.get(fir).add(two);
            list.get(two).add(fir);
        }
        bfs();
    }

    static void bfs() {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        vis[r] = true;
        ans[r] = ++cnt;
        q.add(r);
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int i : list.get(cur)) {
                if(vis[i]) continue;
                vis[i] = true;
                ans[i] = ++cnt;
                q.add(i);
            }
        }
        for (int i = 1; i <= n; i++) sb.append(ans[i]).append("\n");
        System.out.println(sb);
    }
}