import java.io.*;
import java.util.*;
public class Main {
    static int N, M, ans;
    static boolean[] vis;
    static List<List<Integer>> relations = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vis = new boolean[N];
        for (int i = 0; i < N; i++) relations.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fir = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            relations.get(fir).add(two);
            relations.get(two).add(fir);
        }
        for(int i = 0; i < N; i++) {
            vis[i] = true;
            if(ans == 0) go(1, i);
            vis[i] = false;
        }
        System.out.println(ans);
    }

    static void go(int level, int start) {
        if(level == 5) {
            ans = 1;
            return;
        }
        for(int i : relations.get(start)) {
            if(vis[i]) continue;
            vis[i] = true;
            go(level+1, i);
            vis[i] = false;
        }
    }
}