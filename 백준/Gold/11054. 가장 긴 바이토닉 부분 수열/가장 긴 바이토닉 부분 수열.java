import java.io.*;
import java.util.StringTokenizer;
 class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[] A = new int[t + 1];
        int[][] dp = new int[t + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= t; i++)
            A[i] = Integer.parseInt(st.nextToken());

        // get LIS
        for (int i = 1; i <= t; i++) {
            for (int j = 1; j < i; j++) {
                if (A[i] > A[j] && dp[i][0] <= dp[j][0])
                    dp[i][0] = dp[j][0] + 1;
            }
        }

        for (int i = t; i >= 1; i--) {
            for (int j = t; j > i; j--) {
                if (A[i] > A[j] && dp[i][1] <= dp[j][1])
                    dp[i][1] = dp[j][1] + 1;
            }
        }

        int result = 0;
        for(int i = 1; i <= t; i++)
            result = Math.max(result, dp[i][1] + dp[i][0] + 1);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}