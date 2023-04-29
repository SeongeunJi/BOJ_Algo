import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] numArr = new int[n+1];
        int[] dp = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        br.close();

        for (int i = 1; i <= n; i++)
            numArr[i] = Integer.parseInt(st.nextToken());

        int max = numArr[1];
        for(int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + numArr[i], numArr[i]);
            max = Math.max(dp[i], max);
        }
        
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
