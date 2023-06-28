import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static boolean[][] board = new boolean[105][105];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        while(N --> 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken());
            board[y][x] = true;
            dragonCurve(x, y, d, g);
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1]) cnt++;
            }
        }
        System.out.println(cnt);
    }

    static void dragonCurve(int x, int y, int d, int g) {
        int[] tmp = new int[1<<g];
        tmp[0] = d;
        int idx = 0;
        for(int i = 1; i <= g; i++) {
            for(int j = idx; j >= 0; j--)
                tmp[++idx] = (tmp[j]+1) & 3;
        }
        for(int i = 0; i < 1<<g; i++) {
            switch (tmp[i]) {
                case 0 -> x++;
                case 1 -> y--;
                case 2 -> x--;
                case 3 -> y++;
            }
            board[y][x] = true;
        }
    }
}