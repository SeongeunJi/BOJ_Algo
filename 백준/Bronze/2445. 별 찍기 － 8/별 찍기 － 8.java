
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(br.readLine());
        for (int i = 1; i <= len; i++) {
            for(int j = 1; j <= i; j++)
                bw.write("*");
            for(int k = 1; k <= (len - i) *2; k++)
                bw.write(" ");
            for(int j = 1; j <= i; j++)
                bw.write("*");
            bw.write("\n");
        }
        for (int i = len - 1; i >= 1; i--) {
            for(int j = 1; j <= i; j++)
                bw.write("*");
            for(int k = 1; k <= (len - i) *2; k++)
                bw.write(" ");
            for(int j = 1; j <= i; j++)
                bw.write("*");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
