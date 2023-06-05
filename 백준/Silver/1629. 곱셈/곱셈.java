import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        System.out.println(pow(A,B,C));
    }
    static long pow(long A, long B, long C) {
        if(B <= 1) return A % C;
        long val = pow(A, B/2, C);
        val = val * val % C;
        if(B % 2 == 0) return val;
        return val * A % C;
    }
}