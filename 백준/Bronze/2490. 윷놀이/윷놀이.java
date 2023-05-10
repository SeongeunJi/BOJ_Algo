import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[] arr = new int[3];
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                if (st.nextToken().equals("0"))
                    arr[i]++;
            }
            String result = switch (arr[i]) {
                case 1 -> "A";
                case 2 -> "B";
                case 3 -> "C";
                case 4 -> "D";
                default -> "E";
            };
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }
}
