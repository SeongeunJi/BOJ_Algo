import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static List<Integer> list = new ArrayList<>();
    static int M = 6;
    static int N;
    static int[] arr = new int[M];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;
            while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
            go(0, 0);
            sb.append("\n");
            list.clear();
        }
        System.out.println(sb);
    }

    static void go(int level, int start) {
        if (level == M) {
            for(int i : arr) sb.append(i).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = start; i < N; i++) {
            arr[level] = list.get(i);
            go(level + 1, i + 1);
        }
    }
}