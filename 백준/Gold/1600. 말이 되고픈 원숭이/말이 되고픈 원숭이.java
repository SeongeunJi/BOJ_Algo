import java.io.*;
import java.util.*;
public class Main {
    static Queue<Tuple> q = new LinkedList<>();
    static int k, x, y;
    static int[][][] dist;
    static byte[][] board;
    static int[] hdx = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] hdy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        board = new byte[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; st.hasMoreTokens(); j++)
                board[i][j] = Byte.parseByte(st.nextToken());
        }

        dist = new int[k+1][x][y];
        for (int[][] tmp1 : dist) {
            for(int[] tmp2 : tmp1) Arrays.fill(tmp2, -1);
        }
        q.add(new Tuple(0,0,0));
        dist[0][0][0] = 0;
        bw.write(String.valueOf(bfs()));
        bw.flush();
        bw.close();
    }
    static int bfs() {
        while (!q.isEmpty()) {
            Tuple cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(OOB(nx, ny) || board[nx][ny] == 1) continue;
                if(dist[cur.k][nx][ny] >= 0) continue;
                q.add(new Tuple(cur.k, nx, ny));
                dist[cur.k][nx][ny] = dist[cur.k][cur.x][cur.y] + 1;
            }
            if(cur.k < k) { // 말이 될 수 있음.
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + hdx[i];
                    int ny = cur.y + hdy[i];
                    if(OOB(nx, ny) || board[nx][ny] == 1) continue;
                    if(dist[cur.k+1][nx][ny] >= 0) continue;
                    q.add(new Tuple(cur.k + 1, nx, ny));
                    dist[cur.k+1][nx][ny] = dist[cur.k][cur.x][cur.y] + 1;
                }
            }
        }
        return getETA();
    }
    static int getETA() {
        boolean reachable = false;
        int result = 987654321;
        for (int[][] tmp : dist) {
            if(tmp[x-1][y-1] == -1) continue;
            result = Math.min(result, tmp[x-1][y-1]);
            reachable = true;
        }
        return (!reachable) ? -1 : result;
    }

    static boolean OOB(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= x || ny >= y;
    }
    static class Tuple {
        int k, x, y;
        public Tuple(int k, int x, int y) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
}
