import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] numArr = new int[N], result = new int[N];
        Stack<Integer> indexStack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; st.hasMoreTokens(); i++)
            numArr[i] = Integer.parseInt(st.nextToken());

        indexStack.push(0);
        for (int i = 0; i < N; i++) {
            while (!indexStack.empty() && numArr[indexStack.peek()] < numArr[i])
                result[indexStack.pop()] = numArr[i];
            indexStack.push(i);
        }

        while(!indexStack.empty())
            result[indexStack.pop()] = -1;

        for(int i : result)
            sb.append(i).append(" ");
        System.out.println(sb);
    }
}