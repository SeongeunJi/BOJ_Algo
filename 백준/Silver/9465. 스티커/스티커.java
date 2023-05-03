import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] A = new int[3][N+1];
            int[][] dp = new int[3][N+1];

            for(int i = 1; i <= 2; i++) { // A 초기화 코드
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j <= N; j++)
                    A[i][j] = Integer.parseInt(st.nextToken());
            }
            
            for (int i = 1; i <= N; i++) { // DP 계산 코드
                dp[0][i] = Math.max(Math.max(dp[0][i-1],dp[1][i-1]),dp[2][i-1]);
                dp[1][i] = Math.max(dp[2][i-1], dp[0][i-1]) + A[1][i];
                dp[2][i] = Math.max(dp[1][i-1], dp[0][i-1]) + A[2][i];
            }

            bw.write(Math.max(Math.max(dp[0][N],dp[1][N]),dp[2][N]) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

