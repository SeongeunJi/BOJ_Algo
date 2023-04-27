import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N], result = new int[N];
        Map<Integer, Integer> map = new HashMap<>(N);
        Stack<Integer> indexStack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; st.hasMoreTokens(); i++) {
            int num = Integer.parseInt(st.nextToken());
            numArr[i] = num;

            if(map.containsKey(num)) // key를 포함하고 있다면 value + 1
                map.put(num, map.get(num) + 1);

            else { // key가 등록되어 있지 않다면 1을 저장
                map.put(num, 1);
            }
        }

        indexStack.push(0);
        for(int i = 0; i < N; i++) {

            while(!indexStack.empty() && map.get(numArr[indexStack.peek()]) < map.get(numArr[i]))
                result[indexStack.pop()] = numArr[i];

            indexStack.push(i);
        }

        indexStack.stream().forEach(i -> {
            result[i] = -1;
        });

        for(int i : result)
            sb.append(i).append(" ");

        System.out.println(sb);
    }
}
