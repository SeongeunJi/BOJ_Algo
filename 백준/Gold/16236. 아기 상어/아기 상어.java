import java.io.*;
import java.util.*;
public class Main {
    static Queue<Pair> q1 = new LinkedList<>();
    static Queue<Pair> q2 = new LinkedList<>();
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int n;
    static int[][] board;
    static boolean[][] vis1;
    static boolean[][] vis2;
    static int[][] dist;
    static BabyShark babyShark = new BabyShark(2,0);
    static Pair targetFish;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        dist = new int[n][n];
        vis1 = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9) {
                    q1.add(new Pair(i, j));
                    q2.add(new Pair(i, j));
                    vis1[i][j] = true;
                    board[i][j] = 0;
                }
            }
        }
        bfs();
        System.out.println(answer);
    }

    static void bfs() {
        targetFish = getFishLoc();
        while (!q1.isEmpty()) {
            if(targetFish == null) return;
            Pair cur = q1.remove();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(board[nx][ny] > 0 && board[nx][ny] > babyShark.level) continue;
                if(vis1[nx][ny]) continue;
                if (nx == targetFish.x && ny == targetFish.y) {
                    answer += dist[cur.x][cur.y] + 1;
                    level_up();
                    q1.clear(); q1.add(new Pair(nx, ny));
                    q2.add(new Pair(nx, ny));
                    vis1 = new boolean[n][n];
                    dist = new int[n][n];
                    board[nx][ny] = 0;
                    vis1[nx][ny] = true;
                    targetFish = getFishLoc();
                    if(targetFish == null) return;
                    break;
                }
                q1.add(new Pair(nx, ny));
                vis1[nx][ny] = true;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
            }
        }
    }

    private static Pair getFishLoc() {
        vis2 = new boolean[n][n];
        int[][] dist2 = new int[n][n];
        Map<Pair, Integer> tMap = new TreeMap<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return (o1.x != o2.x) ? o1.x - o2.x
                                      : o1.y - o2.y;
            }
        });
        boolean flag = false;
        while (!q2.isEmpty()) {
            Pair cur = q2.remove();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(board[nx][ny] > 0 && board[nx][ny] > babyShark.level) continue;
                if(vis2[nx][ny]) continue;
                if(board[nx][ny] > 0 && board[nx][ny] < babyShark.level) {
                    tMap.put(new Pair(nx, ny), dist2[cur.x][cur.y] + 1);
                    flag = true;
                }
                if(!flag) {
                    q2.add(new Pair(nx, ny));
                    vis2[nx][ny] = true;
                    dist2[nx][ny] = dist2[cur.x][cur.y] + 1;
                }
            }
        }
        if(tMap.isEmpty()) return null;
        int min = 987654321;
        for(int dist : tMap.values()) min = Math.min(min, dist);
        for (Pair pair : tMap.keySet()) {
            if(tMap.get(pair) == min) return pair;
        }
        return null;
    }

    private static void level_up() {
        if(babyShark.level == ++babyShark.exp){
            babyShark.level++;
            babyShark.exp = 0;
        }
    }
    static class Pair {
        int x,y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    static class BabyShark {
        int level, exp;
        public BabyShark(int level, int exp) {
            this.level = level;
            this.exp = exp;
        }
    }
}
