import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = 0;
        while(st.hasMoreTokens()) ans += Math.pow(Integer.parseInt(st.nextToken()), 2);
        System.out.println(ans % 10);
    }
}