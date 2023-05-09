
import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] A = new int[N+1][4];
        int[][] dp = null;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= 3; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }

        int dummy = 10000001;
        int result = dummy;

        for (int i = 1; i <= 3; i++) { // 처음 칠한 집의 색상
            dp = new int[N+1][4];
            for (int j = 1; j <= N; j++) { // 각 집의 idx
                if (j == 1) { // dp[1][k]를 제외한 색상엔 dummy 값을 넣음 {
                    for (int k = 1; k <= 3; k++) { // 색상
                        if (i == k)
                            dp[1][k] = A[1][k];
                        else
                            dp[1][k] = dummy;
                        }
                    }
                else {
                    dp[j][1] = Math.min(dp[j-1][2], dp[j-1][3]) + A[j][1];
                    dp[j][2] = Math.min(dp[j-1][1], dp[j-1][3]) + A[j][2];
                    dp[j][3] = Math.min(dp[j-1][1], dp[j-1][2]) + A[j][3];
                }
            } // end of idx for

            for(int j = 1; j <= 3; j++) {
                if(i == j) continue;
                result = Math.min(dp[N][j], result);
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
