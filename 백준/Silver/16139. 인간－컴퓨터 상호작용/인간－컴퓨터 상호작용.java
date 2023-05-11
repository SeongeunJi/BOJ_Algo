import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받은 문자열을 문자 배열
        String source = br.readLine().trim();
        // 누적합을 저장할 배열
        int[] prefixSum = new int[source.length()+1];
        // 질문 횟수 > 즉, 반복 횟수
        int Q = Integer.parseInt(br.readLine());
        // 출력값을 저장할 변수
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < source.length(); i++)
            prefixSum[i+1] = prefixSum[i] + source.charAt(i);

        for(int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int pattern = st.nextToken().charAt(0), cnt = 0;
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());

            for(int j = from; j <= to; j++) {
                if (prefixSum[j+1] - prefixSum[j] == pattern)
                    cnt++;
            }
            sb.append(cnt + "\n");
        }
        System.out.println(sb);
    }
}