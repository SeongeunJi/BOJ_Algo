import java.util.*;
public class Main {
    static int N;
    static long ans;
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        if(N <= 11) ans = N-1;
        else if(N > 1023) ans = -1;
        else {
            for(int i = 0; i < 10; i++) go(i);
            Collections.sort(list);
            ans = list.get(N-1);
        }
        System.out.println(ans);
    }

    static void go(long num) {
        list.add(num);
        for(int i = 0; i < num % 10; i++)
            go(num * 10 + i);
    }
}
