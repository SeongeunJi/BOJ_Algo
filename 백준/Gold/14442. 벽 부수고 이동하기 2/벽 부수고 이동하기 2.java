import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][][] dist = new int[k+1][x][y];
        for (int[][] tmp1 : dist) {
            for(int[] tmp2 : tmp1) Arrays.fill(tmp2, -1);
        }
        String[] board = new String[x];
        for(int i = 0; i < x; i++)
            board[i] = br.readLine();
        Queue<Multiple> q = new LinkedList<>();
        q.add(new Multiple(0,0, 0));
        dist[0][0][0] = 1;
        while (!q.isEmpty()) {
            Multiple cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if (board[nx].charAt(ny) == '0') {
                    if (dist[cur.breakable][nx][ny] == -1) {
                        q.add(new Multiple(nx, ny, cur.breakable));
                        dist[cur.breakable][nx][ny] = dist[cur.breakable][cur.x][cur.y] + 1;
                    }
                } else {
                    if (cur.breakable < k && dist[cur.breakable + 1][nx][ny] == -1) {
                        q.add(new Multiple(nx, ny, cur.breakable + 1));
                        dist[cur.breakable + 1][nx][ny] = dist[cur.breakable][cur.x][cur.y] + 1;
                    }
                }
            }
        }
        int result = 987654321;
        boolean impossible = true;
        for (int i = 0; i <= k; i++) {
            if(dist[i][x-1][y-1] == -1) continue;
            result = Math.min(result, dist[i][x-1][y-1]);
            impossible = false;
        }
        bw.write(String.valueOf((impossible) ? "-1" : result));
        bw.flush();
        bw.close();
    }
    static class Multiple {
        int x, y, breakable;
        public Multiple(int x, int y, int breakable) {
            this.x = x;
            this.y = y;
            this.breakable = breakable;
        }
    }
}