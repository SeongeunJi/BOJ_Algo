import java.io.*;
import java.util.*;
public class Main {
    static int comp;
    static int ans;
    static boolean[] vis;
    static List<Set<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        comp = Integer.parseInt(br.readLine());
        vis = new boolean[comp + 1];
        int nodes = Integer.parseInt(br.readLine());
        for (int i = 0; i <= comp; i++) list.add(new HashSet<>());
        while(nodes --> 0) {
            st = new StringTokenizer(br.readLine());
            int fir = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());
            list.get(fir).add(sec);
            list.get(sec).add(fir);
        }
        vis[1] = true;
        dfs(1);
        System.out.println(ans);
    }

    static void dfs(int start) {
        for (int i : list.get(start)) {
            if (!vis[i]) {
                ans++;
                vis[i] = true;
                dfs(i);
            }
        }
    }
}