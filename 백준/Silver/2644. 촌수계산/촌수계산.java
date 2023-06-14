import java.io.*;
import java.util.*;
public class Main {
    static int n, from, to;
    static List<Set<Integer>> list = new ArrayList<>();
    static Queue<Node> q = new LinkedList<>();
    static boolean[] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        vis = new boolean[n+1];
        for (int i = 0; i <= n; i++) list.add(new HashSet<>());
        StringTokenizer st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
        int tc = Integer.parseInt(br.readLine());
        while(tc --> 0) {
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            list.get(one).add(two);
            list.get(two).add(one);
        }
        bfs();
    }

    static void bfs() {
        vis[from] = true;
        q.add(new Node(from, 0));
        while (!q.isEmpty()) {
            Node cur = q.remove();
            for (int next : list.get(cur.pointer)) {
                if(vis[next]) continue;
                if(next == to) {
                    System.out.println(cur.val+1);
                    System.exit(0);
                }
                q.add(new Node(next, cur.val + 1));
                vis[next] = true;
            }
        }
        System.out.println(-1);
    }
    static class Node {
        int pointer, val;
        public Node(int pointer, int val) {
            this.pointer = pointer;
            this.val = val;
        }
    }
}