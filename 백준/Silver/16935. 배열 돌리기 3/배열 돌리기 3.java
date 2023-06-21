import java.io.*;
import java.util.*;
public class Main {
    static int N, M, R;
    static int[][] board;
    static int[] op;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        op = new int[R];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) op[i] = Integer.parseInt(st.nextToken());
        Arrays.stream(op).forEach(e -> {
            switch (e) {
                case 1 -> upside_down();
                case 2 -> switch_left_to_right();
                case 3 -> rotate_clockwise_90d();
                case 4 -> rotate_counterclockwise_90d();
                case 5 -> move1();
                case 6 -> move2();
            }
        });
        print();
    }


    private static void upside_down() {
        for(int i = 0; i < N/2; i++) {
            int[] tmp1 = Arrays.copyOf(board[i], M);
            int[] tmp2 = Arrays.copyOf(board[N-i-1], M);
            board[i] = tmp2;
            board[N-i-1] = tmp1;
        }
    }
    private static void switch_left_to_right() {
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M/2; j++) {
                int tmp1 = board[i][j];
                int tmp2 = board[i][M-j-1];
                board[i][M-j-1] = tmp1;
                board[i][j] = tmp2;
            }
        }
    }
    private static void rotate_clockwise_90d() {
        int temp = N;
        N = M;
        M = temp;
        int[][] newBoard = new int[N][M];
        for(int i = 0; i < temp; i++) {
            int[] tmp = Arrays.copyOf(board[i], N);
            for(int j = 0; j < N; j++)
                newBoard[j][M-1-i] = tmp[j];
        }
        board = newBoard;
    }
    private static void rotate_counterclockwise_90d() {
        int temp = N;
        N = M;
        M = temp;
        int[][] newBoard = new int[N][M];
        int[] tmp = new int[M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < temp; j++)
                tmp[j] = board[j][N-1-i];
            newBoard[i] = tmp.clone();
        }
        board = newBoard;
    }

    public static void move1(){

        int[][] newBoard = new int[N][M];
        for(int i=0; i<N/2; i++){
            if (M / 2 >= 0) System.arraycopy(board[i], 0, newBoard[i], M / 2, M / 2);
        }

        for(int i=0; i<N/2; i++){
            if (M - M / 2 >= 0) System.arraycopy(board[i], M / 2, newBoard[N / 2 + i], M / 2, M - M / 2);
        }

        for(int i=N/2; i<N; i++){
            if (M - M / 2 >= 0) System.arraycopy(board[i], M / 2, newBoard[i], 0, M - M / 2);
        }

        for(int i=N/2; i<N; i++){
            if (M / 2 >= 0) System.arraycopy(board[i], 0, newBoard[i - N / 2], 0, M / 2);
        }
        board = newBoard;

    }
    public static void move2(){
        int[][] newBoard = new int[N][M];
        for(int i=0; i<N/2; i++){
            if (M / 2 >= 0) System.arraycopy(board[i], 0, newBoard[N / 2 + i], 0, M / 2);
        }

        for(int i=N/2; i<N; i++){
            if (M / 2 >= 0) System.arraycopy(board[i], 0, newBoard[i], M / 2, M / 2);
        }
        for(int i=N/2; i<N; i++){
            if (M - M / 2 >= 0) System.arraycopy(board[i], M / 2, newBoard[i - N / 2], M / 2, M - M / 2);
        }
        for(int i=0; i<N/2; i++){
            if (M - M / 2 >= 0) System.arraycopy(board[i], M / 2, newBoard[i], 0, M - M / 2);
        }
        board = newBoard;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                sb.append(board[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}