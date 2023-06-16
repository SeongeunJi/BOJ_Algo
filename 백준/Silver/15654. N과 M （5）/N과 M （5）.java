import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static Set<Integer> ts = new TreeSet<>();
    static int[] arr = new int[10];
    static boolean[] vis = new boolean[10001];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) ts.add(Integer.parseInt(st.nextToken()));
        go(0);
        System.out.println(sb);
    }

    static void go(int level) {
        if (level == M) {
            for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
            sb.append("\n");
            return;
        }
        for (int i : ts) {
            if(vis[i]) continue;
            vis[i] = true;
            arr[level] = i;
            go(level+1);
            vis[i] = false;
        }
    }
}