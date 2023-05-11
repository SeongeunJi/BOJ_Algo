
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int result = 1;
        for (int i = 0; i < 3; i++) 
            result *= Integer.parseInt(br.readLine());

        int[] resultArr = new int[10];
        while(result > 0) {
            resultArr[result%10]++;
            result/=10;
        }

        for(int i : resultArr)
            bw.write(i + "\n");
        bw.flush();
        bw.close();
    }
}
