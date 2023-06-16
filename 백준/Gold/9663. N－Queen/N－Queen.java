import java.util.*;
public class Main {
    static int N;
    static int ans;
    static boolean[] visR;
    static boolean[][] vis;
    static int[] dy = {-1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visR = new boolean[N];
        vis = new boolean[N][N];
        go(0);
        System.out.println(ans);
    }
    static void go(int level) {
        if (level == N) {
            ans++; return;
        }
        for (int i = 0; i < N; i++) {
            if(visR[i]) continue;
            if(attackAble(level, i)) continue;
            vis[level][i] = true;
            visR[i] = true;
            go(level+1);
            visR[i] = false;
            vis[level][i] = false;
        }
    }

    static boolean attackAble(int x, int y) {
        for(int i = x-1; i >= 0; i--) {
            for(int j = 0; j < 2; j++) {
                int ny = y + dy[j] * (x - i);
                if(ny < 0 || ny >= N) continue;
                if(vis[i][ny]) return true;
            }
        }
        return false;
    }
}