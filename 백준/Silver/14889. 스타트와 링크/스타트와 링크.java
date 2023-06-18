import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] board;
    static int[][] teams;
    static boolean[] vis;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        vis = new boolean[N];
        board = new int[N][N];
        teams = new int[2][N/2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        go(0, 0,true);
        System.out.println(ans);
    }

    static void go(int level, int start, boolean teamA) {
        if (level == N / 2) {
            if(teamA) go(0, 0,false);
            else ans = Math.min(ans, getDiff());
            if(ans == 0) {
                System.out.println(0);
                System.exit(0);
            }
            return;
        }
        for (int i = start; i < N; i++) {
            if(vis[i]) continue;
            teams[teamA ? 0 : 1][level] = i;
            vis[i] = true;
            go(level+1, i+1, teamA);
            vis[i] = false;
        }
    }

    private static int getDiff() {
        int[] result = new int[2];
        for(int k = 0; k < 2; k++) {
            for (int i : teams[k]) {
                for (int j : teams[k]) result[k] += board[i][j];
            }
        }
        return Math.abs(result[0] - result[1]);
    }
}
