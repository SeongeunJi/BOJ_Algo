import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] board = new String[N];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < N; i++)
            board[i] = br.readLine();

        Queue<Pair<Integer>> q = new ArrayDeque<>();
        for(int h = 0; h < 2; h++) {
            boolean[][] vis = new boolean[N][N];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(vis[i][j]) continue;
                    cnt++;
                    q.add(new Pair<>(i, j));
                    char first = board[i].charAt(j);
                    while (!q.isEmpty()) {
                        Pair<Integer> cur = q.remove();
                        for (int k = 0; k < 4; k++) {
                            int nx = cur.x + dx[k];
                            int ny = cur.y + dy[k];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                            if (vis[nx][ny]) continue;
                            if (h == 0) {
                                if (board[nx].charAt(ny) != first) continue;
                            } else {
                                if((first == 'R' || first == 'G') && board[nx].charAt(ny) == 'B') continue;
                                else if (first == 'B' && board[nx].charAt(ny) != 'B') continue;
                            }
                            q.add(new Pair<>(nx, ny));
                            vis[nx][ny] = true;
                        }
                    }
                }
            }
            bw.write(cnt + " ");
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