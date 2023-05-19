import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());

        int[][][] board = new int[z][x][y];
        boolean[][][] vis = new boolean[z][x][y];
        int[] dx = {1, 0, -1, 0, 0, 0};
        int[] dy = {0, -1, 0, 1, 0 ,0};
        int[] dz = {0, 0, 0, 0, -1, 1};

        Queue<Pair> Q = new ArrayDeque<>();
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < x; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < y; k++) {
                    board[i][j][k] = Integer.parseInt(st.nextToken());
                    if(board[i][j][k] == 1) {
                        Q.add(new Pair(j,k,i));
                        vis[i][j][k] = true;
                    }
                }
            }
        }

        while (!Q.isEmpty()) {
            Pair cur = Q.remove();
            for (int i = 0; i < 6; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];
                if(nx < 0 || ny < 0 || nz < 0 || nx >= x || ny >= y || nz >= z) continue;
                if(board[nz][nx][ny] != 0 || vis[nz][nx][ny]) continue;
                board[nz][nx][ny] = board[cur.z][cur.x][cur.y] + 1;
                Q.add(new Pair(nx, ny, nz));
                vis[nz][nx][ny] = true;
            }
        }

        int result = 0;
        loop1 :
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < x; j++) {
                for (int k = 0; k < y; k++) {
                    if (board[i][j][k] == 0) {
                        result = -1;
                        break loop1;
                    }
                    result = Math.max(result, board[i][j][k]);
                }
            }
        }

        bw.write(String.valueOf((result <= 0) ? result : result -1));
        bw.flush();
        bw.close();
    }
    static class Pair {
        int x, y, z;
        public Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}