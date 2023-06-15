import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(st.hasMoreTokens()) sb.append(st.nextToken());
        String answer = "mixed";
        if(sb.toString().equals("12345678")) answer = "ascending";
        else if(sb.toString().equals("87654321")) answer = "descending";
        System.out.println(answer);
    }
}