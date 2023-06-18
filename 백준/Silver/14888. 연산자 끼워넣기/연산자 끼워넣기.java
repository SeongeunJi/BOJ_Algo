import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[] numArr;
    static int[] op = new int[4];
    static int[] ans = {Integer.MIN_VALUE, Integer.MAX_VALUE};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) numArr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) op[i] = Integer.parseInt(st.nextToken());
        go(0,numArr[0]);
        System.out.println(ans[0] + "\n" + ans[1]);
    }

    static void go(int level, int result) {
        if (level == N - 1) {
            ans[0] = Math.max(ans[0], result);
            ans[1] = Math.min(ans[1], result);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if(op[i] == 0) continue;
            op[i]--;
            int tmp = result;
            if(i == 0) result += numArr[level+1];
            else if(i == 1) result -= numArr[level+1];
            else if(i == 2) result *= numArr[level+1];
            else result /= numArr[level+1];
            go(level+1, result);
            op[i]++;
            result = tmp;
        }
    }
}