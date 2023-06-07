import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        bw.write(String.valueOf(recursion(n,r,c)));
        bw.flush();
        bw.close();
    }
    static int recursion(int n, int r, int c) {
        if(n == 0) return 0;
        int half = 1<<(n-1);
        if(r < half && c < half) return recursion(n-1, r, c);
        if(r < half && c >= half) return half*half + recursion(n-1,r,c-half);
        if(r >= half && c < half) return 2*half*half + recursion(n-1,r-half,c);
        return 3*half*half + recursion(n-1,r-half,c-half);
    }
}
