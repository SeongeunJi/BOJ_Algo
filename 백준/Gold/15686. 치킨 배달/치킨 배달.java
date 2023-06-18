import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int[][] board;
    static int[] arr;
    static List<Pair> chickens = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        arr = new int[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        combinatorics(0, 0);
        System.out.println(ans);
    }

    static void combinatorics(int level, int start) {
        if(level == M) {
            getCityOfChickenDistance();
            chickens.clear();
            return;
        }
        for (int i = start; i < N * N; i++) {
            int nx = i/N, ny = i%N;
            if(board[nx][ny] != 2) continue;
            arr[level] = i;
            combinatorics(level+1, i+1);
        }
    }

    static void getCityOfChickenDistance() {
        for(int i : arr) chickens.add(new Pair(i / N, i % N));
        int cd = 0;
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] == 1) {
                    int dist = Integer.MAX_VALUE;
                    for (Pair chicken : chickens)
                        dist = Math.min(dist, getChickenDistance(i, j, chicken.x, chicken.y));
                    cd += dist;
                    if(cd >= ans) return;
                }
            }
        }
        ans = Math.min(ans, cd);
    }

    static int getChickenDistance(int cx, int cy, int tx, int ty) {
        return Math.abs(cx - tx) + Math.abs(cy - ty);
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}