
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int input = Integer.parseInt(br.readLine());
        int[] sticker = new int[10];

        while (input > 0) {
            sticker[input%10]++;
            input /= 10;
        }

        int max = (sticker[6] + sticker[9]) / 2;
        if((sticker[6] + sticker[9]) %2 != 0) max += 1;

        for (int i = 0; i < 10; i++) {
            if(i == 6 || i == 9) continue;
            max = Math.max(max, sticker[i]);
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
