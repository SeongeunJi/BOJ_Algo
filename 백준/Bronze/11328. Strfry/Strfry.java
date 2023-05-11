import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            int[] arr = new int[26];
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str1 = st.nextToken();
            String str2 = st.nextToken();

            for(char ch : str1.toCharArray())
                arr[ch-'a']++;
            for(char ch : str2.toCharArray())
                arr[ch-'a']--;

            boolean check = Arrays.stream(arr).allMatch(E -> E == 0);
            if(check) bw.write("Possible\n");
            if(!check) bw.write("Impossible\n");
        }
        bw.flush();
        bw.close();
    }
}
