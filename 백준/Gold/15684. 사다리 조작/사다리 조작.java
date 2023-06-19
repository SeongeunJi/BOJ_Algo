import java.io.*;
import java.util.*;
public class Main {
    static final int RIGHT = 1, LEFT = 2;
    static int N, M, H, installed;
     static int[][] ladderArr;
     static boolean finish;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladderArr = new int[H+1][N+1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladderArr[x][y] = RIGHT;
            ladderArr[x][y+1] = LEFT;
        }
        for(int i = 0; i <= 3; i++) {
            installed = i;
            go(0, 1);
            if(finish) break;
        }
        System.out.println(finish ? installed : -1);
    }
    static void go(int level, int start) {
        if(finish) return;
        if(installed == level) {
            if(check()) finish = true;
            return;
        }
        for(int i = start; i <= H; i++) {
            for(int j = 1; j < N; j++) {
                if(ladderArr[i][j] == 0 && ladderArr[i][j+1] == 0) {
                    ladderArr[i][j] = RIGHT;
                    ladderArr[i][j+1] = LEFT;
                    go(level+1, i);
                    ladderArr[i][j] = ladderArr[i][j+1] = 0;
                }
            }
        }
    }

    static boolean check() {
        for(int i = 1; i <= N; i++) {
            int x = i, y = 1;
            for(int j = 0; j < H; j++) {
                switch (ladderArr[y][x]) {
                    case RIGHT -> x++;
                    case LEFT -> x--;
                }
                y++;
            }
            if(x != i) return false;
        }
        return true;
    }
}
