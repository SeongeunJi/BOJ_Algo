import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(), x = sc.nextInt();
        int[] numArr = new int[N];

        for(int i = 0; i < N; i++) {
            numArr[i] = sc.nextInt();
        }

        for(int i = 0; i < N; i++) {
            if(numArr[i] < x)
                System.out.print(numArr[i] + " ");
        }
    }
}