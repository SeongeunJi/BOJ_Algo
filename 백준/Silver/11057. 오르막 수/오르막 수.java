
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        long[][] D = new long[N+2][11];
        long mod = 10007L;


        for(int i = 0; i < 10; i++)
           D[1][i] = 1;


        for(int i = 2; i < D.length; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = j; k < 10; k++)
                    D[i][j] = (D[i-1][k] + D[i][j]) % mod;
            }
        }

        bw.write(String.valueOf(D[N+1][0]));
        bw.flush();
        bw.close();
    }
}