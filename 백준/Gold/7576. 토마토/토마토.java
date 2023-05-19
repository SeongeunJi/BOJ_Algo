import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[][] board = new int[x][y];
        boolean[][] vis = new boolean[x][y];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        Queue<Pair> Q = new ArrayDeque<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 1) {
                    Q.add(new Pair(i, j));
                    vis[i][j] = true;
                }
            }
        }

        int dayTotal = 0;
        while (!Q.isEmpty()) {
            Pair cur = Q.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if(vis[nx][ny] || board[nx][ny] != 0) continue;
                dayTotal = board[nx][ny] = board[cur.x][cur.y] + 1;
                Q.add(new Pair(nx,ny));
                vis[nx][ny] = true;
            }
        }

        for (int[] arr : board) {
            for (int tmp : arr) {
                if(tmp == 0) {
                    dayTotal = -1;
                    break;
                }
            }
        }

        bw.write(String.valueOf((dayTotal == -1) ? -1 : (dayTotal == 0) ? 0 : dayTotal -1));
        bw.flush();
        bw.close();
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}