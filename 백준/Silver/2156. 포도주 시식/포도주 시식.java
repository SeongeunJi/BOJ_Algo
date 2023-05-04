import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N+1];
        int[][] dp = new int[N + 1][3];

        for (int i = 1; i <= N; i++)
            A[i] = Integer.parseInt(br.readLine());

        br.close();
        dp[1][1] = A[1];

        for(int i = 2; i <= N; i++){
            dp[i][2] = dp[i-1][1] + A[i];
            dp[i][1] = dp[i-1][0] + A[i];
            dp[i][0] = Math.max(Math.max(dp[i-1][0],dp[i-1][1]),dp[i-1][2]);
        }

        bw.write(String.valueOf(Math.max(Math.max(dp[N][0],dp[N][1]),dp[N][2])));
        bw.flush();
        bw.close();
    }
}
