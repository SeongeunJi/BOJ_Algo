import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n+1];
        int[][] dp = new int[n+1][3];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++)
            A[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++)
            dp[i][1] = Math.max(dp[i-1][1] + A[i], A[i]);

        dp[n][2] = A[n];
        int max = dp[n][1];
        for(int i = n-1; i >= 1; i--) {
            dp[i][2] = Math.max(dp[i+1][2] + A[i], A[i]);
            max = Math.max(max, dp[i][1]);
        }

        for(int i = 1; i <= n; i++) {
            if(i == n) continue;
            max = Math.max(dp[i-1][1] + dp[i+1][2], max);
        }
        
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
