import java.io.*;
import java.util.*;
public class Main {
    static Pair myHome;
    static Pair festival;
    static Set<Pair> conveniences = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        while(tc --> 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            myHome = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            while(n --> 0) {
                st = new StringTokenizer(br.readLine());
                int fir = Integer.parseInt(st.nextToken());
                int two = Integer.parseInt(st.nextToken());
                conveniences.add(new Pair(fir, two));
            }
            st = new StringTokenizer(br.readLine());
            festival = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            bw.write(bfs() ? "happy\n" : "sad\n");
            conveniences.clear();
        }
        bw.flush();
        bw.close();
    }

    static boolean bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.add(myHome);
        while (!q.isEmpty()) {
            Pair cur = q.remove();
            if(taxicabGeometry(cur, festival) <= 1000) return true;
            List<Pair> convenienceLoc = getConvenienceLoc(cur);
            if(convenienceLoc.size() == 0) continue;
            q.addAll(convenienceLoc);
            convenienceLoc.forEach(conveniences::remove);
        }
        return false;
    }
    static List<Pair> getConvenienceLoc(Pair cur) {
        List<Pair> list = new ArrayList<>();
        for(Pair convenience : conveniences) {
            if(taxicabGeometry(cur, convenience) <= 1000) {
                list.add(convenience);
            }
        }
        return list;
    }

    private static int taxicabGeometry(Pair cur, Pair convenience) {
        return Math.abs(cur.x - convenience.x) + Math.abs(cur.y - convenience.y);
    }

    static class Pair implements Comparable<Pair> {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Pair pair) {
            return this.x == pair.x ? this.y - pair.y : this.x - pair.x;
        }
    }
}
