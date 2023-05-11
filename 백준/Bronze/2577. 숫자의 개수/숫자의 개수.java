
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int result = 1;
        for (int i = 0; i < 3; i++) {
            result *= Integer.parseInt(br.readLine());
        }

        char[] sourceArr = String.valueOf(result).toCharArray();
        int[] resultArr = new int[10];
        for(char ch : sourceArr)
            resultArr[ch-'0']++;

        for(int i : resultArr)
            bw.write(i + "\n");
        bw.flush();
        bw.close();
    }
}
