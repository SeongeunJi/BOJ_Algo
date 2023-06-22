import java.io.*;
import java.util.*;
public class Main {
    static int[][] board1;
    static int[][] board2;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board1 = new int[N][N];
        board2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int mx = 0;
        for(int tmp = 0; tmp < 1024; tmp++) {
            for (int i = 0; i < N; i++)
                System.arraycopy(board1[i], 0, board2[i], 0, N);
            int brute = tmp;
            for(int i = 0; i < 5; i++) {
                tilt(brute % 4);
                brute /= 4;
            }
            for (int i = 0; i < N; i++)
                for(int j = 0; j < N; j++)
                    mx = Math.max(mx, board2[i][j]);
        }
        System.out.println(mx);
    }
    static void rotate_clockwise() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++)
            System.arraycopy(board2[i], 0, tmp[i], 0, N);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board2[i][j] = tmp[N - 1 - j][i];
    }
    static void tilt(int dir) {
        while(dir-- >= 1) rotate_clockwise();
        for (int i = 0; i < N; i++) {
            int[] tilted = new int[N];
            int idx = 0;
            for (int j = 0; j < N; j++) {
                if(board2[i][j] == 0) continue;
                if(tilted[idx] == 0)
                    tilted[idx] = board2[i][j];
                else if(tilted[idx] == board2[i][j])
                    tilted[idx++] = board2[i][j]<<1;
                else
                    tilted[++idx] = board2[i][j];
            }
            System.arraycopy(tilted, 0, board2[i], 0, N);
        }
    }
}