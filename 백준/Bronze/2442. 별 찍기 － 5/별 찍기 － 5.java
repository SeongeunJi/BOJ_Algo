
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(br.readLine());
        for (int i = 1; i <= len; i++) {
            for(int j = i; j < len; j++)
                bw.write(" ");
            for(int j = 1; j <= i * 2-1; j++)
                bw.write("*");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}