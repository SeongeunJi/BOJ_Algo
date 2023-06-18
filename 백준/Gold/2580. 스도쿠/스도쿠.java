import java.io.*;
import java.util.*;
public class Main {
    static int[][] board = new int[9][9];
    static int[] col_vis = new int[9];
    static int[] row_vis = new int[9];
    static int[] area_vis = new int[9];
    static StringBuilder sb = new StringBuilder();
    static int blanks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    blanks++;
                    continue;
                }
                markVis(i, j, board[i][j], true);
            }
        }
        go(0, 0);
    }

    static void go(int level, int start) {
        if (level == blanks) {
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
        int bitmask = 1 << num;
        return (col_vis[nx] & bitmask) != 0 ||
                (row_vis[ny] & bitmask) != 0 ||
                (area_vis[nx / 3 * 3 + ny / 3] & bitmask) != 0;
    }

    private static void markVis(int nx, int ny, int num, boolean status) {
        int bitmask = 1 << num;
        if (status) {
            col_vis[nx] |= bitmask;
            row_vis[ny] |= bitmask;
            area_vis[nx / 3 * 3 + ny / 3] |= bitmask;
        } else {
            col_vis[nx] &= ~bitmask;
            row_vis[ny] &= ~bitmask;
            area_vis[nx / 3 * 3 + ny / 3] &= ~bitmask;
        }
    }

    private static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                sb.append(board[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
        System.exit(0);
    }
}