import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static byte[][] back = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    static int x, y;
    static byte[][] board;
    static Queue<Pair> q = new LinkedList<>();
    static int ans = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        board = new byte[x][y];
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < y; j++) {
                board[i][j] = Byte.parseByte(st.nextToken());
            }
        }
        q.add(new Pair(x1, y1, dir));
        board[x1][y1] = 2;
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            boolean movable = false;
            for (int i = 0; i < 4; i++) {
                int nd = (cur.dir+3-i)%4;
                int nx = cur.x + dx[nd];
                int ny = cur.y + dy[nd];
                if(board[nx][ny] != 0) continue;
                movable = true;
                q.add(new Pair(nx, ny, nd));
                board[nx][ny] = 2;
                ans++;
                break;
            }
            if(!movable) {
                int nx = cur.x + back[cur.dir][0];
                int ny = cur.y + back[cur.dir][1];
                if(board[nx][ny] == 1) return;
                q.add(new Pair(nx, ny, cur.dir));
            }
        }
    }
    static class Pair {
        int x,y,dir;
        public Pair(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}