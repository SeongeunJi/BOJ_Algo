import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[x][y];
        boolean[][] vis = new boolean[x][y];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++)
                matrix[i][j] = Integer.parseInt(st.nextToken());
        }

        Queue<Pair<Integer>> Q = new LinkedList<>();
        int maxArea = 0;
        int cnt = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matrix[i][j] == 1 && !vis[i][j]) {
                    cnt++;
                    vis[i][j] = true;
                    Q.add(new Pair<>(i, j));

                    int tmp = 0;
                    while (!Q.isEmpty()) {
                        Pair<Integer> cur = Q.remove();
                        tmp++;
                        for (int k = 0; k < 4; k++) {
                            int nx = cur.x + dx[k];
                            int ny = cur.y + dy[k];
                            if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                            if(matrix[nx][ny] == 0 || vis[nx][ny]) continue;
                            Q.add(new Pair<>(nx, ny));
                            vis[nx][ny] = true;
                        }
                    }
                    maxArea = Math.max(maxArea, tmp);
                }
            }
        }
        bw.write(cnt + "\n" + maxArea);
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
