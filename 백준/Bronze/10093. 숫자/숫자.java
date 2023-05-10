
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] arr = new int[2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        long now = System.currentTimeMillis();
        for(int i = 0; i < 2; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int max = Math.max(arr[1], arr[0]);
        int min = arr[0] + arr[1] - max;

        bw.write((max == min ? 0 : max - min -1) + "\n");
        if (max != min) {
            for(int i = min + 1; i < max; i++) {
                bw.write(String.valueOf(i));
                bw.write(" ");
            }
        }
        bw.flush();
        bw.close();
    }
}
