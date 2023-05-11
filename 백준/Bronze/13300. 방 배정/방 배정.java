
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] stdArr = new int[2][7];

        String[] str = br.readLine().split(" ");
        int stds = Integer.parseInt(str[0]);
        int roomMax = Integer.parseInt(str[1]);
        while (stds --> 0) {
            str = br.readLine().split(" ");
            stdArr[Integer.parseInt(str[0])][Integer.parseInt(str[1])]++;
        }

        int result = 0;
        for (int i = 1; i <= 6; i++) {
            for (int j = 0; j <= 1; j++) {
                result += (stdArr[j][i] / roomMax);
                if(stdArr[j][i] % roomMax != 0) result++;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
