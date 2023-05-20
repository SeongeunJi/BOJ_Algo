
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] board = new int[100001];
        Arrays.fill(board, -1);

        Queue<Integer> Q = new ArrayDeque<>();
        Q.add(N); board[N] = 0;
        while (board[K] == -1) {
            int curX = Q.remove();
            for (int nx : new int[]{curX+1, curX-1, curX*2}) {
                if(nx < 0 || nx > 100000 || board[nx] >= 0) continue;
                Q.add(nx);
                board[nx] = board[curX] + 1;
            }
        }
        bw.write(String.valueOf(board[K]));
        bw.flush();
        bw.close();
    }
}