import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt(); // 테스트 개수
        double percent = 0;
        int N = 0;


        for(int i = 0; i < C; i++) {
            N = sc.nextInt(); // 학생 수
            int[] score = new int[N];
            double avg = 0;
            int sum = 0;
            int count = 0;
            for(int j = 0; j < N; j++) {
                score[j]= sc.nextInt();
                sum += score[j];
                avg = sum / (double)N;
            }
            for(int j = 0; j < score.length; j++) {
                if (score[j] > avg)
                    count++;
            }
            percent = count / (double)N * 100;
            System.out.printf("%.3f%c%n",percent,'%');
        }
    }
}
