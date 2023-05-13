import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Pair> tower = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        tower.push(new Pair(100000001,0));
        for (int i = 1; i <= N; i++) {
            int val = Integer.parseInt(st.nextToken());
            while(tower.peek().height < val)
                tower.pop();
            bw.write(tower.peek().idx + " ");
            tower.push(new Pair(val, i));
        }
        bw.flush();
        bw.close();
    }

    private static class Pair {
        final int height;
        final int idx;
        public Pair(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
}

