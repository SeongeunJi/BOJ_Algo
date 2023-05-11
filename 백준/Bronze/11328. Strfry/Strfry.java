
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<Character> chList = new LinkedList<>();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] chars = st.nextToken().toCharArray();
            char[] chars2 = st.nextToken().toCharArray();

            if (chars.length != chars2.length) {
                bw.write("Impossible\n");
                continue;
            }

            for(char ch : chars)
                chList.add(ch);
            boolean check = true;

            for (char ch : chars2) {
                if (chList.contains(ch)) {
                    Character tmp = ch;
                    chList.remove(tmp);
                } else {
                    bw.write("Impossible\n");
                    check = false;
                    break;
                }
            }
            if(check) bw.write("Possible\n");
            chList.clear();
        }
        bw.flush();
        bw.close();
    }
}
