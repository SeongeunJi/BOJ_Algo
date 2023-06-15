import java.io.*;
import java.util.*;
public class Main {
    static char[] board = new char[101];
    static boolean[] vis = new boolean[101];
    static Map<Integer, Integer> ladderMap = new HashMap<>();
    static Map<Integer, Integer> snakeMap = new HashMap<>();
    static int[] dice = {1, 2, 3, 4, 5, 6};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ladders = Integer.parseInt(st.nextToken());
        int snakes = Integer.parseInt(st.nextToken());
        Arrays.fill(board, '.');
        char ch;
        for (int i = 1; i <= ladders + snakes; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if (i <= ladders) {
                ch = 'L';
                ladderMap.put(f, t);
            } else {
                ch = 'S';
                snakeMap.put(f, t);
            }
            board[f] = ch;
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1, 0));
        vis[1] = true;
        while (!q.isEmpty()) {
            Node cur = q.remove();
            if(cur.pos >= 100) return cur.time;
            for (int dir = 0; dir < 6; dir++) {
                int nx = cur.pos + dice[dir];
                if (nx >= 100) return cur.time + 1;
                if(vis[nx]) continue;
                if(board[nx] == 'S') {
                    q.add(new Node(snakeMap.get(nx), cur.time + 1));
                    vis[nx] = true;
                    continue;
                }
                if (board[nx] == 'L') {
                    q.add(new Node(ladderMap.get(nx), cur.time + 1));
                    vis[nx] = true;
                    continue;
                }
                q.add(new Node(nx, cur.time + 1));
                vis[nx] = true;
            }
        }
        return -1;
    }
    static class Node {
        int pos, time;
        public Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
}