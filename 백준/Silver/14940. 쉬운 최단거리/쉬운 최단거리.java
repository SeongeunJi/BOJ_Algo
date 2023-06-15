import java.io.*;
import java.util.*;
public class Main {
    static int x, y;
    static int[][] board;
    static int[][] dist;
    static Queue<Pair> q = new LinkedList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        board = new int[x][y];
        dist = new int[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1) dist[i][j] = -1;
                else if (board[i][j] == 2) {
                    q.add(new Pair(i, j));
                    board[i][j] = 2;
                    dist[i][j] = 0;
                }
                else dist[i][j] = 0;
            }
        }
        bfs();
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >=x || ny >= y) continue;
                if(dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                q.add(new Pair(nx, ny));
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) sb.append(dist[i][j]).append(" ");
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
