import java.util.*;
import java.io.*;
public class Main {
    static int N, L, R, ans;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][][] open;
    static Queue<Pair> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        move();
        System.out.println(ans);
    }

    static void move() {
        while(true) {
            open = new boolean[N][N][5];
            boolean flag = false;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    for(int dir = 0; dir < 4; dir++) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        if(OOB(nx, ny)) continue;
                        int dif = Math.abs(board[nx][ny] - board[i][j]);
                        if(L <= dif && dif <= R) {
                            open[i][j][dir] = true;
                            open[nx][ny][(dir+2)%4] = true;
                            open[i][j][4] = true;
                            open[nx][ny][4] = true;
                            flag = true;
                        }
                    }
                }
            }
            if(!flag) return;
            bfs();
            ans++;
        }
    }

    private static boolean OOB(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= N;
    }

    static void bfs() {
        boolean[][] vis = new boolean[N][N];
        Queue<Pair> q2 = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(vis[i][j] || !open[i][j][4]) continue;
                int sum = board[i][j];
                vis[i][j] = true;
                q.add(new Pair(i, j));
                q2.add(new Pair(i, j));
                while (!q.isEmpty()) {
                    Pair cur = q.remove();
                    for(int dir = 0; dir < 4; dir++) {
                        int nx = cur.x + dx[dir];
                        int ny = cur.y + dy[dir];
                        if(OOB(nx, ny) || !open[cur.x][cur.y][dir] || vis[nx][ny]) continue;
                        vis[nx][ny] = true;
                        sum += board[nx][ny];
                        q.add(new Pair(nx, ny));
                        q2.add(new Pair(nx, ny));
                    }
                }
                int val = sum / q2.size();
                while (!q2.isEmpty()) {
                    Pair cur = q2.remove();
                    board[cur.x][cur.y] = val;
                }
            }
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