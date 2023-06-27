import java.io.*;
import java.util.*;
public class Main {
    static int N, L;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) 
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            if (check(i, true)) cnt++;
            if (check(i, false)) cnt++;
        }
        System.out.println(cnt);
    }
    static boolean check(int start, boolean isCol) {
        boolean[] vis = new boolean[N];
        if (isCol) {
            int tmp = board[start][0];
            for(int i = 1; i < N; i++) {
                if(tmp != board[start][i]) {
                    if(Math.abs(tmp - board[start][i]) != 1) return false;
                    if(tmp > board[start][i]) {
                        for (int j = i; j < i + L; j++) {
                            if (j >= N || board[start][j] != tmp - 1 || vis[j]) return false;
                            vis[j] = true;
                        }
                        i += L - 1;
                        tmp--;
                    } else {
                        for (int j = i - 1; j >= i - L; j--) {
                            if (j < 0 || board[start][j] != tmp || vis[j]) return false;
                            vis[j] = true;
                        }
                        tmp++;
                    }
                }
            }
        } else {
            int tmp = board[0][start];
            for(int i = 1; i < N; i++) {
                if(tmp != board[i][start]) {
                    if(Math.abs(tmp - board[i][start]) != 1) return false;
                    if(tmp > board[i][start]) {
                        for (int j = i; j < i + L; j++) {
                            if (j >= N || board[j][start] != tmp - 1 || vis[j]) return false;
                            vis[j] = true;
                        }
                        i += L - 1;
                        tmp--;
                    } else {
                        for (int j = i - 1; j >= i - L; j--) {
                            if (j < 0 || board[j][start] != tmp || vis[j]) return false;
                            vis[j] = true;
                        }
                        tmp++;
                    }
                }
            }
        }
        return true;
    }
}