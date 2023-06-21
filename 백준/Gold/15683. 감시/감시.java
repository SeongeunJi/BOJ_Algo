import java.util.*;
import java.io.*;
public class Main {
    static int[][] dirArr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // clockwise
    static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    static int[] arr;
    static int x, y;
    static int[][] board;
    static List<Integer> cctvList = new ArrayList<>();
    static int blindSpots, cctvCnt, ans = Integer.MAX_VALUE;
    static Queue<Pair> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        board = new int[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < y; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) blindSpots++;
                else if(board[i][j] != 6) {
                    cctvList.add(i * y + j);
                    cctvCnt++;
                }
            }
        }
        arr = new int[cctvCnt];
        go(0, 0);
        System.out.println(ans);
    }
    // cctv 의 보는 방향은 4가지. 각 방향에 따라서, 탐색 범위를 정해야 한다.
    // 7이상을 감시중인 것으로 표시한다.
    static void go(int level, int start) {
        if (start == cctvCnt) {
            ans = Math.min(ans, blindSpots);
            return;
        }
        int cctv = cctvList.get(start);
        int cx = cctv / y, cy = cctv % y;
        for(int dir = 0; dir < 4; dir++) {
            monitor(cx ,cy, dir, 7);
            arr[level] = dir;
            go(level+1, start+1);
            monitor(cx, cy, dir,0);
        }
    }
    static void monitor(int cx, int cy, int dir, int state) {
        switch (board[cx][cy]) {
            case 1 -> bfs(cx, cy, state, dirArr[dir]);
            case 2 -> {
                switch (dir) {
                    case NORTH, SOUTH -> bfs(cx, cy, state, dirArr[NORTH], dirArr[SOUTH]);
                    case EAST, WEST -> bfs(cx, cy, state, dirArr[EAST], dirArr[WEST]);
                }
            }
            case 3 -> bfs(cx, cy, state, dirArr[dir], dirArr[(dir+1)%4]);
            case 4 -> {
                switch (dir) {
                    case NORTH -> bfs(cx, cy, state, dirArr[EAST], dirArr[NORTH], dirArr[WEST]);
                    case EAST -> bfs(cx, cy, state, dirArr[NORTH], dirArr[EAST], dirArr[SOUTH]);
                    case SOUTH -> bfs(cx, cy, state, dirArr[EAST], dirArr[SOUTH], dirArr[WEST]);
                    case WEST -> bfs(cx, cy, state, dirArr[SOUTH], dirArr[WEST], dirArr[NORTH]);
                }
            }
            case 5 -> bfs(cx, cy, state, dirArr);
        }
    }

    static void bfs(int cx, int cy, int state,  int[]... dirArr) {
        for(int[] dir : dirArr) {
            q.add(new Pair(cx, cy));
            while (!q.isEmpty()) {
                Pair cur = q.remove();
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];
                if(nx < 0 || ny < 0 || nx >= x || ny >= y) break;
                if(board[nx][ny] == 6) break;
                if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {
                    q.add(new Pair(nx, ny));
                    continue;
                }
                if(state == 7) {
                    if(board[nx][ny] == 0) {
                        blindSpots--;
                        board[nx][ny] = state;
                    }
                    else board[nx][ny]++;
                }
                else {
                    if(board[nx][ny] == 7) {
                        blindSpots++;
                        board[nx][ny] = state;
                    }
                    else board[nx][ny]--;
                }
                q.add(new Pair(nx, ny));
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