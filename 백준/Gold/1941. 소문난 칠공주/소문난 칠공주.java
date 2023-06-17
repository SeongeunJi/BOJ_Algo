import java.io.*;
import java.util.*;
public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] board = new char[5][5];
    static boolean[] arr = new boolean[25];
    static List<Pair> list = new ArrayList<>();
    static int ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = input.charAt(j);
                list.add(new Pair(i, j));
            }
        }
        go(0, 0);
        System.out.println(ans);
    }

    static void go(int level, int start) {
        if (level == 7) {
            bfs(start-1);
            return;
        }
        for (int i = start; i < 25; i++) {
            arr[i] = true;
            go(level+1, i+1);
            arr[i] = false;
        }
    }

    static void bfs(int init) {
        int S = 0, Y = 0;
        Pair pair = list.get(init);
        Queue<Pair> q = new LinkedList<>();
        boolean[][] vis = new boolean[5][5];
        if(board[pair.x][pair.y] == 'S') S++;
        else Y++;
        q.add(pair);
        vis[pair.x][pair.y] = true;
        
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if(vis[nx][ny] || !arr[nx*5+ny]) continue;
                Pair nxPair = new Pair(nx, ny);
                q.add(nxPair);
                vis[nx][ny] = true;
                if(board[nxPair.x][nxPair.y] == 'S') S++;
                else Y++;
            }
        }
        if((S + Y == 7) && S >= 4) ans++;
    }
    static class Pair {
        int x,y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}