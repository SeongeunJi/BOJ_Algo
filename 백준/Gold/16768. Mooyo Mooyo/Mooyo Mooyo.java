import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] board;
    static boolean[][] vis;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Pair> q1 = new LinkedList<>();
    static Queue<Pair> q2 = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][10];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < 10; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }
        game();
    }

    static void game() {
        boolean finish = false;
        while (!finish) {
            vis = new boolean[N][10];
            int tmp = 0;
            for(int i = N-1; i >= 0; i--) {
                for(int j = 0; j < 10; j++) {
                    if(board[i][j] == 0 || vis[i][j]) continue;
                    tmp += bfs(i, j);
                }
            }
            if(tmp > 0) moveByGravity();
            else finish = true;
        }
        print();
    }

    static void moveByGravity() {
        for (int i = 0; i < 10; i++) {
            int idx = 0;
            int[] tilted = new int[N];
            for(int j = N-1; j >= 0; j--) {
                if(board[j][i] == 0) continue;
                tilted[idx++] = board[j][i];
            }
            for (int j = 0; j < N; j++)
                board[N - 1 - j][i] = tilted[j];
        }
    }

    static int bfs(int x, int y) {
        int cnt = 0;
        q1.add(new Pair(x, y));
        while (!q1.isEmpty()) {
            Pair cur = q1.remove();
            for(int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= N || ny >= 10) continue;
                if(board[nx][ny] != board[x][y] || vis[nx][ny]) continue;
                q1.add(new Pair(nx, ny));
                q2.add(new Pair(nx, ny));
                vis[nx][ny] = true;
                cnt++;
            }
        }
        if(cnt >= K) {
            while (!q2.isEmpty()) {
                Pair cur = q2.remove();
                board[cur.x][cur.y] = 0;
            }
            return 1;
        }
        q2.clear();
        return 0;
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < 10; j++)
                sb.append(board[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}