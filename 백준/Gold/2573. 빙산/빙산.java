import java.io.*;
import java.util.*;
public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] vis;
    static Map<Pair, Integer> hm = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[][] iceberg = new int[x][y];

        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++)
                iceberg[i][j] = Byte.parseByte(st.nextToken());
        }

        int result = 0;
        boolean allZero = false;
        Queue<Pair> q = new ArrayDeque<>();
        outer:
        for(int m = 0; !allZero; m++) {
            vis = new boolean[x][y];
            allZero = true;
            int lump = 0;
            for(int i = 0; i < x; i++) {
                for(int j = 0; j < y; j++) {
                    if(iceberg[i][j] == 0 || vis[i][j]) continue;
                    if(++lump >= 2) {
                        result = m;
                        break outer;
                    }
                    q.add(new Pair(i, j));
                    vis[i][j] = true; allZero = false;
                    bfs(x, y, iceberg, q);
                    hm.keySet().forEach(k -> iceberg[k.x][k.y] = hm.get(k));
                    hm.clear();
                }
            }
        }
        bw.write(String.valueOf((result)));
        bw.flush();
        bw.close();
    }
    private static void bfs(int x, int y, int[][] iceberg, Queue<Pair> q) {
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            byte seaCnt = 0;
            for (int idx = 0; idx < 4; idx++) {
                int nx = cur.x + dx[idx];
                int ny = cur.y + dy[idx];
                if (nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if (iceberg[nx][ny] == 0) seaCnt++;
                else if(!vis[nx][ny] && iceberg[nx][ny] > 0) {
                    vis[nx][ny] = true;
                    q.add(new Pair(nx, ny));
                }
            }
            hm.put(cur, Math.max(iceberg[cur.x][cur.y] - seaCnt, 0));
        }
    }
    static class Pair {
         int x, y;
         public Pair(int x, int y) {
             this.x = x;
             this.y = y;
         }
    }
}