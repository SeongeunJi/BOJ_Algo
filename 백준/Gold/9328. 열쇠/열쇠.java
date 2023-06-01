import java.io.*;
import java.util.*;
public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] board;
    static boolean[][] vis;
    static boolean[] keys;
    static Map<Character,List<Pair>> unvisitedMap = new HashMap<>();
    static Queue<Pair> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while(tc --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board = new char[x][y];
            vis = new boolean[x][y];

            for (int i = 0; i < x; i++) {
                String input = br.readLine();
                for(int j = 0; j < y; j++)
                    board[i][j] = input.charAt(j);
            }

            keys = new boolean[26];
            String iGotTheKeys = br.readLine();
            if (!iGotTheKeys.equals("0")) {
                for(char ch : iGotTheKeys.toCharArray()) 
                    keys[ch-'a'] = true;
            }

            int docs = 0;
            for (int i = 0; i < 26; i++)
                unvisitedMap.put((char)('A' + i), new ArrayList<>());

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if(!(i == 0 || i == x-1) && !(j == 0 || j == y-1)) continue;
                    if(vis[i][j] || board[i][j] == '*') continue;
                    // 시작점이 문이라면
                    if ('A' <= board[i][j] && board[i][j] <= 'Z' && !keys[board[i][j] - 'A']) {
                        unvisitedMap.get(board[i][j]).add(new Pair(i, j));
                        vis[i][j] = true;
                        continue;
                    }
                    // 시작점이 열쇠라면
                    if ('a' <= board[i][j] && board[i][j] <= 'z' && !keys[board[i][j] - 'a']) {
                        keys[board[i][j] - 'a'] = true;
                        unvisitedDoor(board[i][j]);
                    }
                    if(board[i][j] == '$') docs++;
                    q.add(new Pair(i, j));
                    vis[i][j] = true;
                    while (!q.isEmpty()) {
                        Pair cur = q.remove();
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];
                            if(nx < 0 || nx >= x || ny < 0 || ny >= y) continue;
                            if(board[nx][ny] == '*' || vis[nx][ny]) continue;
                            char ne = board[nx][ny];
                            if ('A' <= ne && ne <= 'Z') {
                                if (keys[ne - 'A']) {
                                    board[nx][ny] = '.';
                                    vis[nx][ny] = true;
                                    q.add(new Pair(nx, ny));
                                }
                                else unvisitedMap.get(ne).add(new Pair(nx, ny));
                                continue;
                            }
                            else if ('a' <= ne && ne <= 'z') {
                                keys[ne - 'a'] = true;
                                unvisitedDoor(ne);
                            }
                            else if (ne == '$') docs++;

                            vis[nx][ny] = true;
                            q.add(new Pair(nx, ny));
                        }
                    }
                }
            }
            for (int i = 0; i < 26; i++) {
                List<Pair> pairs = unvisitedMap.get((char) ('A' + i));
                if(!pairs.isEmpty() && keys[i]) docs += pairs.size();
            }
            bw.write(docs + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static void unvisitedDoor(char ne) {
        List<Pair> pairs = unvisitedMap.get((char)(ne - 32));
        if (!pairs.isEmpty()) {
            for(Pair pair : pairs) {
                vis[pair.x][pair.y] = true;
                q.add(pair);
            }
            pairs.clear();
        }
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}