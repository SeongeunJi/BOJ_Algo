import java.io.*;
import java.util.*;
public class Main {
    static int x, y, k, ans;
    static int[][] laptop;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        laptop = new int[x][y];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[x][y];
            for (int j = 0; j < x; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < y; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            for (int degree = 0; degree <= 270; degree+=90) {
                if(!attachable(sticker))
                    sticker = rotate_clockwise(sticker);
                else break;
            }
        }
        System.out.println(ans);
    }

    static boolean attachable(int[][] sticker) {
        int row = sticker.length;
        int col = sticker[0].length;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(i + row > x || j + col > y) break;
                if(attachable(i, j ,row, col, sticker)) {
                    attach(sticker, row, col, i, j);
                    return true;
                }
            }
        }
        return false;
    }
    static boolean attachable(int x, int y, int r, int c, int[][] sticker) {
        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {
                if (laptop[i][j] == 1 && sticker[i - x][j - y] == 1)
                    return false;
            }
        }
        return true;
    }
    private static void attach(int[][] sticker, int row, int col, int i, int j) {
        for (int k = i; k < i + row; k++) {
            for(int l = j; l < j + col; l++) {
                if(sticker[k- i][l- j] == 1) {
                    laptop[k][l] = 1;
                    ans++;
                }
            }
        }
    }

    static int[][] rotate_clockwise(int[][] sticker) {
        int row = sticker.length;
        int col = sticker[0].length;
        int[][] rotatedSticker = new int[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rotatedSticker[j][row - 1 - i] = sticker[i][j];
            }
        }
        return rotatedSticker;
    }
}