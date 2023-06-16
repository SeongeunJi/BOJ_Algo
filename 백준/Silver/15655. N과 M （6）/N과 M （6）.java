import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] vis = new boolean[10001];
    static int[] arr = new int[10];
    static List<Integer> list = new ArrayList<>();
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
        Collections.sort(list);
        go(0, 0);
        System.out.println(sb);
    }

    static void go(int level, int start) {
        if (level == M) {
            for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = start; i < N; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            arr[level] = list.get(i);
            go(level+1, i);
            vis[i] = false;
        }
    }
}