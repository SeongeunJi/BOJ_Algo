import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        String[] board = new String[x];
        int[][] fireDist = new int[x][y];
        int[][] personDist = new int[x][y];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Pair> fireQ = new LinkedList<>();
        Queue<Pair> personQ = new LinkedList<>();

        for (int i = 0; i < x; i++) {
            Arrays.fill(fireDist[i], -1);
            Arrays.fill(personDist[i], -1);
        }

        for (int i = 0; i < x; i++) {
            board[i] = br.readLine();
            for (int j = 0; j < y; j++) {
                switch (board[i].charAt(j)) {
                    case 'F' : {
                        fireQ.add(new Pair(i,j));
                        fireDist[i][j] = 0;
                    } break;
                    case 'J' : {
                        personQ.add(new Pair(i, j));
                        personDist[i][j] = 0;
                    }
                }
            }
        }

        while (!fireQ.isEmpty()) {
            Pair cur = fireQ.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if(board[nx].charAt(ny) == '#' || fireDist[nx][ny] >= 0) continue;
                fireQ.add(new Pair(nx, ny));
                fireDist[nx][ny] = fireDist[cur.x][cur.y] + 1;
            }
        }

        while (!personQ.isEmpty()) {
            Pair cur = personQ.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if((nx < 0 || ny < 0 || nx >= x || ny >= y)) {
                    bw.write(String.valueOf(personDist[cur.x][cur.y] + 1));
                    bw.flush();
                    System.exit(0);
                }
                if(personDist[nx][ny] >= 0 || board[nx].charAt(ny) == '#') continue;
                if(fireDist[nx][ny] != -1 && personDist[cur.x][cur.y] + 1 >= fireDist[nx][ny]) continue;
                personQ.add(new Pair(nx, ny));
                personDist[nx][ny] = personDist[cur.x][cur.y] + 1;
            }
        }
        bw.write("IMPOSSIBLE");
        bw.flush();
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}