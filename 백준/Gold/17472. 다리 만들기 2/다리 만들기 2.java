import java.io.*;
import java.util.*;
public class Main {
    static int x, y;
    static Queue<Pair> q = new LinkedList<>();
    static Queue<Edge> pq = new PriorityQueue<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static byte[][] board;
    static byte[][] map;
    static int[] parents;
    static byte groupID;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        board = new byte[x][y];
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < y; j++)
                board[i][j] = Byte.parseByte(st.nextToken());
        }
        map = new byte[x][y];
        groupID = 1;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(board[i][j] == 0 || map[i][j] >= 1) continue;
                bfs(groupID++, i, j);
            }
        }
        parents = new int[groupID];
        for(int i = 0; i < groupID; i++)
            parents[i] = i;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(map[i][j] == 0) continue;
                getEdges(i, j);
            }
        }
        int result = kruskal();
        bw.write(String.valueOf((result == 0) ? "-1" : result));
        bw.flush();
        bw.close();
    }

    private static void getEdges(int i, int j) {
        int first = map[i][j];
        for (int k = 0; k < 4; k++) {
            int[][] dist = new int[x][y];
            dist[i][j] = 0;
            q.add(new Pair(i, j));
            while (!q.isEmpty()) {
                Pair cur = q.remove();
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if(OOB(nx,ny)) continue;
                if (board[nx][ny] == 1) {
                    if (first != map[nx][ny]) {
                        int bridgeLen = dist[cur.x][cur.y];
                        if (bridgeLen > 1)
                            pq.add(new Edge(map[nx][ny], map[i][j], bridgeLen));
                    }
                    break;
                }
                q.add(new Pair(nx,ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
            }
        }
    }
    private static int kruskal() {
        int result = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.remove();
            if(find(edge.from) != find(edge.to)) {
                result += edge.dist;
                union(edge.from,edge.to);
            }
        }
        for (int i = 2; i < groupID; i++) {
            if(parents[1] != find(i)) return 0;
        }
        return result;
    }
    static int find(int from) {
        return (from == parents[from])
                ? from : find(parents[from]);
    }

    static void union(int from, int to) {
        from = find(from);
        to = find(to);
        if(from < to) parents[to] = from;
        else parents[from] = to;
    }
    static void bfs(byte groupID, int i, int j) {
        q.add(new Pair(i, j));
        map[i][j] = groupID;
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if(OOB(nx, ny) || board[nx][ny] == 0 || map[nx][ny] >= 1) continue;
                q.add(new Pair(nx, ny));
                map[nx][ny] = groupID;
            }
        }
    }
    static boolean OOB(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= x || ny >= y;
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Edge implements Comparable<Edge> {
        int to, from;
        int dist;
        public Edge(int to, int from, int dist) {
            this.to = to;
            this.from = from;
            this.dist = dist;
        }
        @Override
        public int compareTo(Edge edge) {
            return this.dist - edge.dist;
        }
    }
}