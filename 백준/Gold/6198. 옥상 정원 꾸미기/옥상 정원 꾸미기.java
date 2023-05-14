import java.io.*;
import java.util.Arrays;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 빌딩 수
        int N = Integer.parseInt(br.readLine());
        int[] towerArr = new int[N];
        // tower,index stack
        Stack<Integer> idx = new Stack<>();
        long[] result = new long[N];

        for (int i = 0; i < N; i++)
            towerArr[i] = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            // 어떠한 빌딩에 대해, 더 높은 빌딩이 있는 경우
            while(!idx.empty() && towerArr[idx.peek()] <= towerArr[i]) {
                int pop = idx.pop();
                result[pop] =  i - pop - 1L;
            }
            idx.push(i);
        }

        // 가장 높은 빌딩의 경우
        int end = idx.pop();
        while (!idx.empty()) {
            int pop = idx.pop();
            result[pop] = (long)end - pop;
        }

        bw.write(String.valueOf(Arrays.stream(result).sum()));
        bw.flush();
        bw.close();
    }
}