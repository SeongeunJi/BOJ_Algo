import java.io.*;
import java.util.*;
public class Main {
    static int[][] board;
    static Map<Integer, Integer> lhm = new LinkedHashMap<>();
    static {
        lhm.put(-1, 0);
        lhm.put(0, 0);
        lhm.put(1, 0);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        recursion(0,0, n);
        lhm.values().forEach(System.out::println);
    }

    private static void recursion(int x, int y, int n) {
        if (check(x,y,n)) {
            lhm.put(board[x][y], lhm.get(board[x][y])+1);
            return;
        }
        n /= 3;
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++)
                recursion(x + i * n, y + j * n, n);
        }
    }

    private static boolean check(int x, int y, int n) {
        for (int i = x; i < x+n; i++) {
            for (int j = y; j < y+n; j++) {
                if (board[x][y] != board[i][j])
                    return false;
            }
        }
        return true;
    }
}