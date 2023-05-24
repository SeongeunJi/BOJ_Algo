import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
        String[] board = new String[x];
        int[][][] dist = new int[2][x][y];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int[][] tmp : dist) {
            for(int[] tpm : tmp) Arrays.fill(tpm, -1);
        }
        for (int i = 0; i < x; i++) board[i] = br.readLine();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, true)); dist[0][0][0] = 1;
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if (board[nx].charAt(ny) == '0') {
                    if (cur.canBreak && dist[0][nx][ny] == -1) {
                        q.add(new Pair(nx, ny, true));
                        dist[0][nx][ny] = dist[0][cur.x][cur.y] + 1;
                    }
                    if (!cur.canBreak && dist[1][nx][ny] == -1) {
                        q.add(new Pair(nx, ny, false));
                        dist[1][nx][ny] = dist[1][cur.x][cur.y] + 1;
                    }
                } else {
                    if (cur.canBreak) {
                        q.add(new Pair(nx, ny, false));
                        dist[1][nx][ny]= dist[0][cur.x][cur.y] + 1;
                    }
                }
            }
        }
        int r1 = dist[0][x-1][y-1], r2 = dist[1][x-1][y-1];
        if(r1 == -1 && r2 == -1) bw.write("-1");
        else if(r1 == -1) bw.write(String.valueOf(r2));
        else if(r2 == -1) bw.write(String.valueOf(r1));
        else bw.write(String.valueOf(Math.min(r1,r2)));
        bw.flush();
        bw.close();
    }
    static class Pair {
        int x, y; boolean canBreak;
        public Pair(int x, int y, boolean canBreak) {
            this.x = x;
            this.y = y;
            this.canBreak = canBreak;
        }
    }
}