import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(br.readLine());
        boolean[][] vis = new boolean[len][len];
        String[] board = new String[len];
        for (int i = 0; i < len; i++)
            board[i] = br.readLine();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Pair> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>(25);
        int complexCnt = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if(vis[i][j] || board[i].charAt(j) == '0') continue;
                q.add(new Pair(i, j)); vis[i][j] = true;
                complexCnt++;
                int area = 1;
                while (!q.isEmpty()) {
                    Pair cur = q.remove();
                    for (int k = 0; k < 4; k++) {
                        int nx = cur.x + dx[k];
                        int ny = cur.y + dy[k];
                        if(nx < 0 || ny < 0 || nx >= len || ny >= len) continue;
                        if(vis[nx][ny] || board[nx].charAt(ny) == '0') continue;
                        q.add(new Pair(nx, ny)); vis[nx][ny] = true;
                        area++;
                    }
                }
                result.add(area);
            }
        }
        bw.write(complexCnt + "\n");
        Collections.sort(result);
        for(int tmp : result)
            bw.write(tmp + "\n");
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
