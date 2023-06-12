import java.io.*;
public class Main {
    static char[][] board;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < n; j++)
                board[i][j] = str.charAt(j);
        }
        recursion(0, 0, n);
        System.out.println(sb);
    }

    private static void recursion(int x, int y, int n) {
        if (check(x, y, n)) {
            sb.append(board[x][y]);
            return;
        }
        n /= 2;
        sb.append("(");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                recursion(x + i * n, y + j * n, n);
            }
        }
        sb.append(")");
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