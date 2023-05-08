
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[31][3];

        dp[2][1] = 2; dp[2][2] = 1;
        dp[2][0] = 3;

        for(int i = 4; i <= N; i++) {
            dp[i][1] = (dp[2][1] * dp[i-2][0]) + (dp[2][1] * dp[i-2][2])
                        + dp[i-4][1];
            dp[i][2] = dp[2][2] * dp[i-2][0];
            dp[i][0] = dp[i][1] + dp[i][2];
        }

        bw.write(String.valueOf(dp[N][0]));
        bw.flush();
        bw.close();
        br.close();
    }
}