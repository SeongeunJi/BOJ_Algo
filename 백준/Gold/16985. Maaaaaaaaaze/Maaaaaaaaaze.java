import java.io.*;
import java.util.*;
public class Main {
    static int n = 5, ans = Integer.MAX_VALUE;
    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int[][][][] board = new int[4][n][n][n];
    static int[][][] maze = new int[n][n][n];
    static int[] orders = new int[n];
    static boolean[] used = new boolean[n];
    static Queue<Tuple> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++)
                    board[0][i][j][k] = Integer.parseInt(st.nextToken());
            }
            for(int j = 0; j < n; j++)
                for(int k = 0; k < n; k++)
                    board[1][i][k][n-1-j] = board[0][i][j][k];
            for(int j = 0; j < n; j++)
                for(int k = 0; k < n; k++)
                    board[2][i][k][n-1-j] = board[1][i][j][k];
            for(int j = 0; j < n; j++)
                for(int k = 0; k < n; k++)
                    board[3][i][k][n-1-j] = board[2][i][j][k];
        }
        go(0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void go(int level) {
        if(level == n) {
            determine_dir();
            return;
        }
        for(int i = 0; i < n; i++) {
            if(used[i]) continue;
            used[i] = true;
            orders[level] = i;
            go(level + 1);
            used[i] = false;
        }
    }

    static void determine_dir() {
        for(int tmp = 0; tmp < 1 << 10; tmp++) {
            int brute = tmp;
            for(int i = 0; i < n; i++) {
                int dir = brute & 3;
                brute >>= 2;
                for (int j = 0; j < n; j++)
                    System.arraycopy(board[dir][orders[i]][j], 0, maze[i][j], 0, n);
            }
            ans = Math.min(ans, bfs());
            q.clear();
        }
    }

    static int bfs() {
        if(maze[0][0][0] == 0 || maze[4][4][4] == 0) return Integer.MAX_VALUE;
        int[][][] dist = new int[n][n][n];
        dist[0][0][0] = 1;
        q.add(new Tuple(0, 0, 0));
        while (!q.isEmpty()) {
            Tuple cur = q.remove();
            for (int dir = 0; dir < 6; dir++) {
                int nz = cur.z + dz[dir];
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nz < 0 || nx < 0 || ny < 0 || nz >= n || nx >= n || ny >= n) continue;
                if(maze[nz][nx][ny] == 0 || dist[nz][nx][ny] != 0) continue;
                if(nz == 4 && nx == 4 && ny == 4) return dist[cur.z][cur.x][cur.y];
                dist[nz][nx][ny] = dist[cur.z][cur.x][cur.y] + 1;
                q.add(new Tuple(nz, nx, ny));
            }
        }
        return Integer.MAX_VALUE;
    }

    static class Tuple {
        int z, x, y;
        public Tuple(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}