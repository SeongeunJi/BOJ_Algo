import java.io.*;
import java.util.*;
public class Main {
    static int N, M, K;
    static int[][] arr;
    static int[][] board;
    static PriorityQueue<Tree> treeQ = new PriorityQueue<>();
    static Queue<Tree> emptyQ = new LinkedList<>();
    static Queue<Tree> deadTreeQ = new LinkedList<>();
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], 5);
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            treeQ.add(new Tree(x, y, age));
        }
        simulation();
        System.out.println(treeQ.size());
    }

    static void simulation() {
        while(K --> 0) {
            for(int season = 0; season < 4; season++) {
                switch (season) {
                    case 0 -> spring();
                    case 1 -> summer();
                    case 2 -> fall();
                    case 3 -> winter();
                }
            }
            if(treeQ.size() == 0) return;
        }
    }

    static void spring() {
        while(!treeQ.isEmpty()) {
            Tree tree = treeQ.remove();
            if(board[tree.x][tree.y] >= tree.age) {
                board[tree.x][tree.y] -= tree.age;
                tree.age++;
                emptyQ.add(tree);
            }
            else deadTreeQ.add(tree);
        }
    }

    static void summer() {
        while(!deadTreeQ.isEmpty()) {
            Tree dead = deadTreeQ.remove();
            board[dead.x][dead.y] += dead.age / 2;
        }
    }

    static void fall() {
        while(!emptyQ.isEmpty()) {
            Tree tree = emptyQ.remove();
            treeQ.add(tree);
            if(tree.age % 5 == 0) {
                for(int dir = 0; dir < 8; dir++) {
                    int nx = tree.x + dx[dir];
                    int ny = tree.y + dy[dir];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    treeQ.add(new Tree(nx, ny, 1));
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] += arr[i][j];
    }

    static class Tree implements Comparable<Tree> {
        int x, y, age;
        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return age - o.age;
        }
    }
}