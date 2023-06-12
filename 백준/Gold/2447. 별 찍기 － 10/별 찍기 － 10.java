import java.util.Scanner;
public class Main {
    static int n;
    static String pattern3 = "***\n* *\n***";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        recursion(3, pattern3);

    }

    private static void recursion(int p, String pattern) {
        if(p == n) {
            System.out.println(pattern);
            return;
        }
        p *= 3;
        String[] lines = pattern.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                if((i >= p/3 && i < p/3*2) && (j >= p/3 && j < p/3*2)) sb.append(" ");
                else sb.append(lines[i % (p/3)].charAt(j%(p/3)));
            }
            sb.append("\n");
        }
        recursion(p, sb.toString());
    }
}