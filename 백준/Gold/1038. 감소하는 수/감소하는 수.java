import java.util.*;
public class Main {
    static List<Long> list = new ArrayList<>();
    static long ans;
    static int n;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        if(n <= 10) ans = n;
        else if(n > 1022) ans = -1;
        else {
            for(int i = 0; i < 10; i++) go(1, i);
            Collections.sort(list);
            ans = list.get(n);
        }
        System.out.println(ans);
    }
    public static void go(int level, long num) {
        list.add(num);
        for(int i = 0; i < num % 10; i++)
            go(level+1, num*10 + i);
    }
}