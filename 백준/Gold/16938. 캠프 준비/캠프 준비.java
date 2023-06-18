import java.io.*;
import java.util.*;
public class Main {
    static int N, L, R, X;
    static int[] difficulty;
    static int[] arr;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        difficulty = new int[N];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) difficulty[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(difficulty);
        go(0, 0);
        System.out.println(ans);
    }
    static void go(int level, int start) {
        if(level >= 2 && able(level)) ans++;
        for(int i = start; i < N; i++) {
            arr[level] = i;
            go(level+1, i+1);
            arr[level] = 0;
        }
    }

    static boolean able(int level) {
        if(Math.abs(difficulty[arr[0]] - difficulty[arr[level-1]]) < X) return false;
        int sum = 0;
        for(int i = 0; i < level; i++) sum += difficulty[arr[i]];
        return L <= sum && sum <= R;
    }
}