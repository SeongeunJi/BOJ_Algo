
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[21];
        for(int i = 1; i < 21; i++)
            arr[i] = i;

        for (int i = 1; i <= 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int standard = (from + to)/2;
            for(int j = from, k = to; j <= standard; j++, k--) {
                int tmp = arr[k];
                arr[k] = arr[j];
                arr[j] = tmp;
            }
        }

        for(int i = 1; i <= 20; i++)
            bw.write(arr[i] + " ");
        bw.flush();
        bw.close();
    }
}
