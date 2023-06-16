import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int[] arr = new int[10];
    static StringBuilder sb = new StringBuilder();
    static Set<String> lhs = new LinkedHashSet<>();
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
        Collections.sort(list);
        go(0);
        for(String str : lhs) sb.append(str).append("\n");
        System.out.println(sb);
    }

    static void go(int level) {
        if (level == M) {
            for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
            lhs.add(sb.toString());
            sb = new StringBuilder();
            return;
        }
        for (int i = 0; i < N; i++) {
            arr[level] = list.get(i);
            go(level + 1);
        }
    }
}
