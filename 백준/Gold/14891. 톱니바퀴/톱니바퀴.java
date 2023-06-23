import java.io.*;
import java.util.*;
public class Main {
    static int[][] gears = new int[4][8];
    static int[] tilted = new int[8];
    static final int CLOCKWISE = 1, CONTROL_CLOCKWISE = -1;
    static int[] dirArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = str.charAt(j) - '0';
            }
        }
        int N = Integer.parseInt(br.readLine());
        while(N --> 0) {
            st = new StringTokenizer(br.readLine());
            int gearNo = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            dirArr = new int[4];
            dirArr[gearNo] = dir;
            sideEffects(gearNo, dir);
            for(int i = 0; i < 4; i++)
                if(dirArr[i] != 0) rotate_gears(i, dirArr[i]);
        }
        getAns();
    }
    static void sideEffects(int gearNo, int dir) {
        for(int i = 0; i < 2; i++) {
            int curDir = dir;
            if(i == 0) {
                for(int cur = gearNo; cur > 0; cur--) {
                    if(gears[cur][6] == gears[cur-1][2]) break;
                    int rotateDir = curDir == CLOCKWISE ? CONTROL_CLOCKWISE : CLOCKWISE;
                    dirArr[cur-1] = rotateDir;
                    curDir = rotateDir;
                }
            }
            else {
                for(int cur = gearNo; cur < 3; cur++) {
                    if(gears[cur][2] == gears[cur+1][6]) break;
                    int rotateDir = curDir == CLOCKWISE ? CONTROL_CLOCKWISE : CLOCKWISE;
                    dirArr[cur+1] = rotateDir;
                    curDir = rotateDir;
                }
            }
        }
    }
    static void rotate_gears(int gearNo, int dir) {
        switch (dir) {
            case CONTROL_CLOCKWISE -> {
                int first = gears[gearNo][0];
                for (int i = 0; i < 8; i++) {
                    if(i == 7) tilted[i] = first;
                    else tilted[i] = gears[gearNo][i+1];
                }
            }
            case CLOCKWISE -> {
                int end = gears[gearNo][7];
                for(int i = 0; i < 8; i++) {
                    if(i == 0) tilted[i] = end;
                    else tilted[i] = gears[gearNo][i-1];
                }
            }
        }
        System.arraycopy(tilted, 0, gears[gearNo], 0, 8);
    }

    static void getAns() {
        int ans = 0;
        for (int i = 0; i < 4; i++)
            if(gears[i][0] == 1) ans += 1<<i;
        System.out.println(ans);
    }
}