import java.util.Scanner;
public class Main {
    static String pattern = "  *\n * *\n*****";
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        recursion(3, pattern);
    }

    static void recursion(int p, String pattern) {
        if (p == n) {
            printAnswer(pattern);
            return;
        }
        StringBuilder sb = new StringBuilder();
        String[] lines = pattern.split("\n");
        for (int i = 0; i < p; i++) lines[i] = lines[i].trim();
        p *= 2;
        for (int i = 1; i <= p; i++) {
            for(int j = p; j > i; j--) sb.append(" ");
            int idx = 0;
            for (int j = 0; j < i*2-1; j++) {
                if (i <= p / 2) sb.append(lines[i-1].charAt(j));
                else {
                    if(j < lines[(i-1)%(p/2)].length()) sb.append(lines[(i-1)%(p/2)].charAt(j));
                    else if(j < (i*2-1) - lines[(i-1)%(p/2)].length()) sb.append(" ");
                    else sb.append(lines[(i-1)%(p/2)].charAt(idx++));
                }
            }
            sb.append("\n");
        }
        recursion(p, sb.toString());
    }

    private static void printAnswer(String ans) {
        StringBuilder sb = new StringBuilder();
        String[] lines = ans.split("\n");
        for (int i = 0; i < lines.length; i++) {
            int cnt = 0;
            for (int j = 0; j < lines[i].length(); j++) {
                if(lines[i].charAt(j) == ' ') cnt++;
                else {
                    sb.append(lines[i]);
                    break;
                }
            }
            for(int j = 0; j < cnt; j++) sb.append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}