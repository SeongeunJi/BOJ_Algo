import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dist = new int[100001];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(n); dist[n] = 0;

        while (dist[k] == -1) {
            int cx = q.remove();
            for (int i = 0; i < 3; i++) {
                int nx;

                if(i == 2) nx = cx + 1;
                else if(i == 1) nx = cx - 1;
                else nx = cx * 2;

                if(nx < 0 || nx > 100000 || dist[nx] != -1) continue;
                if (i != 0) dist[nx] = dist[cx] + 1;
                else dist[nx] = dist[cx];

                q.add(nx);
            }
        }
        bw.write(String.valueOf(dist[k]));
        bw.flush();
        bw.close();
    }
}