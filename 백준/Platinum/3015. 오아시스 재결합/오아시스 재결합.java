import java.io.*;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Pair> stack = new Stack<>();
        long ans = 0;

        while (N --> 0) {
            int curHeight = Integer.parseInt(br.readLine());
            int cnt = 1;

            while (!stack.empty() && stack.peek().height <= curHeight) {
                ans += stack.peek().cnt;
                if(stack.peek().height == curHeight) cnt += stack.peek().cnt;
                stack.pop();
            }
            if(!stack.empty()) ans++;
            stack.push(new Pair(curHeight, cnt));
        }


        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    static class Pair {
        int height, cnt;
        public Pair(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
}
