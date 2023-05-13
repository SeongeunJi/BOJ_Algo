
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 송신 탑의 개수
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> top = new Stack<>();
        Stack<Integer> idx = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int val = Integer.parseInt(st.nextToken());

            if (top.empty()) { // 비어 있다면
                bw.write("0 ");
                idx.push(i);
            }
            else if (top.peek() < val) {
                while (!top.empty() && top.peek() < val) {
                    top.pop();
                    idx.pop();
                }

                if(top.empty()) bw.write("0 ");
                else bw.write(idx.peek() + " ");

            }
            else if (top.peek() > val) {
                bw.write(idx.peek() + " ");
            }

            idx.push(i);
            top.push(val);
        }

        bw.flush();
        bw.close();
    }
}
