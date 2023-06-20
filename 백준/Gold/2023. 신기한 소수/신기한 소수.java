import java.util.*;
public class Main {
    static int N;
    static int[] prime = {1, 2, 3, 5, 7, 9};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i : prime) go(1, i);
        System.out.println(sb);
    }
    static void go(int level, int num) {
        if(!isPrime(num)) return;
        if (level == N) sb.append(num).append("\n");
        for(int i : prime) {
            go(level+1, num * 10 + i);
        }
    }
    static boolean isPrime(int num) {
        if(num < 2) return false;
        for(int i = 2; i <= num / 2; i++)
            if(num % i == 0) return false;
        return true;
    }
}