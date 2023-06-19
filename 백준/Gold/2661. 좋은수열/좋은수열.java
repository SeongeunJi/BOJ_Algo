import java.util.Scanner;
public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        go("");
    }
    static void go(String str) {
        if(str.length() == N) {
            System.out.println(str);
            System.exit(0);
        }
        for(int i = 1; i <= 3; i++) {
            if(possible(str + i)) go(str + i);
        }
    }
    static boolean possible(String str) {
        for(int i = 1; i <= str.length() / 2; i++) {
            String fir = str.substring(str.length() - i * 2, str.length() - i);
            String two = str.substring(str.length() - i);
            if(fir.equals(two)) return false;
        }
        return true;
    }
}
