import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int minTime = Integer.MAX_VALUE / 16;
        int[] time = new int[100001];
        Queue<Integer> q = new LinkedList<>();

        q.add(N); time[N] = 1; int cnt = 0;
        while (!q.isEmpty()) {
            int cx = q.remove();
            if (minTime < time[cx]) break;
            for (int nx : new int[]{cx + 1, cx - 1, cx * 2}) {
                if (nx < 0 || nx > 100000) continue;
                if (time[nx] == 0 || time[nx] == time[cx] + 1) {
                    q.add(nx);
                    time[nx] = time[cx] + 1;
                }
                if (nx == K) {
                    minTime = time[cx];
                    cnt++;
                }
            }
        }
        if(N == K) bw.write(0 + "\n1");
        else bw.write(minTime + "\n" + cnt);
        bw.flush();
        bw.close();
    }
}