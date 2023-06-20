import java.io.*;
import java.util.*;
public class Main {
    static int[][] board = new int[10][10];
    static int[] paperArr = {0, 5, 5, 5, 5, 5};
    static int ans = Integer.MAX_VALUE;
    static boolean finish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        go(0,  0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
    static void go(int start, int cnt) {
        int x = start / 10, y = start % 10;
        if (start > 99) {
            finish = true;
            ans = Math.min(ans, cnt);
            return;
        }
        if(ans <= cnt) return;
        if(board[x][y] == 1) {
            for(int i = 5; i >= 1; i--) {
                if (paperArr[i] > 0 && able(x, y, i)) {
                    markVis(x, y, i, 0);
                    paperArr[i]--;
                    go(start+1, cnt+1);
                    markVis(x, y, i, 1);
                    paperArr[i]++;
                }
            }
        } else {
            go(start+1, cnt);
        }
    }
    static void markVis(int x, int y, int len, int status) {
        for (int i = x; i < x + len; i++) {
            for(int j = y; j < y + len; j++) {
                board[i][j] = status;
            }
        }
    }
    static boolean able(int x, int y, int len) {
        for (int i = x; i < x + len; i++) {
            for(int j = y; j < y + len; j++) {
                if(i < 0 || j < 0 || i >= 10 || j >= 10) return false;
                if(board[i][j] != 1) return false;
            }
        }
        return true;
    }
}
