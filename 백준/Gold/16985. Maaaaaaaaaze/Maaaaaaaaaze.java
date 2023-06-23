import java.io.*;
import java.util.*;
public class Main {
    static int N = 5, ans = Integer.MAX_VALUE;
    static boolean[] used = new boolean[N];
    static int[] idxArr = new int[N];
    static int[][][] maze = new int[N][N][N];
    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static Queue<Tuple> q = new LinkedList<>();
    static int[][][][] board = new int[4][N][N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k = 0; k < N; k++) {
                    board[0][i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
            for(int j = 0; j < N; j++)
                for(int k = 0; k < N; k++)
                    board[1][i][k][N-1-j] = board[0][i][j][k];
            for(int j = 0; j < N; j++)
                for(int k = 0; k < N; k++)
                    board[2][i][k][N-1-j] = board[1][i][j][k];
            for(int j = 0; j < N; j++)
                for(int k = 0; k < N; k++)
                    board[3][i][k][N-1-j] = board[2][i][j][k];
        }
        backtracking_idx(0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void backtracking_idx(int level) {
        if(level == N) {
            brute_dir();
            return;
        }
        for(int i = 0; i < N; i++) {
            if(used[i]) continue;
            idxArr[level] = i;
            used[i] = true;
            backtracking_idx(level+1);
            used[i] = false;
        }
    }

    static void brute_dir() {
        for (int tmp = 0; tmp < (1 << 2 * N); tmp++) {
            int brute = tmp;
            for (int i = 0; i < N; i++) {
                int dir = brute % 4;
                brute /= 4;
                for (int j = 0; j < N; j++)
                    System.arraycopy(board[dir][idxArr[i]][j], 0, maze[i][j], 0, N);
            }
            ans = Math.min(ans, bfs());
            q.clear();
        }
    }

    static int bfs() {
        if(maze[0][0][0] == 0 || maze[4][4][4] == 0) return Integer.MAX_VALUE;
        int[][][] dist = new int[N][N][N];
        q.add(new Tuple(0, 0, 0));
        dist[0][0][0] = 1;
        while (!q.isEmpty()) {
            Tuple cur = q.remove();
            for (int dir = 0; dir < 6; dir++) {
                int nz = cur.z + dz[dir];
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= N || nz >= N) continue;
                if(maze[nz][nx][ny] == 0 || dist[nz][nx][ny] != 0) continue;
                if(nz == 4 && nx == 4 && ny == 4) return dist[cur.z][cur.x][cur.y];
                q.add(new Tuple(nz, nx, ny));
                dist[nz][nx][ny] = dist[cur.z][cur.x][cur.y] + 1;
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