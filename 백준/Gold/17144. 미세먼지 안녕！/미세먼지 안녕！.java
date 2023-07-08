import java.io.*;
import java.util.*;
public class Main {
    static int X, Y, t;
    static Pair[][] board;
    static int[] dx = {1, 0, -1, 0}; // 하,우,상,좌
    static int[] dy = {0, 1, 0, -1};
    static final int DOWN = 0, RIGHT = 1, UP = 2, LEFT = 3;
    static List<Pair> airCleaners = new ArrayList<>();
    static List<Pair> particulates = new LinkedList<>();
    static Queue<Pair> q = new LinkedList<>();
    static Deque<Pair> deque = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new Pair[X][Y];
        for(int i = 0; i < X; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < Y; j++) {
                int val = Integer.parseInt(st.nextToken());
                if(val == -1) {
                    board[i][j] = new Pair(i, j, val, 0);
                    airCleaners.add(board[i][j]);
                }
                else if(val != 0) {
                    board[i][j] = new Pair(i, j, val, 0);
                    particulates.add(board[i][j]);
                }
            }
        }
        while(t --> 0) {
            expanding_particulates();
            active_airCleaner();
        }
        System.out.println(particulates.stream().mapToInt(p -> p.amount).sum());
    }

    static void expanding_particulates() {
        for(Pair pair : particulates) {
            if(board[pair.x][pair.y].amount < 5) continue;
            int amount = board[pair.x][pair.y].amount / 5;
            int cnt = 0;
            for(int dir = 0; dir < 4; dir++) {
                int nx = pair.x + dx[dir];
                int ny = pair.y + dy[dir];
                if(nx < 0 || ny < 0 || nx >= X || ny >= Y || isAirCleaner(nx, ny)) continue;
                if(board[nx][ny] == null) {
                    board[nx][ny] = new Pair(nx, ny, amount, 0);
                    q.add(board[nx][ny]);
                } else board[nx][ny].add += amount;
                cnt++;
            }
            pair.amount -= amount * cnt;
        }
        while(!q.isEmpty()) {
            Pair remove = q.remove();
            board[remove.x][remove.y] = remove;
            particulates.add(remove);
        }
        for(Pair pair : particulates) {
            if(pair.add != 0) {
                pair.amount += pair.add;
                pair.add = 0;
            }
        }
    }

    static void active_airCleaner() {
        for(int i = 0; i < 2; i++) {
            Pair airCleaner = airCleaners.get(i);
            rotate(airCleaner.x, airCleaner.y, i != 0);
        }
    }

    static void rotate(int x, int y, boolean isClockwise) {
        int dir = RIGHT;
        int cx = x, cy = y;
        int nx, ny;
        while(true) {
             nx = cx + dx[dir];
             ny = cy + dy[dir];
             if(nx < 0 || ny < 0 || ny >= Y || nx >= X) {
                 Pair pair = deque.peekLast();
                 boolean flag = pair != null && (pair.x < 0 || pair.y < 0 || pair.x >= X || pair.y >= Y);
                 if(flag) {
                     pair.x -= dx[dir];
                     pair.y -= dy[dir];
                 }
                 if (ny >= Y) dir = isClockwise ? DOWN : UP;
                 else if (ny < 0) dir = isClockwise ? UP : DOWN;
                 else if (nx < 0 || nx >= X) dir = LEFT;
                 if(flag) {
                     pair.x += dx[dir];
                     pair.y += dy[dir];
                 }
             }
             else {
                cx = nx;
                cy = ny;
                if(isAirCleaner(nx, ny)) break;
                if(board[nx][ny] != null) {
                    board[nx][ny].x += dx[dir];
                    board[nx][ny].y += dy[dir];
                    deque.add(board[nx][ny]);
                    board[nx][ny] = null;
                }
             }
        }
        while(!deque.isEmpty()) {
            Pair cur = deque.remove();
            if(cur.x == x && cur.y == y) particulates.remove(cur);
            else board[cur.x][cur.y] = cur;
        }
    }

    private static boolean isAirCleaner(int x, int y) {
        return board[x][y] != null && board[x][y].amount == -1;
    }

    static class Pair {
        int x, y, amount, add;
        public Pair(int x, int y, int amount, int add) {
            this.x = x;
            this.y = y;
            this.amount = amount;
            this.add = add;
        }
    }
}