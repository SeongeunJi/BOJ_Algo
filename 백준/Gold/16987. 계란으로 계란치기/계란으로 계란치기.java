import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static boolean[] isBroken;
    static List<Egg> eggs = new LinkedList<>();
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isBroken = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs.add(new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        go(0);
        System.out.println(ans);
    }

    static void go(int start) {
        if (start == N) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if(isBroken[i]) cnt++;
            }
            ans = Math.max(cnt, ans);
            return;
        }
        if (isBroken[start]) {
            go(start+1);
        }
        else {
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                if(i == start || isBroken[i]) continue;
                attack(start, i);
                flag = true;
                go(start+1);
                attackCancel(start, i);
            }
            if(!flag) go(start+1);
        }
    }

    static void attack(int a, int b) {
        Egg egg1 = eggs.get(a);
        Egg egg2 = eggs.get(b);
        egg1.HP -= egg2.ATK;
        egg2.HP -= egg1.ATK;
        if(egg1.HP <= 0) isBroken[a] = true;
        if(egg2.HP <= 0) isBroken[b] = true;
    }

    static void attackCancel(int a, int b) {
        Egg egg1 = eggs.get(a);
        Egg egg2 = eggs.get(b);
        egg1.HP += egg2.ATK;
        egg2.HP += egg1.ATK;
        if(egg1.HP > 0) isBroken[a] = false;
        if(egg2.HP > 0) isBroken[b] = false;
    }
    static public class Egg {
        int HP, ATK;
        public Egg(int HP, int ATK) {
            this.HP = HP;
            this.ATK = ATK;
        }
    }
}