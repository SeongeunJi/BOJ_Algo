import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] vis;
    static List<Integer> list = new ArrayList<>();
    static int N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        vis = new boolean[N+1];
        go(0);
        System.out.print(sb);
    }

    static void go(int level) {
        if(level == M) {
            list.forEach(e -> sb.append(e).append(" "));
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            list.add(i);
            go(level+1);
            vis[i] = false;
            list.remove(new Integer(i));
        }
    }
}