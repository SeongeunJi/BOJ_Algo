import java.io.*;
import java.util.*;
public class Main {
    static int N, M, R;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rotate(0);
    }
    public static void rotate(int times) {
        int count = Math.min(N, M) / 2;
        while(times != R) {
            for(int j=0; j < count; j++) {
                int temp = arr[j][j];

                for (int k = j + 1; k < M - j; k++)
                    arr[j][k - 1] = arr[j][k];
                
                for (int k = j + 1; k < N - j; k++)
                    arr[k - 1][M - 1 - j] = arr[k][M - 1 - j];

                for (int k = M - 2 - j; k >= j; k--) 
                    arr[N - 1 - j][k + 1] = arr[N - 1 - j][k];

                for(int k=N-2-j; k>=j; k--)
                    arr[k+1][j] = arr[k][j];

                arr[j+1][j] = temp;
            }
            times++;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) sb.append(arr[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}