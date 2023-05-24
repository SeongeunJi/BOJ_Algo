import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

        int tc = sc.nextInt();
        while(tc --> 0) {
            int len = sc.nextInt();
            Queue<Pair<Integer>> q = new ArrayDeque<>();
            int[][] board = new int[len][len];
            for(int[] tmp : board)
                Arrays.fill(tmp, -1);

            int sx = sc.nextInt(), sy = sc.nextInt();
            int ax = sc.nextInt(), ay = sc.nextInt();

            board[sx][sy] = 0;
            q.add(new Pair<>(sx, sy));
            while (board[ax][ay] == -1) {
                Pair<Integer> cur = q.remove();
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= len || ny >= len) continue;
                    if(board[nx][ny] >= 0) continue;
                    q.add(new Pair<>(nx, ny));
                    board[nx][ny] = board[cur.x][cur.y] + 1;
                }
            }
            bw.write(board[ax][ay] + "\n");
        }
        bw.flush();
        bw.close();
    }
    static class Pair<T> {
        T x, y;
        public Pair(T x, T y) {
            this.x = x;
            this.y = y;
        }
    }
}