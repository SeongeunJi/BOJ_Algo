import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static int[] arr = new int[10];
    static boolean[] vis = new boolean[10];
    static List<Integer> list = new ArrayList<>();
    static Set<String> lhs = new LinkedHashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
        Collections.sort(list);
        go(0, 0);
        lhs.forEach(str -> sb.append(str).append("\n"));
        System.out.println(sb);
    }

    static void go(int level, int start) {
        if (level == M) {
            for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
            lhs.add(sb.toString());
            sb = new StringBuilder();
            return;
        }
        for (int i = start; i < N; i++) {
            if(vis[i]) continue;
            arr[level] = list.get(i);
            vis[i] = true;
            go(level+1, i);
            vis[i] = false;
        }
    }
}