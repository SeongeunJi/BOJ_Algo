
import java.io.*;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            char[] input = br.readLine().toCharArray();
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();

            for (char ch : input) {
                if (ch == '<') {
                    if(!left.empty())
                        right.push(left.pop());
                }
                else if (ch == '>') {
                    if(!right.empty())
                        left.push(right.pop());
                }
                else if (ch == '-') {
                    if(!left.empty())
                        left.pop();
                }
                else
                    left.push(ch);
            }
            for (Character character : left) bw.write(character);
            while(!right.empty()) bw.write(right.pop());
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
