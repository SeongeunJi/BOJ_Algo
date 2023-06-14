import java.io.*;
import java.util.*;
public class Main {
    static int n, m, whitespace;
    static Queue<Pair> q = new LinkedList<>();
    static List<Pair> virusList = new ArrayList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[] virus_vis;
    static int[] virus_arr;
    static byte[][] board;
    static int answer = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new byte[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Byte.parseByte(st.nextToken());
                if (board[i][j] == 2) virusList.add(new Pair(i, j));
                if(board[i][j] == 0) whitespace++;
            }
        }
        if(whitespace == 0) answer = 0;
        else {
            virus_vis = new boolean[virusList.size()];
            virus_arr = new int[m];
            activateVirus(0, 0);
            answer = (answer == 987654321) ? -1 : answer;
        }
        System.out.println(answer);
    }

    private static void activateVirus(int start, int virusCnt) {
        if (virusCnt == m) {
            bfs(); return;
        }
        for (int i = start; i < virusList.size(); i++) {
            if (!virus_vis[i]) {
                virus_arr[virusCnt] = i;
                virus_vis[i] = true;
                activateVirus(i,virusCnt+1);
                virus_vis[i] = false;
            }
        }
    }

    // non-activate = 2, activate = 3
    private static void bfs() {
        if(!q.isEmpty()) q.clear();
        int tmp = whitespace;
        boolean[][] vis = new boolean[n][n];
        for (int i : virus_arr) {
            Pair initVir = virusList.get(i);
            vis[initVir.x][initVir.y] = true;
            q.add(initVir);
        }
        int time = 0;
        while (!q.isEmpty()) {
            int ls = q.size();
            while(ls --> 0) {
                Pair cur = q.remove();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if (board[nx][ny] == 1 || vis[nx][ny]) continue;
                    if (board[nx][ny] == 0) tmp--;
                    q.add(new Pair(nx, ny));
                    vis[nx][ny] = true;
                }
            }
            time++;
            if(time >= answer) return;
            if(tmp <= 0) answer = time;
        }
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}