import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int[][] board;
    static boolean[][] vis;
    static boolean[][] isBlack;
    static int[] dy = {-1, 1};
    static int[] ans = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        vis = new boolean[n][n];
        isBlack = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                isBlack[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0);
            }
        }
        go(0, 0, true);
        go(0, 0, false);
        System.out.println(ans[0] + ans[1]);
    }

    static void go(int start, int level, boolean black) {
        ans[black ? 0 : 1] = Math.max(ans[black ? 0 : 1], level);
        for (int i = start; i < n * n; i++) {
            int nx = i / n;
            int ny = i % n;
            if(board[nx][ny] == 0 || isBlack[nx][ny] != black || check(nx, ny)) continue;
            vis[nx][ny] = true;
            go(i+1, level+1, black);
            vis[nx][ny] = false;
        };
    }
    static boolean check(int x, int y) {
        for (int nx = x - 1; nx >= 0; nx--) {
            for(int dir = 0; dir < 2; dir++) {
                int ny = y + dy[dir] * (x - nx);
                if(ny < 0 || ny >= n) continue;
                if(vis[nx][ny]) return true;
            }
        }
        return false;
    }
}