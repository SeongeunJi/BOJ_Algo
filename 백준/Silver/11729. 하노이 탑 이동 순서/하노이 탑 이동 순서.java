import java.io.*;
import java.util.*;
public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int disk = sc.nextInt();
        bw.write((1<<disk) -1 + "\n");
        hanoi(1,3, disk);
        bw.flush();
    }
    static void hanoi(int a, int b, int n) throws IOException {
        if(n == 1) {
            bw.write((a + " " + b) + "\n");
            return;
        }
        hanoi(a, 6-a-b,n-1);
        bw.write((a + " " + b) + "\n");
        hanoi(6-a-b,b,n-1);
    }
}