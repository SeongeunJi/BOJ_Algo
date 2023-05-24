import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] board = new int[x][y];
        for(int[] tmp : board)
            Arrays.fill(tmp, -1);

        while(k --> 0) {
            st = new StringTokenizer(br.readLine());
            int[] tmp = new int[4];
            for (int i = 0; i < 4; i++)
                tmp[i] = Integer.parseInt(st.nextToken());

            for (int i = tmp[1]; i < tmp[3]; i++) {
                for (int j = tmp[0]; j < tmp[2]; j++)
                    board[i][j] = 1;
            }
        }
        List<Integer> result = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();
        int areaCnt = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(board[i][j] == 1) continue;
                q.add(new Pair(i, j));
                areaCnt++;
                board[i][j] = 1;
                int area = 1;
                while (!q.isEmpty()) {
                    Pair cur = q.remove();
                    for (int idx = 0; idx < 4; idx++) {
                        int nx = cur.x + dx[idx];
                        int ny = cur.y + dy[idx];
                        if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                        if(board[nx][ny] >= 0) continue;
                        area++;
                        q.add(new Pair(nx, ny));
                        board[nx][ny] = 1;
                    }
                }
                result.add(area);
            }
        }
        bw.write(areaCnt + "\n");

        Collections.sort(result);
        for(int tmp : result)
            bw.write(tmp + " ");
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