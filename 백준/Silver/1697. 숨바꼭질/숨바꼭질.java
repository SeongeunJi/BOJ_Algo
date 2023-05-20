import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int size = 100000;
        int[] board = new int[size+1];
        int[] dx = {-1, 1};

        Arrays.fill(board, -1);

        Queue<Integer> Q = new ArrayDeque<>();
        Q.add(N); board[N] = 0;
        loop1:
        while (!Q.isEmpty()) {
            int cur = Q.remove();
            for (int i = 0; i < 2; i++) {
                int nx = cur + dx[i];
                if(nx < 0 || nx > size || board[nx] >= 0) continue;
                Q.add(nx);
                board[nx] = board[cur] + 1;
                if(nx == K) break loop1;
            }
            int nx = cur * 2;
            if(nx > size || board[nx] >= 0) continue;
            Q.add(nx);
            board[nx] = board[cur] + 1;
            if(nx == K) break;
        }

        bw.write(String.valueOf(board[K]));
        bw.flush();
        bw.close();
    }
}