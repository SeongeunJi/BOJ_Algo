import java.io.*;
import java.util.*;
public class Main {
    static int[][] board;
    static int[] result = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        recursion(0,0,n);
        Arrays.stream(result).forEach(System.out::println);
    }

    private static void recursion(int x, int y, int n) {
        if (check(x, y, n)) {
            result[board[x][y]]++;
            return;
        }
        n /= 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                recursion(x + i * n, y + j * n, n);
        }
    }
    private static boolean check(int x, int y, int n) {
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if(board[x][y] != board[i][j]) return false;
            }
        }
        return true;
    }
}