
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int mod = 9901;
        long[][] dp = new long[N+1][3];

        dp[0][2] = 1;
        dp[1][1] = 1;
        dp[1][2] = 2;
        dp[1][0] = 1;

        long tmp = 0;
        for(int i = 2; i <= N; i++) {
            tmp = (tmp + dp[i-2][2]) % mod;
            dp[i][1] = (dp[i-1][0] + tmp) % mod;
            dp[i][0] = dp[i][1];
            dp[i][2] = (dp[i][1] * 2) % mod;
        }

        long max = 0;
        for(int i = 0; i <= N; i++)
            max = (dp[i][2] + max) % mod;

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();

    }
}
