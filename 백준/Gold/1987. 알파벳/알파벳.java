import java.io.*;
import java.util.*;
public class Main {
    static int x, y, ans;
    static String[] board;
    static boolean[] vis;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        board = new String[x];
        vis = new boolean[26];
        for(int i = 0; i < x; i++) board[i] = br.readLine();
        vis[board[0].charAt(0) - 'A'] = true;
        go(1, 0, 0);
        System.out.println(ans);
    }
    static void go(int level, int cx, int cy) {
        ans = Math.max(ans, level);
        for (int dir = 0; dir < 4; dir++) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
            char ch = board[nx].charAt(ny);
            if(vis[ch-'A']) continue;
            vis[ch-'A'] = true;
            go(level + 1, nx, ny);
            vis[ch - 'A'] = false;
        }
    }
}