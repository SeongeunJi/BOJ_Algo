import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] diagram = new int[N];
        for (int i = 0; i < N; i++)
            diagram[i] = Integer.parseInt(br.readLine());

        Stack<Integer> idx = new Stack<>();
        int max = 0;
        for (int i = 0; i < N; i++) {

            while (!idx.empty() && diagram[idx.peek()] >= diagram[i]) {
                int height = diagram[idx.pop()];
                int width = (idx.isEmpty()) ? i : i - idx.peek() - 1;
                max = Math.max(max, height * width);
            }
            idx.push(i);
        }

        while (!idx.empty()) {
            int height = diagram[idx.pop()];
            int width = (idx.isEmpty()) ? N : N - idx.peek() - 1;
            max = Math.max(max, height * width);
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
