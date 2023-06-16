import java.util.*;
public class Main {
    static Map<Long, Long> hm = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextInt();
        long B = sc.nextInt();
        System.out.println(bfs(A,B));
    }

    static long bfs(long A, long B) {
        Queue<Long> q = new LinkedList<>();
        q.add(A);
        hm.put(A, 1L);
        while (!q.isEmpty()) {
            long cur = q.remove();
            for(long nx : new long[]{cur<<1, cur*10+1}) {
                if(nx > B || hm.containsKey(nx)) continue;
                if(nx == B) return hm.get(cur) + 1;
                q.add(nx);
                hm.put(nx, hm.get(cur) + 1);
            }
        }
        return -1;
    }
}
