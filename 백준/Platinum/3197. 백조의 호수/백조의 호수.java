import java.io.*;
import java.util.*;
public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Pair>[] swans = new Queue[2];
        for (int i = 0; i < 2; i++)
            swans[i] = new LinkedList<>();
        Queue<Pair> waterQ = new LinkedList<>();
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        boolean[][] vis1 = new boolean[x][y];
        boolean[][] vis2 = new boolean[x][y];
        char[][] board = new char[x][y];
        for (int i = 0; i < x; i++) {
            String input = br.readLine();
            for (int j = 0; j < y; j++) {
                board[i][j] = input.charAt(j);
                if(board[i][j] == 'L') {
                    waterQ.add(new Pair(i,j));
                    vis1[i][j] = true;
                    swans[0].add(new Pair(i, j));
                }
                else if(board[i][j] == '.'){
                    waterQ.add(new Pair(i, j));
                    vis1[i][j] = true;
                }
            }
        }
        // 도착 백조 좌표
        Pair swan1 = swans[0].remove();
        // 시작 백조 좌표
        Pair swan2 = swans[0].peek();
        vis2[swan2.x][swan2.y] = true;
        int result = 0;
        for (int days = 0; !vis2[swan1.x][swan1.y]; days++, result++) {
            int waters = waterQ.size();
            while(waters --> 0) {
                Pair cur = waterQ.remove();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if(nx < 0 || nx >= x || ny < 0 || ny >= y) continue;
                    if(vis1[nx][ny] || board[nx][ny] != 'X') continue;
                    board[nx][ny] = '.';
                    vis1[nx][ny] = true;
                    waterQ.add(new Pair(nx, ny));
                }
            }
            int mod = days % 2;
            while (!swans[mod].isEmpty()) {
                Pair cur = swans[mod].remove();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if(nx < 0 || nx >= x || ny < 0 || ny >= y || vis2[nx][ny]) continue;
                    // 오늘 백조가 갈 수 있었다면 내일은 반드시 갈 수 있음
                    if(board[nx][ny] == 'X') {
                        swans[1-mod].add(new Pair(nx, ny));
                        vis2[nx][ny] = true;
                        continue;
                    }
                    swans[mod].add(new Pair(nx, ny));
                    vis2[nx][ny] = true;
                }
            }
        }
        bw.write(String.valueOf(result));
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