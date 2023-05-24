import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int len = Integer.parseInt(br.readLine());
        int[][] board = new int[len][len];
        int maxHeight = 0;
        for (int i = 0; i < len; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, board[i][j]);
            }
        }
        Queue<Pair> q = new LinkedList<>();
        int result = 1, cnt = -1;
        for (int nowHeight = 1; cnt != 0; nowHeight++) {
            boolean[][] vis = new boolean[len][len];
            result = Math.max(result, cnt);
            cnt = 0;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if(vis[i][j] || board[i][j] <= nowHeight) continue;
                    q.add(new Pair(i, j)); vis[i][j] = true;
                    cnt++;
                    while (!q.isEmpty()) {
                        Pair cur = q.remove();
                        for (int k = 0; k < 4; k++) {
                            int nx = cur.x + dx[k];
                            int ny = cur.y + dy[k];
                            if(nx < 0 || ny < 0 || nx >= len || ny >= len) continue;
                            if(vis[nx][ny] || board[nx][ny] <= nowHeight) continue;
                            q.add(new Pair(nx, ny));
                            vis[nx][ny] = true;
                        }
                    }
                }
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
    static class Pair {
        int x,y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
