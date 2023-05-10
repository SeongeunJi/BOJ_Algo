import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] a = new int[26];
        String str = br.readLine();

        for(char ch : str.toCharArray())
            a[ch-97]++;

        for(int tmp : a)
            bw.write(tmp + " ");

        bw.flush();
        bw.close();
    }
}
