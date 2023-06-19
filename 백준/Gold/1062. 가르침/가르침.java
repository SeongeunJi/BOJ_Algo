import java.io.*;
import java.util.*;
public class Main {
    static int N, K, ans;
    static String[] input;
    static boolean[] vis = new boolean[26];
    static char[] init = {'a', 'c', 'i', 'n', 't'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(K < 5) ans = 0;
        else {
            input = new String[N];
            for(int i = 0; i < N; i++) input[i] = br.readLine();
            for(int i : init) vis[i-'a'] = true;
            go(5, 0);
        }
        System.out.println(ans);
    }
    static void go(int level, int start) {
        if(level == K) {
            ans = Math.max(getAns(), ans);
            return;
        }
        for(int i = start; i < 26; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            go(level+1, i+1);
            vis[i] = false;
        }
    }
    static int getAns() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int size = input[i].length();
            boolean check = true;
            for(int j = 0; j < size; j++) {
                if(!vis[input[i].charAt(j) - 'a']) {
                    check = false;
                    break;
                };
            }
            if(check) cnt++;
        }
        return cnt;
    }
}