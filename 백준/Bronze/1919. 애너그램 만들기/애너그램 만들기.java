
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str1 = br.readLine(), str2 = br.readLine();
        int[] arr1 = new int[26], arr2 = new int[26];

        for(char ch : str1.toCharArray())
            arr1[ch-'a']++;
        for(char ch : str2.toCharArray())
            arr2[ch-'a']++;

        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if(arr1[i] == arr2[i]) continue;
            cnt += Math.abs(arr1[i] - arr2[i]);
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
    }
}
