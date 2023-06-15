import java.io.*;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        while(tc --> 0) {
            String input = br.readLine();
            int len = input.length();
            int[] ans = new int[len];
            int score = 0;
            for (int i = 0; i < len; i++) {
                if (input.charAt(i) == 'O') ans[i] = ++score;
                else score = 0;
            }
            sb.append(Arrays.stream(ans).sum()).append("\n");
        }
        System.out.println(sb);
    }
}
