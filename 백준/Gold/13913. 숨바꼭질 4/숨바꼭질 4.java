import java.io.*;
import java.util.*;

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
            for (int nx : new int[]{cx + 1, cx - 1, cx * 2}) {
                if(nx < 0 || nx > 100000 || dist[nx] >= 0) continue;
                dist[nx] = dist[cx] + 1;
                q.add(nx);
            }
        }
        bw.write(dist[k] + "\n");
        q.clear();

        Deque<Integer> result = new ArrayDeque<>();
        result.add(k); q.add(k);
        int minTime = dist[k];
        while (minTime != 0) {
            int cx = q.remove();
            for (int i = 0; i < 3; i++) {
                int nx;

                if(i == 0) nx = cx / 2;
                else if (i == 1) nx = cx + 1;
                else  nx = cx - 1;

                if(nx < 0 || nx > 100000 || dist[nx] != minTime - 1) continue;
                if(i == 0 && cx % 2 != 0) continue;

                q.add(nx);
                result.add(nx);
                minTime--;
                break;
            }
        }
        Iterator<Integer> iterator = result.descendingIterator();
        while(iterator.hasNext())
            bw.write(iterator.next() + " ");
        bw.flush();
        bw.close();
    }
}