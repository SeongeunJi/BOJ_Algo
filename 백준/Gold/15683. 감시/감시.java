import java.io.*;
import java.util.*;
public class Main {
    static int X, Y, blindSpots;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<Pair> cctvList = new ArrayList<>();
    static int[][] board1;
    static int[][] board2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        board1 = new int[X][Y];
        board2 = new int[X][Y];
        for (int i = 0; i < X; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < Y; j++) {
                board1[i][j] = Integer.parseInt(st.nextToken());
                if(board1[i][j] >= 1 && board1[i][j] <= 5)
                    cctvList.add(new Pair(i, j));
                else if(board1[i][j] == 0) blindSpots++;
            }
        }
        go();
    }

    static void go() {
        for(int temp = 0; temp < (1 << 2 * cctvList.size()); temp++) {
            for (int i = 0; i < X; i++)
                System.arraycopy(board1[i], 0, board2[i], 0, Y);
            int brute = temp;
            for (Pair pair : cctvList) {
                int dir = brute % 4;
                brute /= 4;
                monitor(pair.x, pair.y, dir);
            }
        }
        System.out.println(blindSpots);
    }

    static void monitor(int x, int y, int dir) {
        switch (board1[x][y]) {
            case 1 ->
                upd(x, y, dir);
            case 2 -> {
                upd(x, y, dir);
                upd(x, y, dir+2);
            }
            case 3 -> {
                upd(x, y, dir);
                upd(x, y, dir+1);
            }
            case 4 -> {
                upd(x, y, dir);
                upd(x, y, dir+1);
                upd(x, y, dir+2);
            }
            case 5 -> {
                upd(x, y, dir);
                upd(x, y, dir+1);
                upd(x, y, dir+2);
                upd(x, y, dir+3);
            }
        }
        int val = 0;
        for (int i = 0; i < X; i++) {
            for(int j = 0; j < Y; j++) {
                if(board2[i][j] == 0) val++;
            }
        }
        blindSpots = Math.min(blindSpots, val);
    }
    static boolean OOB(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= X || ny >= Y;
    }
    static void upd(int x, int y, int dir) {
        dir %= 4;
        while(true) {
            x += dx[dir];
            y += dy[dir];
            if(OOB(x, y) || board2[x][y] == 6) return;
            if(board2[x][y] != 0) continue;
            board2[x][y] = 7;
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