import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        while (T --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] board = new int[x+1][y+1];
            boolean[][] vis = new boolean[x+1][y+1];

            while (k --> 0) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                board[n][m] = 1;
            }

            Queue<Pair<Integer>> q = new ArrayDeque<>();
            int result = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if(board[i][j] == 0 || vis[i][j]) continue;
                    q.add(new Pair<>(i, j));
                    vis[i][j] = true;
                    result++;

                    while (!q.isEmpty()) {
                        Pair<Integer> cur = q.remove();
                        for (int h = 0; h < 4; h++) {
                            int nx = cur.x + dx[h];
                            int ny = cur.y + dy[h];

                            if(nx < 0 || ny < 0 || nx > x || ny > y) continue;
                            if(vis[nx][ny] || board[nx][ny] == 0) continue;

                            q.add(new Pair<>(nx, ny));
                            vis[nx][ny] = true;
                        }
                    }
                }
            }
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
    static class Pair<T> {
        T x, y;
        public Pair(T x, T y) {
            this.x = x;
            this.y = y;
        }
    }
}