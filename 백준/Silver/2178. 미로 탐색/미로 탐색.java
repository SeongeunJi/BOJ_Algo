import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        String[] board = new String[x];
        int[][] dist =  new int[x][y];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < x; i++)
            board[i] = br.readLine();

        for(int[] tmp : dist)
            Arrays.fill(tmp, -1);

        Queue<Pair> Q = new ArrayDeque<>();
        Q.add(new Pair(0, 0));
        dist[0][0] = 0;
        while (!Q.isEmpty()) {
            Pair cur = Q.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if(board[nx].charAt(ny) == '0' || dist[nx][ny] >= 0) continue;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                Q.add(new Pair(nx, ny));
            }
        }
        bw.write(String.valueOf(dist[x-1][y-1] + 1));
        bw.flush();
        bw.close();
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
