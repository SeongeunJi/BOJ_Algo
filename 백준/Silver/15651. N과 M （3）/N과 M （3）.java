import java.util.*;
public class Main {
    static int N, M;
    static int[] arr = new int[10];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        go(0);
        System.out.print(sb);
    }

    static void go(int level) {
        if (level == M) {
            for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            arr[level] = i;
            go(level + 1);
        }
    }
}