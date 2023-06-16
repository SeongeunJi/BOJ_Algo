import java.util.*;
public class Main {
    static int N, M;
    static boolean[] vis = new boolean[10];
    static int[] arr = new int[10];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        go(0, 1);
        System.out.print(sb);
    }

    static void go(int level, int start) {
        if (level == M) {
            for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = start; i <= N; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            arr[level] = i;
            go(level + 1, i);
            vis[i] = false;
        }
    }
}