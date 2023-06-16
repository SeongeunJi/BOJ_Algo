import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] vis = new boolean[10];
    static int[] arr = new int[10];
    static int N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        go(0);
        System.out.print(sb);
    }

    static void go(int level) {
        if(level == M) {
            for (int i = 0; i < level; i++) sb.append(arr[i]).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            arr[level] = i;
            go(level+1);
            vis[i] = false;
        }
    }
}