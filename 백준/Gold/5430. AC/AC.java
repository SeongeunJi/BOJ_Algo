import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Deque<Integer> deque;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int i = 0; i < T; i++) {
            String command = br.readLine(); // 명령어
            br.readLine(); // 필요 없지만 입력 형식을 위해서

            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");

            deque = new ArrayDeque<>();

            while(st.hasMoreTokens())
                deque.add(Integer.parseInt(st.nextToken()));

            direction(command);
        }

        System.out.println(sb);
    }

    public static void direction(String command) {
        boolean descending = false;

        for (char ch : command.toCharArray()) {
            if (ch == 'R') {
                descending = !descending;
                continue;
            }

            if (ch == 'D') {
                if (deque.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }

                if (descending)
                    deque.pollLast();
                else
                    deque.pollFirst();
            }

        }
        plzBeAnswer(descending);
    }

    private static void plzBeAnswer(boolean descending) {
        sb.append("[");

        if (deque.size() > 0) {

            if(!descending) {
                while(!deque.isEmpty()) {
                    sb.append(deque.pollFirst() +",");
                }
            }
            else {
                while (!deque.isEmpty()) {
                    sb.append(deque.pollLast() + ",");
                }
            }
        }

        if(sb.charAt(sb.length()-1) == ',')
            sb.deleteCharAt(sb.lastIndexOf(","));

        sb.append("]\n");
    }
}


class Q543 {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Deque<Integer> deque;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int i = 0; i < T; i++) {
            String command = br.readLine(); // 명령어
            br.readLine(); // 필요 없지만 입력 형식을 위해서

            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");

            deque = new ArrayDeque<>();

            while(st.hasMoreTokens())
                deque.add(Integer.parseInt(st.nextToken()));

            direction(command);
        }

        System.out.println(sb);
    }

    public static void direction(String command) {
        boolean descending = false;

        for (char ch : command.toCharArray()) {
            if (ch == 'R') {
                descending = !descending;
                continue;
            }

            if (ch == 'D') {
                if (deque.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }

                if (descending)
                    deque.pollLast();
                else
                    deque.pollFirst();
            }

        }
        plzBeAnswer(descending);
    }

    private static void plzBeAnswer(boolean descending) {
        sb.append("[");

        Iterator<Integer> it = (descending)
                             ? deque.descendingIterator()
                             : deque.iterator();


        while(it.hasNext())
            sb.append(it.next() + ",");


        if(sb.charAt(sb.length()-1) == ',')
            sb.deleteCharAt(sb.lastIndexOf(","));

        sb.append("]\n");
    }
}