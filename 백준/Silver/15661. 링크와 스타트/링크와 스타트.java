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
        teams = new int[2][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        getTeamA(0, 0);
        System.out.println(ans);
    }

    static void getTeamA(int level, int start) {
        if(level >= 1 && level < N) getTeamB(level);
        for (int i = start; i < N; i++) {
            teams[0][level] = i;
            vis[i] = true;
            getTeamA(level+1, i+1);
            vis[i] = false;
        }
    }

    static void getTeamB(int countA) {
        int countB = 0;
        for (int i = 0; i < N; i++) {
            if(!vis[i]) teams[1][countB++] = i;
        }
        int val = getDiff(countA, countB);
        ans = Math.min(ans, val);
        if(ans == 0) {
            System.out.println(0);
            System.exit(0);
        }
    }

    private static int getDiff(int countA, int countB) {
        int[] result = new int[2];
        for(int k = 0; k < 2; k++) {
            if(k == 0) {
                for(int i = 0; i < countA; i++) {
                    for(int j = 0; j < countA; j++)
                        result[0] += board[teams[k][i]][teams[k][j]];
                }
            }
            else {
                for(int i = 0; i < countB; i++) {
                    for(int j = 0; j < countB; j++)
                        result[1] += board[teams[k][i]][teams[k][j]];
                }
            }
        }
        return Math.abs(result[0] - result[1]);
    }
}