import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static int[] arr = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
        Collections.sort(list);
        go(0);
        System.out.print(sb);
    }

    static void go(int level) {
        if (level == M) {
            for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            arr[level] = list.get(i);
            go(level+1);
        }
    }
}