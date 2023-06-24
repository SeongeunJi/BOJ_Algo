import java.io.*;
import java.util.*;
public class Main {
    static int n, k, L, ans;
    static char[][] board;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static Deque<Pair> snake = new LinkedList<>();
    static Queue<Pair> q = new LinkedList<>();
    static boolean[] isCommand = new boolean[10000];
    static char[] command_dirArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        k = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++)
            Arrays.fill(board[i], '.');
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = '@';
        }
        L = Integer.parseInt(br.readLine());
        command_dirArr = new char[L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int fir = Integer.parseInt(st.nextToken());
            isCommand[fir] = true;
            command_dirArr[i] = st.nextToken().charAt(0);
        }
        game();
    }
    static void game() {
        boolean finish = false;
        int dir = 0;
        int idx = 0;
        board[0][0] = '$';
        snake.add(new Pair(0, 0));
        while(!finish) {
            ans++;
            Pair last = null;
            while(!snake.isEmpty()) {
                Pair cur = snake.remove();
                if(last == null) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == '$') {
                        finish = true;
                        break;
                    }
                    if(board[nx][ny] == '@') {
                        snake.addFirst(cur);
                        snake.addFirst(new Pair(nx, ny));
                        board[nx][ny] = '$';
                        break;
                    }
                    board[cur.x][cur.y] = '.';
                    board[nx][ny] = '$';
                    q.add(new Pair(nx, ny));
                } else {
                    board[cur.x][cur.y] = '.';
                    board[last.x][last.y] = '$';
                    q.add(last);
                }
                last = cur;
            }
            if(isCommand[ans])
                dir = setDir(command_dirArr[idx++], dir);
            while(!finish && !q.isEmpty())
                snake.add(q.remove());
        }
        System.out.println(ans);
    }

    static int setDir(char command, int dir) {
        return (command == 'L') ? (dir + 3) & 3 : (dir + 1) & 3;
    }

    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}