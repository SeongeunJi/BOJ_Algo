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
        for(int i = 1; i <= n; i++)
            A[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
            for(int j = 1; j < i; j++) {
                if(A[i] > A[j] && dp[i][1] <= dp[j][1])
                    dp[i][1] = dp[j][1] + 1;
            }
        }

        for(int i = n; i >= 1; i--) {
            dp[i][2] = 1;
            for(int j = n; j > i; j--)
                if(A[i] > A[j] && dp[i][2] <= dp[j][2])
                    dp[i][2] = dp[j][2] + 1;
        }

        int max = 0;
        for(int i = 1; i <= n; i++)
            max = Math.max(dp[i][1] + dp[i][2] - 1, max);

        bw.write(String.valueOf(max));
        bw.flush();
    }
}