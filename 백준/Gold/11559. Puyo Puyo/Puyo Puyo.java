import java.io.*;
import java.util.*;
public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] board = new char[12][6];
    static boolean[][] vis;
    static Queue<Pair> q1 = new LinkedList<>();
    static Queue<Pair> q2 = new LinkedList<>();
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for(int j = 0; j < 6; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        game();
    }
    static void game() {
        boolean finish = false;
        while(!finish) {
            vis = new boolean[12][6];
            int tmp = 0;
            for (int i = 11; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] == '.' || vis[i][j]) continue;
                    tmp += bfs(i, j);
                }
            }
            if(tmp > 0) {
                ans++;
                moveByGravity();
            }
            else finish = true;
        }
        System.out.println(ans);
    }
    static int bfs(int x, int y) {
        int cnt = 0;
        q1.add(new Pair(x, y));
        while (!q1.isEmpty()) {
            Pair cur = q1.remove();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;
                if(board[nx][ny] != board[x][y] || vis[nx][ny]) continue;
                vis[nx][ny] = true;
                q1.add(new Pair(nx, ny));
                q2.add(new Pair(nx, ny));
                cnt++;
            }
        }
        if(cnt >= 4) {
            while (!q2.isEmpty()) {
                Pair cur = q2.remove();
                board[cur.x][cur.y] = '.';
            }
            return 1;
        }
        else q2.clear();
        return 0;
    }
    static void moveByGravity() {
        for (int i = 0; i < 6; i++) {
            int idx = 0;
            char[] tilted = new char[12];
            Arrays.fill(tilted, '.');
            for (int j = 11; j >= 0; j--) {
                if(board[j][i] == '.') continue;
                tilted[idx++] = board[j][i];
            }
            for (int j = 0; j < 12; j++)
                board[11-j][i] = tilted[j];
        }
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}