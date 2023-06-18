import java.io.*;
import java.util.*;
public class Main {
    static int[][] board = new int[9][9];
    static boolean[][] col_vis = new boolean[9][10];
    static boolean[][] row_vis = new boolean[9][10];
    static boolean[][] area_vis = new boolean[9][10];
    static StringBuilder sb = new StringBuilder();
    static int blanks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0) {
                    blanks++;
                    continue;
                }
                markVis(i, j, board[i][j], true);
            }
        }
        go(0, 0);
    }


    static void go(int level, int start) {
        if(level == blanks) {
            printBoard();
            return;
        }
        for (int i = start; i < 81; i++) {
            int nx = i / 9, ny = i % 9;
            if (board[nx][ny] != 0) continue;
            for (int num = 1; num <= 9; num++) {
                if (isVis(nx, ny, num)) continue;
                markVis(nx, ny, num, true);
                board[nx][ny] = num;
                go(level + 1, i + 1);
                markVis(nx, ny, num, false);
                board[nx][ny] = 0;
            }
            return;
        }
    }
    private static boolean isVis(int nx, int ny, int num) {
        return col_vis[nx][num] || row_vis[ny][num] || area_vis[nx / 3 * 3 + ny / 3][num];
    }
    private static void markVis(int nx, int ny, int num, boolean status) {
        col_vis[nx][num] = status;
        row_vis[ny][num] = status;
        area_vis[nx/3*3 + ny/3][num] = status;
    }
    private static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++)
                sb.append(board[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
        System.exit(0);
    }
}