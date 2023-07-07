import java.io.*;
import java.util.*;
public class Main {
    static int X, Y, M;
    static Shark[][] board;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    static List<Shark> sharks = new LinkedList<>();
    static List<Shark> fished = new ArrayList<>();
    static Queue<Shark> eliminated = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Shark[X+1][Y+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            board[x][y] = new Shark(x, y, s, d, z);
            sharks.add(board[x][y]);
        }
        fishing();
    }
    static void fishing() {
        for(int y = 1; y <= Y; y++) {
            int x = 0;
            while(++x <= X) {
                if(board[x][y] != null) {
                    fished.add(board[x][y]);
                    sharks.remove(board[x][y]);
                    board[x][y] = null;
                    break;
                }
            }
            for(Shark shark : sharks) {
                if(board[shark.x][shark.y] == shark)
                    board[shark.x][shark.y] = null;
                switch (shark.d) {
                    case 1, 2 -> {
                        int mv = shark.s % (2*(X-1));
                        while(mv --> 0) {
                            shark.x += dx[shark.d];
                            shark.y += dy[shark.d];
                            if(shark.x < 1 || shark.y < 1 || shark.x > X || shark.y > Y) {
                                shark.d = 3 - shark.d;
                                shark.x += 2 * dx[shark.d];
                                shark.y += 2 * dy[shark.d];
                            }
                        }
                    }
                    case 3, 4 -> {
                        int mv = shark.s % (2*(Y-1));
                        while(mv --> 0) {
                            shark.x += dx[shark.d];
                            shark.y += dy[shark.d];
                            if(shark.x < 1 || shark.y < 1 || shark.x > X || shark.y > Y) {
                                shark.d = 7 - shark.d;
                                shark.x += 2 * dx[shark.d];
                                shark.y += 2 * dy[shark.d];
                            }
                        }
                    }
                }

                shark.moveCnt++;
                if(existSameSpace(shark)) {
                    if(board[shark.x][shark.y].z > shark.z) {
                        eliminated.add(shark);
                    } else {
                        eliminated.add(board[shark.x][shark.y]);
                        board[shark.x][shark.y] = shark;
                    }
                }
                else board[shark.x][shark.y] = shark;
            }
            while(!eliminated.isEmpty())
                sharks.remove(eliminated.remove());
        }
        System.out.println(fished.stream().mapToInt(shark -> shark.z).sum());
    }

    private static boolean existSameSpace(Shark shark) {
        return board[shark.x][shark.y] != null && board[shark.x][shark.y].moveCnt == shark.moveCnt;
    }

    static class Shark {
        int x, y, s, d, z, moveCnt;
        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }
        public String toString() {
            return z + "size";
        }
    }
}