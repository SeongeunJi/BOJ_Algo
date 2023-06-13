import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Pair> q = new LinkedList<>();
    static List<Pair> virusList = new ArrayList<>();
    static int[] virus_arr;
    static boolean[] virus_vis;
    static int side, virus;
    static byte[][] board;
    static int answer = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        side = Integer.parseInt(st.nextToken());
        virus = Integer.parseInt(st.nextToken());
        board = new byte[side][side];
        for (int i = 0; i < side; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < side; j++) {
                board[i][j] = Byte.parseByte(st.nextToken());
                if(board[i][j] == 2) virusList.add(new Pair(i, j,0));
            }
        }
        virus_arr = new int[virus];
        virus_vis = new boolean[virusList.size()];
        virusInstaller(0, 0);
        System.out.println(answer == 987654321 ? -1 : answer);
    }

    static void virusInstaller(int virusCnt, int start) {
        if (virusCnt == virus) {
            bfs(); return;
        }
        for (int i = start; i < virusList.size(); i++) {
            if (!virus_vis[i]) {
                virus_arr[virusCnt] = i;
                virus_vis[i] = true;
                virusInstaller(virusCnt+1, i);
                virus_vis[i] = false;
            }
        }
    }

    private static void bfs() {
        byte[][] copy_board = new byte[side][side];
        boolean[][] vis = new boolean[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                if(board[i][j] == 2) copy_board[i][j] = 0;
                else copy_board[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < virus; i++) {
            Pair virusLoc = virusList.get(virus_arr[i]);
            q.add(virusLoc);
            copy_board[virusLoc.x][virusLoc.y] = 2;
            vis[virusLoc.x][virusLoc.y] = true;
        }
        int time = -1;
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= side || ny >= side) continue;
                if(vis[nx][ny] || copy_board[nx][ny] == 1) continue;
                copy_board[nx][ny] = 2;
                q.add(new Pair(nx, ny, cur.time+1));
                vis[nx][ny] = true;
            }
            time = Math.max(cur.time, time);
        }
        if(isCheck(copy_board)) answer = Math.min(answer, time);
    }

    private static boolean isCheck(byte[][] copy_board) {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                if(copy_board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Pair {
        int x,y,time;
        public Pair(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}