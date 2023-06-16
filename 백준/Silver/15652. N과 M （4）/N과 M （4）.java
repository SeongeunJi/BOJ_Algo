import java.util.*;
public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static int[] arr = new int[10];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        go(0, 1);
        System.out.println(sb);
    }

    static void go(int level, int start) {
        if (level == M) {
            for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = start; i <= N; i++) {
            arr[level] = i;
            go(level+1, i);
        }
    }
}