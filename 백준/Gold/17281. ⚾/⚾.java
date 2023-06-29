import java.io.*;
import java.util.*;
public class Main {
    static int N, ans;
    static int[] orders = new int[9];
    static boolean[] vis = new boolean[9];
    static List<List<Integer>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new ArrayList<>());
            while(st.hasMoreTokens())
                list.get(i).add(Integer.parseInt(st.nextToken()));
        }
        orders[3] = 0;
        vis[0] = true;
        go(0);
        System.out.println(ans);
    }
    static void go(int level) {
        if(level == 9) {
            ans = Math.max(ans, getAns());
            return;
        }
        if(level == 3) go(level + 1);
        else {
            for(int i = 1; i < 9; i++) {
                if(vis[i]) continue;
                vis[i] = true;
                orders[level] = i;
                go(level + 1);
                vis[i] = false;
            }
        }
    }

    static int getAns() {
        int score = 0, last = 0;
        for(int inning = 0; inning < N; inning++) {
            int outCnt = 0;
            boolean finish = false;
            int[] state = new int[5];
            while (!finish) {
                for (int i = last % 9; i < 9; i++, last++) {
                    int result = list.get(inning).get(orders[i]);
                    if(result == 0) {
                        if(++outCnt == 3) {
                            finish = true;
                            break;
                        }
                    }
                    else simulation(state, result);
                }
            }
            score += state[4];
            last++;
        }
        return score;
    }
    static void simulation(int[] state, int result) {
        for(int i = 3; i >= 1; i--) {
            if(state[i] == 0) continue;
            int next = i + result;
            if(next > 3) state[4]++;
            else state[next] = 1;
            state[i] = 0;
        }
        state[result]++;
    }
}
