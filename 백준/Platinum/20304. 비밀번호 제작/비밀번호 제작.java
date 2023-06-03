import java.io.*;
import java.util.*;
public class Main {
    static int[] dist = new int[1_000_001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<>();
        int n = ps(br), tc = ps(br);
        int[] p = new int[tc];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Arrays.fill(dist, -1);
        for (int i = 0; i < tc; i++) {
            p[i] = Integer.parseInt(st.nextToken());
            dist[p[i]] = 0;
            q.add(p[i]);
        }
        while (!q.isEmpty()) {
            int cur = q.remove();
            int nx;
            for (int i = 0; i < 31; i++) {
                if((cur & (1 << i)) != 0)
                    nx = cur & ~(1 << i);
                else
                    nx = cur | (1 << i);
                if(nx > n || dist[nx] >= 0) continue;
                dist[nx] = dist[cur] + 1;
                q.add(nx);
            }
        }
        bw.write(String.valueOf(Arrays.stream(dist).max().getAsInt()));
        bw.flush();
        bw.close();
    }
    private static int ps(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }
}