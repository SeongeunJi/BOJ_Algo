import java.io.*;
import java.util.*;
public class Main {
    static Map<Integer, Integer> groupStore = new HashMap<>();
    static Set<Integer> visSet = new TreeSet<>();
    static Queue<Pair> q = new ArrayDeque<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int x;
    static int y;
    static String[] board;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        map = new int[x][y];
        for(int[] tmp : map) Arrays.fill(tmp, -1);
        board = new String[x];
        for (int i = 0; i < x; i++)
            board[i] = br.readLine();

        int groupID = 1;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                group(groupID, i, j);
                groupID++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++)
                sb.append(bfs(i,j));
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int bfs(int x1, int y1) {
        if(map[x1][y1] != -1) return 0;

        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x1 + dx[i];
            int ny = y1 + dy[i];
            if(nx < 0 || ny < 0 || nx >= x || ny >= y || board[nx].charAt(ny) == '1') continue;
            if(visSet.contains(map[nx][ny])) continue;
            result += groupStore.get(map[nx][ny]);
//            System.out.println(map[nx][ny]);
            visSet.add(map[nx][ny]);
        }
        visSet.clear();
        return (result + 1) % 10;
    }

    private static void group(int groupID, int x1, int y1) {
        if(board[x1].charAt(y1) == '1' || map[x1][y1] >= 0) return;
        int area = 1;
        q.add(new Pair(x1, y1));
        map[x1][y1] = groupID;
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;
                if(map[nx][ny] >= 0 || board[nx].charAt(ny) == '1') continue;
                q.add(new Pair(nx, ny));
                map[nx][ny] = groupID;
                area++;
            }
        }
        groupStore.put(groupID, area);
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}