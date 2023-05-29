import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int MAX = 1000000;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] board = new int[x][y];
        int[] pi = new int[p+1];
        int[] result = new int[p+1];
        List<Queue<Pair>> lists = new LinkedList<>();

        for(int i = 0; i <= p; i++)
            lists.add(new LinkedList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= p; i++) {
            pi[i] = Integer.parseInt(st.nextToken());
            if(pi[i] > MAX) pi[i] = MAX;
        }

        for (int i = 0; i < x; i++) {
            String input = br.readLine();
            for (int j = 0; j < y; j++) {
                char ch = input.charAt(j);
                if(ch == '#') board[i][j] = -1;
                else if(ch == '.') board[i][j] = 0;
                else {
                    board[i][j] = ch - '0';
                    lists.get(board[i][j]).add(new Pair(i, j));
                    result[board[i][j]]++;
                }
            }
        }

        boolean finish = false;
        while (!finish) {
            finish = true;
            for (int idx = 1; idx <= p; idx++) {
                int exCnt = 0;
                while (!lists.get(idx).isEmpty()) {
                    int size = lists.get(idx).size();
                    while(size --> 0) {
                        Pair cur = lists.get(idx).poll();
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];
                            if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                            if(board[nx][ny] != 0) continue;
                            lists.get(idx).add(new Pair(nx, ny));
                            board[nx][ny] = idx;
                            result[idx]++;
                        }
                    }
                    if(++exCnt == pi[idx]) break;
                }
            }
            for (Queue<Pair> q : lists) {
                if (!q.isEmpty()) {
                    finish = false;
                    break;
                }
            }
        }
        for(int i = 1; i <= p; i++)
            bw.write(result[i] + " ");
        bw.flush();
        bw.close();
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
