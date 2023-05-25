import java.io.*;
import java.util.*;
public class Main {
    static final int MORNING = 0;
    static final int DINNER = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][][][] dist = new int[2][k+1][x][y];
        for (int[][][] tmp1 : dist) {
            for (int[][] tmp2 : tmp1) {
                for(int[] tmp3 : tmp2) Arrays.fill(tmp3, -1);
            }
        }
        String[] board = new String[x];
        for(int i = 0; i < x; i++)
            board[i] = br.readLine();

        Queue<Multiple> q = new ArrayDeque<>();
        dist[DINNER][0][0][0] = 1;
        q.add(new Multiple(DINNER,0,0,0));
        while (!q.isEmpty()) {
            Multiple cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nt = (cur.time == MORNING) ? DINNER : MORNING;
                if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if (board[nx].charAt(ny) == '0') {
                    if (dist[nt][cur.breakable][nx][ny] == -1) {
                        q.add(new Multiple(nt, cur.breakable, nx, ny));
                        dist[nt][cur.breakable][nx][ny] =
                                dist[cur.time][cur.breakable][cur.x][cur.y] + 1;
                    }
                } else { // 다음이 벽인 경우
                    if (nt == MORNING) { // 다음이 아침인 경우, 즉 벽 부수기 가능
                        if (cur.breakable < k && dist[nt][cur.breakable + 1][nx][ny] == -1) {
                            q.add(new Multiple(nt, cur.breakable + 1, nx, ny));
                            dist[nt][cur.breakable + 1][nx][ny] =
                                    dist[cur.time][cur.breakable][cur.x][cur.y] + 1;
                        }
                    } else { // 다음이 밤인 경우, 벽 부수기 불가능
                        if(dist[nt][cur.breakable][cur.x][cur.y] == -1) {
                            q.add(new Multiple(nt, cur.breakable, cur.x, cur.y));
                            dist[nt][cur.breakable][cur.x][cur.y] =
                                    dist[cur.time][cur.breakable][cur.x][cur.y] + 1;
                        }
                    }
                }
            }
        }
        int result = 987654321;
        boolean impossible = true;
        for(int j = 0; j < 2; j++) {
            for (int i = 0; i <= k; i++) {
                if(dist[j][i][x-1][y-1] == -1) continue;
                result = Math.min(result, dist[j][i][x-1][y-1]);
                impossible = false;
            }
        }
        bw.write(String.valueOf((impossible) ? "-1" : result));
        bw.flush();
        bw.close();
    }
    static class Multiple {
        int x, y, breakable, time;
        public Multiple(int time, int breakable, int x, int y) {
            this.x = x;
            this.y = y;
            this.breakable = breakable;
            this.time = time;
        }
    }
}