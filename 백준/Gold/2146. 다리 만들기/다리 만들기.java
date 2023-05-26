import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static byte[][] board;
    static Queue<Pair> q = new ArrayDeque<>();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        board = new byte[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                board[i][j] = Byte.parseByte(st.nextToken());
        }
        for(int[] tmp : map) Arrays.fill(tmp, -1);
        int groupID = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] >= 0|| board[i][j] == 0) continue;
                q.add(new Pair(i, j));
                map[i][j] = ++groupID;
                while(!q.isEmpty()) {
                    Pair cur = q.remove();
                    for (int k = 0; k < 4; k++) {
                        int nx = cur.x + dx[k];
                        int ny = cur.y + dy[k];
                        if (isOutOfRange(nx, ny)) continue;
                        if(map[nx][ny] >= 0 || board[nx][ny] == 0) continue;
                        q.add(new Pair(nx, ny));
                        map[nx][ny] = groupID;
                    }
                }
            }
        }
        int result = 987654321;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == 0) continue;
                int[][] dist = new int[N][N];
                for(int[] tmp : dist) Arrays.fill(tmp, -1);
                q.add(new Pair(i, j));
                dist[i][j] = 0;
                int islandNo = map[i][j];
                wrap:
                while (!q.isEmpty()) {
                    Pair cur = q.remove();
                    for (int k = 0; k < 4; k++) {
                        int nx = cur.x + dx[k];
                        int ny = cur.y + dy[k];
                        if(isOutOfRange(nx,ny) || dist[nx][ny] >= 0) continue;
                        if (board[nx][ny] == 1) {
                            if (map[nx][ny] != islandNo) {
                                result = Math.min(dist[cur.x][cur.y], result);
                                q.clear();
                                break wrap;
                            }
                            else continue;
                        }
                        q.add(new Pair(nx, ny));
                        dist[nx][ny] = dist[cur.x][cur.y] + 1;
                    }
                }
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
    private static boolean isOutOfRange(int nx, int ny) {
        return (nx < 0 || ny < 0 || nx >= N || ny >= N);
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
