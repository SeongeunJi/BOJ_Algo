import java.io.*;
import java.util.*;
public class Main {
    static int[] bridge;
    static Queue<Integer> q = new LinkedList<>();
    static int n, w, L, time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        bridge = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            q.add(Integer.parseInt(st.nextToken()));
        do {
            int curWeight = calWeight();
            if (curWeight <= L) {
                curWeight -= bridge[w-1];
                move();
                if(!q.isEmpty() && curWeight + q.peek() <= L)
                    bridge[0] = q.remove();
            }
            time++;
        } while (!isAllPassed());
        System.out.println(time);
    }

    static int calWeight() {
        return Arrays.stream(bridge).sum();
    }

    static boolean isAllPassed() {
        return Arrays.stream(bridge).allMatch(e -> e == 0);
    }

    static void move() {
        for (int i = w-1; i > 0; i--)
            bridge[i] = bridge[i-1];
        bridge[0] = 0;
    }
}