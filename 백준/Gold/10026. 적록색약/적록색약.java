import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] board = new String[N];
        boolean[][] vis1 = new boolean[N][N];
        boolean[][] vis2 = new boolean[N][N];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < N; i++)
            board[i] = br.readLine();

        int cnt1 = 0;
        int cnt2 = 0;
        Queue<Pair<Integer>> q = new ArrayDeque<>();
        for(int f = 0; f < 2; f++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (vis1[i][j]) continue;
                    char first = board[i].charAt(j);
                    q.add(new Pair<>(i, j));
                    vis1[i][j] = true;
                    cnt1++;
                    while (!q.isEmpty()) {
                        Pair<Integer> cur = q.remove();
                        for (int k = 0; k < 4; k++) {
                            int nx = cur.x + dx[k];
                            int ny = cur.y + dy[k];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                            if (vis1[nx][ny]) continue;
                            if(f == 0 && board[nx].charAt(ny) != first) continue;
                            q.add(new Pair<>(nx, ny));
                            vis1[nx][ny] = true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(vis2[i][j]) continue;
                char first = board[i].charAt(j);
                q.add(new Pair<>(i, j));
                vis2[i][j] = true;
                cnt2++;
                while (!q.isEmpty()) {
                    Pair<Integer> cur = q.remove();
                    for (int k = 0; k < 4; k++) {
                        int nx = cur.x + dx[k];
                        int ny = cur.y + dy[k];

                        if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        if(vis2[nx][ny]) continue;
                        if((first == 'R' || first == 'G') && board[nx].charAt(ny) == 'B') continue;
                        else if (first == 'B' && board[nx].charAt(ny) != 'B') continue;
                        q.add(new Pair<>(nx, ny));
                        vis2[nx][ny] = true;
                    }
                }
            }
        }
        bw.write(cnt1 + " " + cnt2);
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