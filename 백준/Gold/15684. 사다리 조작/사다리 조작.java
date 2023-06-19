import java.io.*;
import java.util.*;
public class Main {
    static final int RIGHT = 1, LEFT = 2;
    static int[][] ladders;
    static int N, M, H, installed;
    static boolean finish;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladders = new int[H][N];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            ladders[y][x] = RIGHT;
            ladders[y][x+1] = LEFT;
        }
        for(int i = 0; i <= 3; i++) {
            installed = i;
            go(0, 0);
            if(finish) break;
        }
        System.out.println(finish ? installed : -1);
    }
    static void go(int level, int start) {
        if(finish) return;
        if(level == installed) {
            if(check()) finish = true;
            return;
        }
        for(int i = start; i < H; i++) {
            for(int j = 0; j < N-1; j++) {
                if(ladders[i][j] != 0 || ladders[i][j+1] != 0) continue;
                ladders[i][j] = RIGHT;
                ladders[i][j+1] = LEFT;
                go(level+1, i);
                ladders[i][j] = ladders[i][j+1] = 0;
            }
        }
    }
    static boolean check() {
        for(int i = 0; i < N; i++) {
            int x = i, y = 0;
            for(int j = 0; j < H; j++) {
                if (ladders[y][x] == RIGHT) x++;
                else if(ladders[y][x] == LEFT) x--;
                y++;
            }
            if(i != x) return false;
        }
        return true;
    }
}