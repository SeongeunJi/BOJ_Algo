import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = ps(st);
        int m = ps(st);
        int[][] board = new int[n][n];
        boolean[][] isTurnOn = new boolean[n][n];
        boolean[][] vis = new boolean[n][n];
        boolean[][] moveAble = new boolean[n][n];
        Map<Integer, List<Integer>> roomMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = (i * n) + j + 1;
                roomMap.put(board[i][j], new ArrayList<>());
            }
        }
        while(m --> 0) {
            st = new StringTokenizer(br.readLine());
            roomMap.get((ps(st)-1) * n + ps(st))
                    .add((ps(st)-1) * n + ps(st));
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        isTurnOn[0][0] = true;
        vis[0][0] = true;
        int result = 1;
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                moveAble[nx][ny] = true;
            }
            for (int rooms : roomMap.get(board[cur.x][cur.y])) {
                int x = (rooms - 1) / n;
                int y = (rooms - 1) % n;
                if(isTurnOn[x][y]) continue;
                isTurnOn[x][y] = true;
                result++;
                if (moveAble[x][y] && !vis[x][y]) {
                    vis[x][y] = true;
                    q.add(new Pair(x, y));
                }
            }
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(!isTurnOn[nx][ny] || vis[nx][ny] || !moveAble[nx][ny]) continue;
                q.add(new Pair(nx, ny));
                vis[nx][ny] = true;
            }
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    private static int ps(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
