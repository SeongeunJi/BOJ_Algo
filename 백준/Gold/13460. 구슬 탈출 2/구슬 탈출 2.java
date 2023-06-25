import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][][][] vis;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Pair> red = new LinkedList<>();
    static Queue<Pair> blue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        vis = new boolean[N][M][N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
                if(board[i][j] == 'R') {
                    board[i][j] = '.';
                    red.add(new Pair(i, j));
                }
                else if(board[i][j] == 'B') {
                    board[i][j] = '.';
                    blue.add(new Pair(i, j));
                }
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(red.remove(), blue.remove()));
        Tuple peek = q.peek();
        vis[peek.red.x][peek.red.y][peek.blue.x][peek.blue.y] = true;
        int times = 0;
        while (!q.isEmpty()) {
            times++;
            if(times > 10) return -1;
            int sz = q.size();
            while(sz --> 0) {
                Tuple cur = q.remove();
                int rx = cur.red.x, ry = cur.red.y;
                int bx = cur.blue.x, by = cur.blue.y;
                for (int dir = 0; dir < 4; dir++) {
                    int n_rx = rx, n_ry = ry, n_bx = bx, n_by = by;
                    while(board[n_bx + dx[dir]][n_by + dy[dir]] == '.') {
                        n_bx += dx[dir];
                        n_by += dy[dir];
                    }
                    if(board[n_bx + dx[dir]][n_by + dy[dir]] == 'O') continue;

                    while(board[n_rx + dx[dir]][n_ry + dy[dir]] == '.') {
                        n_rx += dx[dir];
                        n_ry += dy[dir];
                    }
                    if(board[n_rx + dx[dir]][n_ry + dy[dir]] == 'O') return times;

                    if(n_rx == n_bx && n_ry == n_by) {
                        switch (dir) {
                            case 0 -> {
                                if (rx < bx) n_rx--;
                                else n_bx--;
                            }
                            case 1 -> {
                                if(ry < by) n_ry--;
                                else n_by--;
                            }
                            case 2 -> {
                                if (rx > bx) n_rx++;
                                else n_bx++;
                            }
                            case 3 -> {
                                if(ry > by) n_ry++;
                                else n_by++;
                            }
                        }
                    }
                    if(vis[n_rx][n_ry][n_bx][n_by]) continue;
                    vis[n_rx][n_ry][n_bx][n_by] = true;
                    q.add(new Tuple(new Pair(n_rx, n_ry), new Pair(n_bx, n_by)));
                }
            }
        }
        return -1;
    }

    static class Tuple {
        Pair red, blue;
        public Tuple(Pair red, Pair blue) {
            this.red = red;
            this.blue = blue;
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
