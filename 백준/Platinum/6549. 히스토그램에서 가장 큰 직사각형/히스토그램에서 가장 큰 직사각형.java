import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if(N == 0) break;

            int[] histogram = new int[N];
            for(int i = 0; i < N; i++)
                histogram[i] = Integer.parseInt(st.nextToken());

            Stack<Integer> idx = new Stack<>();
            long max = 0;
            for (int i = 0; i < N; i++) {

                while (!idx.empty() && histogram[idx.peek()] >= histogram[i]) {
                    int height = histogram[idx.pop()];
                    long width = idx.isEmpty() ? i : i - idx.peek() -1;
                    max = Math.max(max, height * width);
                }
                idx.push(i);
            }

            while (!idx.empty()) {
                int height = histogram[idx.pop()];
                long width = idx.isEmpty() ? N : N - idx.peek() -1;
                max = Math.max(max, height * width);
            }

            bw.write(max + "\n");
        }
        bw.flush();
        bw.close();
    }
}
