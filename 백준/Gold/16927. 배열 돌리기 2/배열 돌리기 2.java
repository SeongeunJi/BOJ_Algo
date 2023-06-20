import java.io.*;
import java.util.*;
public class Main {
    static int N, M, R;
    static int[][] arr;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
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
        int n=N, m=M;
        for(int i=0; i<Math.min(N, M)/2; i++) {
            rotate(i, 2*n + 2*m -4);
            n-=2;
            m-=2;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) sb.append(arr[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void rotate(int times, int len) {
        int count = R%len;
        for (int j = 0; j < count; j++) {
            int y = times;
            int x = times;
            int idx = 0;
            int pre = arr[times][times];
            int tmp;

            while (idx < 4) {
                int yy = y + dy[idx];
                int xx = x + dx[idx];
                if (yy >= times && yy < N - times && xx >= times && xx < M - times) {
                    tmp = arr[yy][xx];
                    arr[yy][xx] = pre;
                    pre = tmp;
                    y = yy;
                    x = xx;
                } else idx++;
            }

        }
    }
}
