
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> shortMen = new ArrayList<>();
        int sum = 0;
        for(int i = 1; i <= 9; i++) {
            int tmp = Integer.parseInt(br.readLine());
            shortMen.add(tmp);
            sum += tmp;
        }

        Collections.sort(shortMen);

        for (int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(sum - shortMen.get(i) - shortMen.get(j) == 100) {
                    for(int k = 0; k < 9; k++) {
                        if(k == i || k == j ) continue;
                        bw.write(shortMen.get(k) + "\n");
                    }
                    bw.flush();
                    bw.close();
                    System.exit(0);
                }
            }
        }
    }
}
