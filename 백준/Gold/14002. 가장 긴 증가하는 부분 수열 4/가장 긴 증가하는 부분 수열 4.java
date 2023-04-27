import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N+1];
        int[] D = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; st.hasMoreTokens(); i++)
            numArr[i] = Integer.parseInt(st.nextToken());

        int max = 1;
        int idx = 1;
        for(int i = 0; i <= N; i++) {
            D[i] = 1;
            for(int j = 1; j < i; j++) {
                if(numArr[i] > numArr[j] && D[j] >= D[i]) {
                    D[i] = D[j] + 1;
                    if(max < D[i]) {
                        max = D[i];
                        idx = i;
                    }
                }
            }
        }

        bw.write(D[idx] + "\n");
        Stack<Integer> stack = new Stack<>();
        int tmp = D[idx];
        while (idx != 0) {
            if (D[idx] == tmp) {
                stack.push(numArr[idx]);
                tmp--;
            }
            idx--;
        }

        while (!stack.empty())
            bw.write(stack.pop() + " ");

        bw.flush();
        bw.close();
        br.close();
    }
}

