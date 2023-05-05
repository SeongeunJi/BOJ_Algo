import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long A = sc.nextInt(), B = sc.nextInt();
        long prime = go(A,B);
        System.out.println(A * B / prime);
    }

    static long go(long a, long b) {
        if(b == 0) return a;
        return go(b, a % b);
    }
}
