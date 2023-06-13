import java.io.*;
import java.util.*;
public class Main {
    static Queue<Pair> q = new LinkedList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static byte[][] board;
    static int ans = -1;
    static int x, y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        board = new byte[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < y; j++)
                board[i][j] = Byte.parseByte(st.nextToken());
        }
        buildingWall(0);
        System.out.println(ans);
    }
    static void buildingWall(int wallCnt) {
        if (wallCnt == 3) {
            bfs(); return;
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(board[i][j] != 0) continue;
                board[i][j] = 1;
                buildingWall(wallCnt+1);
                board[i][j] = 0;
            }
        }
    }
    static void bfs() {
        boolean[][] vis = new boolean[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(board[i][j] == 2) {
                    q.add(new Pair(i,j));
                    vis[i][j] = true;
                }
            }
        }
        byte[][] copyBoard = copyBoard();
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || nx >= x || ny < 0 || ny >= y) continue;
                if(vis[nx][ny] || copyBoard[nx][ny] != 0) continue;
                copyBoard[nx][ny] = 2;
                q.add(new Pair(nx, ny));
                vis[nx][ny] = true;
            }
        }
        ans = Math.max(ans, getBlankCnt(copyBoard));
    }

    private static int getBlankCnt(byte[][] board) {
        int cnt = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(board[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
    private static byte[][] copyBoard() {
        byte[][] copy_board = new byte[x][y];
        for (int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++)
                copy_board[i][j] = board[i][j];
        }
        return copy_board;
    }

    static class Pair {
        int x,y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}