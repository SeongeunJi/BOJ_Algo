import java.io.*;
import java.util.*;
public class Main {
    static int L, C;
    static char[] arr;
    static char[] chArr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[L];
        chArr = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; st.hasMoreTokens(); i++) chArr[i] = st.nextToken().charAt(0);
        Arrays.sort(chArr);
        go(0, 0);
        System.out.println(sb);
    }

    static void go(int level, int start) {
        if (level == L) {
            if(!check()) return;
            for(char ch : arr) sb.append(ch);
            sb.append("\n");
            return;
        }
        for (int i = start; i < C; i++) {
            arr[level] = chArr[i];
            go(level + 1, i + 1);
        }
    }

    static boolean check() {
        int cnt1 = 0;
        int cnt2 = 0;
        for (char ch : arr) {
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') cnt1++;
            else cnt2++;
        }
        return cnt1 >= 1 && cnt2 >= 2;
    }
}