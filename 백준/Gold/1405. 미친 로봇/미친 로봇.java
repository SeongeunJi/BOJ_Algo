import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static double[] percents = new double[4];
    static boolean[][] vis = new boolean[30][30];
    static int[] arr;
    static double ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < 4; i++)
            percents[i] = Double.parseDouble(st.nextToken()) / 100.;
        go(0, 15,15, 1);
        System.out.println(ans);
    }
    static void go(int level, int cx, int cy, double percent) {
        if(level == N) {
            ans += percent;
            return;
        }
        vis[cx][cy] = true;
        for(int dir = 0; dir < 4; dir++) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            if(nx < 0 || ny < 0 || nx >= 30 || ny >= 30) continue;
            if(vis[nx][ny]) continue;
            vis[nx][ny] = true;
            go(level+1, nx, ny, percent * percents[dir]);
            vis[nx][ny] = false;
        }
    }
}