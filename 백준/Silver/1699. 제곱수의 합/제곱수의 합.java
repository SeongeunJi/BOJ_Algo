import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        br.close();

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 1; j * j <= i; j++) {
                int i1 = dp[i - j * j] + 1;
                if (dp[i] > i1) dp[i] = i1;
            }
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
        bw.close();
    }
}
