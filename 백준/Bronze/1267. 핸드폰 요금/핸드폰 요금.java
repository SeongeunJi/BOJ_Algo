
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        int[] arr = new int[tc];

        int Y = 0, M = 0;
        int result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < tc; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            Y += (arr[i] / 30 + 1) * 10;
            M += (arr[i] / 60 + 1) * 15;
        }

        if(Y > M)
            bw.write("M " + M);
        else if(M > Y)
            bw.write("Y " + Y);
        else
            bw.write("Y M " + Y);
        bw.flush();
        bw.close();
    }
}
