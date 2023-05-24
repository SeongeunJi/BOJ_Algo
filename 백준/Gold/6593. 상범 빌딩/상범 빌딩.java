import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dx = {1, 0, -1, 0, 0, 0};
        int[] dy = {0, 1, 0, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] info = new int[3];
            Pair exit = null;
            for (int i = 0; st.hasMoreTokens(); i++)
                info[i] = Integer.parseInt(st.nextToken());
            if(info[0] == 0 && info[1] == 0 && info[2] == 0) {
                bw.flush();
                bw.close();
                break;
            }
            String[][] board = new String[info[0]][info[1]];
            int[][][] dist = new int[info[0]][info[1]][info[2]];

            for (int[][] tmp1 : dist) {
                for (int[] tmp2 : tmp1) Arrays.fill(tmp2, -1);
            }
            Queue<Pair> q = new LinkedList<>();
            for (int i = 0; i < info[0]; i++) {
                for (int j = 0; j < info[1] + 1; j++) {
                    String input = br.readLine();
                    if(j == info[1]) continue;
                    else board[i][j] = input;
                    for (int k = 0; k < info[2]; k++) {
                        if (input.charAt(k) == 'S') {
                            q.add(new Pair(i, j, k));
                            dist[i][j][k] = 0;
                        }
                        else if (input.charAt(k) == 'E') {
                            exit = new Pair(i, j, k);
                        }
                    }
                }
            }
            while (!q.isEmpty()) {
                Pair cur = q.remove();
                for (int i = 0; i < 6; i++) {
                    int nz = cur.z + dz[i];
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if(nz < 0 || nx < 0 || ny < 0 || nz >= info[0] || nx >= info[1] || ny >= info[2]) continue;
                    if (board[nz][nx].charAt(ny) == '#' || dist[nz][nx][ny] >= 0) continue;
                    q.add(new Pair(nz, nx, ny));
                    dist[nz][nx][ny] = dist[cur.z][cur.x][cur.y] + 1;
                }
            }
            if(dist[exit.z][exit.x][exit.y] == -1) bw.write("Trapped!\n");
            else bw.write("Escaped in " + dist[exit.z][exit.x][exit.y] +" minute(s).\n");
        }
    }

    static class Pair {
        int z, x, y;
        public Pair(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}