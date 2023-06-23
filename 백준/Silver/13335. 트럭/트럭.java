import java.io.*;
import java.util.*;
public class Main {
    static int n, w, l, ans;
    static Queue<Integer> q = new LinkedList<>();
    static int[] bridge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        bridge = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            q.add(Integer.parseInt(st.nextToken()));
        do {
            int tmp = calculate();
            if (tmp <= l) {
                tmp -= bridge[w - 1];
                moveTrucks();
                if (!q.isEmpty() && tmp + q.peek() <= l)
                    bridge[0] = q.remove();
            }
            ans++;
        } while (!isEmpty());
        System.out.println(ans);
    }
    static void moveTrucks() {
        for(int i = w-1; i > 0; i--)
            bridge[i] = bridge[i-1];
        bridge[0] = 0;
    }
    static boolean isEmpty(){
        for(int i = 0; i < w; ++i)
            if(bridge[i] != 0) return false;
        return true;
    }
    static int calculate(){
        int sum = 0;
        for(int i = 0; i<w; ++i)
            sum+=bridge[i];
        return sum;
    }
}