
import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()); // 숫자
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[201][201];

        for(int i = 0; i <= n; i++) // 모든 수 1개의 항 초기화
            dp[i][1] = 1;

        for(int i = 1; i <= k; i++) {
            for(int j = 0; j <= n; j++) {
                for(int l = 0; l <= j; l++)
                    dp[j][i] = (dp[j][i] + dp[j-l][i-1]) % 1000000000;
            }
        }

        bw.write(String.valueOf(dp[n][k]));
        bw.flush();
        bw.close();
        br.close();
    }
}
