
import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st;

            int n = Integer.parseInt(br.readLine());
            int[][] A = new int[n + 1][];
            int[][] dp = new int[n + 1][];

            for (int i = 0; i <= n; i++) { // 배열 생성
                A[i] = new int[i + 1];
                dp[i] = new int[i + 1];
            }

            for (int i = 1; i <= n; i++) { //  초기화
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1; j <= i; j++)
                    A[i][j] = Integer.parseInt(st.nextToken());
            }

            dp[1][1] = A[1][1];
            int max = dp[1][1];
            for (int i = 2; i <= n; i++) {
                int endLen = dp[i].length - 1;
                for (int j = 1; j < dp[i].length; j++) {
                    if (endLen <= j) dp[i][j] = dp[i - 1][j - 1];
                    else
                        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                    dp[i][j] += A[i][j];
                    max = Math.max(max, dp[i][j]);
                }
            }

            bw.write(String.valueOf(max));
            bw.flush();
        }
    }
}
