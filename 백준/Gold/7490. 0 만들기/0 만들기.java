import java.io.*;
public class Main {
    static int N;
    static int[] numArr;
    static int[] opArr;
    static final int COMBINE = 0, PLUS = 1, MINUS = 2;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while(tc --> 0) {
            N = Integer.parseInt(br.readLine());
            numArr = new int[N];
            opArr = new int[N-1];
            for(int i = 0; i < N; i++) numArr[i] = i+1;
            go(0, 0);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void go(int level, int start) {
        if(level == N-1) {
            cal();
            return;
        }
        for(int i = start; i < N-1; i++) {
            for(int op = 0; op < 3; op++) {
                opArr[level] = op;
                go(level+1, i+1);
            }
        }
    }

    static void cal() {
        int total = numArr[0];
        for(int i = 0; i < N-1; i++) {
            switch(opArr[i]) {
                case PLUS -> total += numArr[i+1];
                case MINUS -> total -= numArr[i+1];
                case COMBINE -> {
                    if(i == 0) total = total * 10 + numArr[i+1];
                    else if (opArr[i-1] == MINUS) {
                        total += numArr[i];
                        total = total - (numArr[i] * 10 + numArr[i+1]);
                    }
                    else if(opArr[i-1] == PLUS){
                        total -= numArr[i];
                        total = total + (numArr[i] * 10 + numArr[i+1]);
                    }
                    else return;
                }
            }
        }
        if(total == 0) {
            int num = 0;
            int op = 0;
            for(int i = 0; i < N*2-1; i++) {
                if(i % 2 == 0) sb.append(numArr[num++]);
                else {
                    switch (opArr[op++]) {
                        case PLUS -> sb.append("+");
                        case MINUS -> sb.append("-");
                        case COMBINE -> sb.append(" ");
                    }
                }
            }
            sb.append("\n");
        }
    }
}