import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] numArr = new int[2000001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 0; st.hasMoreTokens(); i++) {
            int input = Integer.parseInt(st.nextToken());
            if(input >= sum || (sum % 2 ==0) && sum / 2 == input) continue;

            numArr[input] = 1;
            int dif = sum - input;
            if(numArr[dif] == 1) cnt++;
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
    }
}
