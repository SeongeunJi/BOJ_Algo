
import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n+1];
        int[] dp = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++)
            A[i] = Integer.parseInt(st.nextToken());

        int max = A[1];
        for (int i = 1; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                if(A[i] > A[j] && dp[j] >= dp[i])
                    dp[i] = dp[j];
            }
            dp[i] += A[i];
            max = Math.max(max,dp[i]);
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
