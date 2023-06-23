import java.util.*;
import java.io.*;
public class Main {
    static int N, M, X, Y, K;
    static int[][] board;
    static int[] dice = new int[7];
    static int[][] dirArr = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int[][] rollArr = {
            {},
            {0, 4, 2, 1, 6, 5, 3},
            {0, 3, 2, 6, 1, 5, 4},
            {0, 2, 6, 3, 4, 1, 5},
            {0, 5, 1, 3, 4, 6, 2}
    };
    static int[] diceLoc = new int[2];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        diceLoc[0] = X;
        diceLoc[1] = Y;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        while(K --> 0) {
            int dir = Integer.parseInt(st.nextToken());
            if(OOB(dir)) continue;
            rollingDice(dir);
        }
        System.out.println(sb);
    }

    static void rollingDice(int dir) {
        int[] rolled = dice.clone();
        for(int i = 1; i <= 6; i++)
            rolled[i] = dice[rollArr[dir][i]];
        dice = rolled.clone();
        diceLoc[0] += dirArr[dir][0];
        diceLoc[1] += dirArr[dir][1];
        if(board[diceLoc[0]][diceLoc[1]] == 0)
            board[diceLoc[0]][diceLoc[1]] = dice[6];
        else {
            dice[6] = board[diceLoc[0]][diceLoc[1]];
            board[diceLoc[0]][diceLoc[1]] = 0;
        }
        sb.append(dice[1]).append("\n");
    }
    static boolean OOB(int dir) {
        int nx = diceLoc[0] + dirArr[dir][0];
        int ny = diceLoc[1] + dirArr[dir][1];
        return nx < 0 || ny < 0 || nx >= N || ny >= M;
    }
}