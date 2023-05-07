import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n+1];
        int[][] dp = new int[n+1][3];


        // 입력값 초기화
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++)
            A[i] = Integer.parseInt(st.nextToken());

        // i번째로 끝나는 최대 연속합 + 최댓값 한번에 구하기
        int max = A[1];
        for(int i = 1; i <= n; i++) {
            dp[i][1] = Math.max(A[i], A[i] + dp[i - 1][1]);
            max = Math.max(dp[i][1], max);
        }

        // i번째로 시작하는 최대 연속합
        dp[n][2] = A[n];
        for(int i = n-1; i >= 1; i--)
            dp[i][2] = Math.max(A[i], A[i] + dp[i+1][2]);

        // 제거한 모든 경우 vs dp[i][1]
        for(int i = 1; i < n; i++) 
            max = Math.max(max, dp[i - 1][1] + dp[i + 1][2]);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
