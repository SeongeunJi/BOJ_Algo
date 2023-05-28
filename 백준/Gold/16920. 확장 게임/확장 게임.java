import java.util.*;
import java.io.*;
class Main {
    static int[][] board;
    static boolean[][] vis;
    static int[] pi;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int x, y, p;
    static int[] result;
    static final int MAX = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        board = new int[x][y];
        vis = new boolean[x][y];
        pi = new int[p+1];
        result = new int[p+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=p; i++) {
            pi[i] = Integer.parseInt(st.nextToken());
            if(pi[i]>MAX) pi[i] = MAX;
        }
        LinkedList<Pair>[] list = new LinkedList[p+1];
        for(int i=1; i<=p; i++) list[i] = new LinkedList<>();
        for(int i = 0; i< x; i++) {
            String in = br.readLine();
            for(int j = 0; j< y; j++) {
                char ch = in.charAt(j);
                if(ch == '#') board[i][j] = -1;
                else if(ch == '.') board[i][j] = 0;
                else {
                    board[i][j] = ch-'0';
                    list[board[i][j]].add(new Pair(i,j));
                    result[board[i][j]]++;
                }
            }
        }
        boolean finish = false;
        while(!finish) {
            for(int i=1; i<=p; i++) {
                int cnt = 0;
                while(!list[i].isEmpty()) {
                    int ls = list[i].size();
                    while(ls-- > 0) {
                        Pair cur = list[i].poll();
                        for(int dir=0; dir<4; dir++) {
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];
                            if(OOB(nx, ny) || board[nx][ny] != 0) continue;
                            result[i]++;
                            list[i].add(new Pair(nx,ny));
                            board[nx][ny] = i;
                        }
                    }
                    if(++cnt == pi[i]) break;
                }
            }
            finish = true;
            for(int i=1; i<=p; i++) {
                if (list[i].size() > 0) {
                    finish = false;
                    break;
                }
            }
        }
        
        for(int i=1; i<=p; i++)
            bw.write(result[i] + " ");
        bw.flush();
        bw.close();
    }
    private static boolean OOB(int ni, int nj) {
        return ni < 0 || ni >= x || nj < 0 || nj >= y;
    }
    static class Pair{
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}