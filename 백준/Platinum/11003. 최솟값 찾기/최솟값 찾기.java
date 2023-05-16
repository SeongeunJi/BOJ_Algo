import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Pair> deque = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            while(!deque.isEmpty() && deque.peekLast().val > cur) deque.pollLast();
            deque.offer(new Pair(cur, i));
            if(deque.peek().idx < i - L + 1)
                deque.pollFirst();
            bw.write(deque.peekFirst().val + " ");
        }
        bw.flush();
        bw.close();
    }

    static class Pair {
        int val, idx;
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}