import java.io.*;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Character> stack = new Stack<>();
        int cnt = 0;
        while (N-- > 0) {
            char[] source = br.readLine().toCharArray();
            for (char ch : source) {
                if(stack.isEmpty() || stack.peek() != ch) stack.push(ch);
                else if(stack.peek() == ch) stack.pop();
            }
            if(stack.empty()) cnt++;
            stack.clear();
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
    }
}
