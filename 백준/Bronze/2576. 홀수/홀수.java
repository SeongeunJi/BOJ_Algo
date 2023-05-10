
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int sum = 0;
        int min = 101;
        for(int i = 1; i <= 7; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num % 2 != 0) {
                sum += num;
                min = Math.min(min, num);
            }
        }

        bw.write(String.valueOf(sum == 0 ? -1 : sum + "\n" + min));
        bw.flush();
        bw.close();
    }
}
