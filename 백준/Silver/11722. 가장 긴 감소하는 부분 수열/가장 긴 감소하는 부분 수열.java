import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 수열의 크기
        int[] numArr = new int[N + 1]; // 수열
        int[] D = new int[N + 1]; // 각 원소의 가장 긴 감소 수열 길이

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++)
            numArr[i] = Integer.parseInt(st.nextToken());
        br.close();

        int max = 1;
        for(int i = 1; i <= N; i++) {
            D[i] = 1; // 각 원소의 최솟값 초기화
            for(int j = 1; j < i; j++) {
                if (numArr[i] < numArr[j] && D[i] <= D[j]) {
                    D[i] = D[j] + 1;
                    if(max < D[i]) max = D[i];
                }
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
