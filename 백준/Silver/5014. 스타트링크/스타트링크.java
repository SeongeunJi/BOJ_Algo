import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int height = sc.nextInt(), gangHo = sc.nextInt(), goal = sc.nextInt();
        int up = sc.nextInt(), down = sc.nextInt();

        Queue<Integer> q = new ArrayDeque<>();
        int[] button = {up, -down};
        int[] dist = new int[height+1];
        Arrays.fill(dist, -1);
        dist[gangHo] = 0;
        q.add(gangHo);

        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int i = 0; i < 2; i++) {
                int nx = cur + button[i];
                if(nx < 1 || nx > height) continue;
                if(dist[nx] >= 0) continue;
                q.add(nx);
                dist[nx] = dist[cur] + 1;
            }
        }

        bw.write((dist[goal] == -1) ? "use the stairs" : String.valueOf(dist[goal]));
        bw.flush();
        bw.close();
    }
}