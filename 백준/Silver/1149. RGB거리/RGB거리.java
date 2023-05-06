import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[][] A = new int[t+1][4];
        int[][] D = new int[t+1][4];

        for(int i = 1; i <= t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 3; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }
        br.close();

        for(int i = 1; i <= t; i++) {
            D[i][1] = Math.min(D[i-1][2], D[i-1][3]) + A[i][1];
            D[i][2] = Math.min(D[i-1][1], D[i-1][3]) + A[i][2];
            D[i][3] = Math.min(D[i-1][2], D[i-1][1]) + A[i][3];
        }

        bw.write(String.valueOf(Math.min(Math.min(D[t][1],D[t][2]),D[t][3])));
        bw.flush();
        bw.close();
    }
}
