import java.io.*;
import java.util.*;
public class Main {
    static int[][][] tetro = {
        // 청록색 블록
        {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
        {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
        // 노랑색 블록
        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
        // 주황색 블록
        {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
        {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
        {{1, 0}, {1, 1}, {1, 2}, {0, 2}},
        {{1, 0}, {1, 1}, {1, 2}, {0, 0}},

        {{0, 1}, {1, 1}, {2, 1}, {0, 0}},
        {{0, 1}, {1, 1}, {2, 1}, {2, 0}},
        {{0, 0}, {1, 0}, {2, 0}, {0, 1}},
        {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
        // 초록색 블록
        {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
        {{1, 0}, {1, 1}, {0, 1}, {0, 2}},
        {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
        {{0, 1}, {1, 1}, {1, 0}, {2, 0}},
        // 자주색 블록
        {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
        {{1, 0}, {1, 1}, {1, 2}, {0, 1}},
        {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
        {{0, 1}, {1, 1}, {2, 1}, {1, 0}}};

    static int N, M;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < 19; k++) {
                    int x = i, y = j;
                    int tmp = 0;
                    for(int f = 0; f < 4; f++) {
                        int nx = x + tetro[k][f][0];
                        int ny = y + tetro[k][f][1];
                        if(nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                        tmp += board[nx][ny];
                    }
                    ans = Math.max(ans, tmp);
                }
            }
        }
        System.out.println(ans);
    }
}