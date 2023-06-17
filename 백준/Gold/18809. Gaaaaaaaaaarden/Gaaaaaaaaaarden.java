import java.io.*;
import java.util.*;
public class Main {
    static int x, y, g, r;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] vis;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        board = new int[x][y];
        vis = new boolean[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < y; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        paintGreen(0, 0);
        System.out.println(ans);
    }

    static void paintGreen(int level, int start) {
        if (level == g) {
            paintRed(0, 0);
            return;
        }
        for (int i = start; i < x * y; i++) {
            int nx = i / y; int ny = i % y;
            if (board[nx][ny] != 2) continue;
            board[nx][ny] = 3;
            paintGreen(level + 1, i + 1);
            board[nx][ny] = 2;
        }
    }

    static void paintRed(int level, int start) {
        if (level == r) {
            bfs();
            return;
        }
        for (int i = start; i < x * y; i++) {
            int nx = i / y; int ny = i % y;
            if (board[i / y][i % y] != 2) continue;
            board[nx][ny] = 4;
            paintRed(level + 1, i + 1);
            board[nx][ny] = 2;
        }
    }

    //3은 녹색, 4는 적색
    static void bfs() {
        Queue<Pair> greenQ = new LinkedList<>();
        Queue<Pair> redQ = new LinkedList<>();
        boolean[][] isFlower = new boolean[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 3) {
                    vis[i][j] = true;
                    greenQ.add(new Pair(i, j));
                } else if (board[i][j] == 4) {
                    vis[i][j] = true;
                    redQ.add(new Pair(i, j));
                }
            }
        }
        int flowerCnt = 0;
        boolean[][] bloomAble = new boolean[x][y];
        while (!greenQ.isEmpty() || !redQ.isEmpty()) {
            int gs = greenQ.size();
            while (gs --> 0) {
                Pair cur = greenQ.remove();
                bloomAble[cur.x][cur.y] = false;
                if(isFlower[cur.x][cur.y]) continue;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if (nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                    if (board[nx][ny] == 0 || vis[nx][ny]) continue;
                    vis[nx][ny] = true;
                    greenQ.add(new Pair(nx, ny));
                    bloomAble[nx][ny] = true;
                }
            }
            if(greenQ.isEmpty()) break;
            int rs = redQ.size();
            while (rs --> 0) {
                Pair cur = redQ.remove();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if (nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                    if (board[nx][ny] == 0) continue;
                    if (bloomAble[nx][ny]) {
                        flowerCnt++;
                        isFlower[nx][ny] = true;
                        bloomAble[nx][ny] = false;
                        continue;
                    }
                    if (vis[nx][ny]) continue;
                    redQ.add(new Pair(nx, ny));
                    vis[nx][ny] = true;
                }
            }
        }
        ans = Math.max(ans, flowerCnt);
        vis = new boolean[x][y];
    }

    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}