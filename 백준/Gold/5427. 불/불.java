import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int tc = Integer.parseInt(br.readLine());
        while(tc --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            String[] board = new String[x];
            int[][] dist1 = new int[x][y];
            int[][] dist2 = new int[x][y];

            for (int i = 0; i < x; i++) {
                Arrays.fill(dist1[i], -1);
                Arrays.fill(dist2[i], -1);
            }

            Queue<Pair<Integer>> fireQ = new ArrayDeque<>();
            Queue<Pair<Integer>> personQ = new ArrayDeque<>();
            for (int i = 0; i < x; i++) {
                board[i] = br.readLine();
                for (int j = 0; j < y; j++) {
                    char ch = board[i].charAt(j);
                    switch (ch) {
                        case '*' -> {
                            fireQ.add(new Pair<>(i, j));
                            dist1[i][j] = 0;
                        }
                        case '@' -> {
                            personQ.add(new Pair<>(i, j));
                            dist2[i][j] = 0;
                        }
                    }
                }
            }

            while (!fireQ.isEmpty()) {
                Pair<Integer> cur = fireQ.remove();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                    if(dist1[nx][ny] >= 0 || board[nx].charAt(ny) == '#') continue;
                    fireQ.add(new Pair<>(nx, ny));
                    dist1[nx][ny] = dist1[cur.x][cur.y] + 1;
                }
            }

            boolean escapable = false;
            outer:
            while (!personQ.isEmpty()) {
                Pair<Integer> cur = personQ.remove();
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= x || ny >= y) {
                        bw.write((dist2[cur.x][cur.y] + 1) + "\n");
                        escapable = true; break outer;
                    }
                    if (dist2[nx][ny] >= 0 || board[nx].charAt(ny) == '#') continue;
                    if(dist2[cur.x][cur.y] + 1 >= dist1[nx][ny] && dist1[nx][ny] != -1) continue;
                    personQ.add(new Pair<>(nx, ny));
                    dist2[nx][ny] = dist2[cur.x][cur.y] + 1;
                }
            }
            if(!escapable) bw.write("IMPOSSIBLE\n");
        }
        bw.flush();
        bw.close();
    }
    static class Pair<T> {
        T x, y;
        public Pair(T x, T y) {
            this.x = x;
            this.y = y;
        }
    }
}