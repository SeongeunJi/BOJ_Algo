import java.io.*;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] source = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int result = 0;
        int tmp = 1;
        char prev = ' ';

        for (char ch : source) {
            if (ch == '(' || ch == '[') {
                stack.push(ch);
                tmp = (ch == '(') ? tmp * 2 : tmp * 3;
            }
            else if (ch == ')') {
                if (stack.empty() || stack.peek() != '(') {
                    result = 0;
                    break;
                }
                else if (prev == '(') result += tmp;
                stack.pop();
                tmp /= 2;
            }
            else if (ch == ']') {
                if (stack.empty() || stack.peek() != '[') {
                    result = 0;
                    break;
                }
                else if (prev == '[') result += tmp;
                stack.pop();
                tmp /= 3;
            }
            prev = ch;
        }
        bw.write(String.valueOf(!stack.empty() ? 0 : result));
        bw.flush();
        bw.close();
    }
}