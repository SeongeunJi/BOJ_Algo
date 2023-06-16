import java.io.*;
import java.util.*;
public class Main {
    static int N, S;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
        Collections.sort(list);
        if(S < 0) Collections.reverse(list);
        go(0, 0, 0);
        System.out.println(ans);
    }

    static void go(int sum, int start, int level) {
        if(level == N) return;
        for (int i = start; i < N; i++) {
            int val = list.get(i);
            sum += val;
            if(sum == S) ans++;
            go(sum, i+1, level+1);
            sum -= val;
        }
    }
}