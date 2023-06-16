import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringBuilder sb2 = new StringBuilder();
    static List<Integer> list = new ArrayList<>();
    static int M = 6;
    static int N;
    static int[] arr = new int[6];
    static boolean[] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            vis = new boolean[N];
            if(N == 0) break;
            while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
            go(0, 0);
            sb2.append(sb).append("\n");
            sb = new StringBuilder();
            list.clear();
        }
        System.out.println(sb2);
    }

    static void go(int level, int start) {
        if (level == M) {
            for(int i : arr) sb.append(i).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = start; i < N; i++) {
            if(vis[i]) continue;
            arr[level] = list.get(i);
            vis[i] = true;
            go(level + 1, i);
            vis[i] = false;
        }
    }
}