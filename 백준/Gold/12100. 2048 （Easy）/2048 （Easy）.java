import java.io.*;
import java.util.*;
public class Main {
    static int N, ans;
    static int[][] board1;
    static int[][] dirArr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북,동,남,서
    static boolean[][] vis;
    static Queue<Pair> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board1 = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        go(0);
        System.out.println(ans);
    }

    static void go(int level) {
        if(level == 5) {
            ans = Math.max(ans, getAns());
            return;
        }
        for(int dir = 0; dir < 4; dir++) {
            int[][] board2 = new int[N][N];
            for(int i = 0; i < N; i++)
                System.arraycopy(board1[i], 0, board2[i], 0, N);
            move(dir, level);
            go(level+1);
            for(int i = 0; i < N; i++)
                System.arraycopy(board2[i], 0, board1[i], 0, N);
        }
    }
    static void move(int dir, int level) {
        vis = new boolean[N][N];
        switch (dir) {
            case 0 -> move_up(dirArr[dir]);
            case 1 -> move_right(dirArr[dir]);
            case 2 -> move_down(dirArr[dir]);
            case 3 -> move_left(dirArr[dir]);
        }
    }

    static void move_up(int[] dirArr) {
        for (int i = 0; i < N; i++) {
            for(int j = 1; j < N; j++) {
                if(board1[j][i] == 0) continue;
                q.add(new Pair(j, i));
                bfs(dirArr);
            }
        }
    }

    static void move_right(int[] dirArr) {
        for (int i = 0; i < N; i++) {
            for (int j = N - 2; j >= 0; j--) {
                if(board1[i][j] == 0) continue;
                q.add(new Pair(i, j));
                bfs(dirArr);
            }
        }
    }

    static void move_down(int[] dirArr) {
        for (int i = 0; i < N; i++) {
            for (int j = N - 2; j >= 0; j--) {
                if(board1[j][i] == 0) continue;
                q.add(new Pair(j, i));
                bfs(dirArr);
            }
        }
    }

    static void move_left(int[] dirArr) {
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if(board1[i][j] == 0) continue;
                q.add(new Pair(i, j));
                bfs(dirArr);
            }
        }
    }
    private static void bfs(int[] dirArr) {
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            int nx = cur.x + dirArr[0];
            int ny = cur.y + dirArr[1];
            if(OOB(nx, ny)) break;
            if(board1[nx][ny] == 0) {
                board1[nx][ny] = board1[cur.x][cur.y];
                board1[cur.x][cur.y] = 0;
                q.add(new Pair(nx, ny));
            }
            else if(board1[nx][ny] == board1[cur.x][cur.y] && !vis[nx][ny]) {
                vis[nx][ny] = true;
                board1[nx][ny] *= 2;
                board1[cur.x][cur.y] = 0;
            }
        }
    }
    static int getAns() {
        int val = 0;
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                val = Math.max(val, board1[i][j]);
            }
        }
        return val;
    }
    private static boolean OOB(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= N;
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}