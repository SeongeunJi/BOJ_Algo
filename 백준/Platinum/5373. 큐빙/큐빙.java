import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static char[] arr = {'w', 'y', 'r', 'o', 'g', 'b'};
    static final int UP = 0, DOWN = 1, FRONT = 2, BACK = 3, LEFT = 4, RIGHT = 5;
    static char[][][] cube = new char[6][3][3];
    static void init() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++)
                Arrays.fill(cube[i][j], arr[i]);
        }
    }

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while(tc --> 0) {
            init();
            int times1 = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(times1 --> 0) {
                String str = st.nextToken();
                char face = str.charAt(0);
                int times2 = str.charAt(1) == '+' ? 1 : 3;
                switch (face) {
                    case 'F' -> rotate(FRONT, times2);
                    case 'B' -> rotate(BACK, times2);
                    case 'U' -> rotate(UP, times2);
                    case 'D' -> rotate(DOWN, times2);
                    case 'L' -> rotate(LEFT, times2);
                    case 'R' -> rotate(RIGHT, times2);
                }
            }
            print();
        }
        System.out.println(sb);
    }

    private static void print() {
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                sb.append(cube[UP][i][j]);
            }
            sb.append("\n");
        }
    }

    static void rotate(int face, int times) {
        rotate_face(face, times);
        char tmp;
        while (times-- > 0) {
            if(face == FRONT){
                for(int i = 0; i < 3; ++i){
                    tmp = cube[UP][2][i];
                    cube[UP][2][i] = cube[LEFT][2 - i][2];
                    cube[LEFT][2 - i][2] = cube[DOWN][0][2 - i];
                    cube[DOWN][0][2 - i] = cube[RIGHT][i][0];
                    cube[RIGHT][i][0] = tmp;
                }
            }
            else if(face == BACK){
                for(int i = 0; i < 3; ++i){
                    tmp = cube[UP][0][i];
                    cube[UP][0][i] = cube[RIGHT][i][2];
                    cube[RIGHT][i][2] = cube[DOWN][2][2 - i];
                    cube[DOWN][2][2 - i] = cube[LEFT][2 - i][0];
                    cube[LEFT][2 - i][0] = tmp;
                }
            }
            else if(face == LEFT){
                for(int i = 0; i < 3; ++i){
                    tmp = cube[UP][i][0];
                    cube[UP][i][0] = cube[BACK][i][0];
                    cube[BACK][i][0]  = cube[DOWN][i][0];
                    cube[DOWN][i][0] = cube[FRONT][i][0];
                    cube[FRONT][i][0] = tmp;
                }
            }
            else if(face == RIGHT){
                for(int i = 0; i < 3; ++i){
                    tmp = cube[UP][i][2];
                    cube[UP][i][2] = cube[FRONT][i][2];
                    cube[FRONT][i][2] = cube[DOWN][i][2];
                    cube[DOWN][i][2] = cube[BACK][i][2];
                    cube[BACK][i][2] = tmp;
                }
            }
            else if(face == UP){
                for(int i = 0; i < 3; ++i){
                    tmp = cube[FRONT][0][i];
                    cube[FRONT][0][i] = cube[RIGHT][0][i];
                    cube[RIGHT][0][i] = cube[BACK][2][2 - i];
                    cube[BACK][2][2 - i] = cube[LEFT][0][i];
                    cube[LEFT][0][i] = tmp;
                }
            }
            else {
                for (int i = 0; i < 3; ++i) {
                    tmp = cube[FRONT][2][i];
                    cube[FRONT][2][i] = cube[LEFT][2][i];
                    cube[LEFT][2][i] = cube[BACK][0][2 - i];
                    cube[BACK][0][2 - i] = cube[RIGHT][2][i];
                    cube[RIGHT][2][i] = tmp;
                }
            }
        }
    }

    static void rotate_face (int face, int times) {
        char[][] tmp = new char[3][3];
        while (times-- > 0) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    tmp[j][2 - i] = cube[face][i][j];
            for(int i = 0; i < 3; i++)
                System.arraycopy(tmp[i], 0, cube[face][i], 0, 3);
        }
    }
}