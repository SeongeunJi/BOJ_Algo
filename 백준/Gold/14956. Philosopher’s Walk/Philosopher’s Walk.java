import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int side = sc.nextInt(), walk = sc.nextInt();
        Pair ans = recursion(walk, side);
        System.out.println(ans.x + " " + ans.y);
    }

    static Pair recursion(int walk, int side) {
        if(side == 2) {
            switch (walk) {
                case 1: return new Pair(1, 1);
                case 2: return new Pair(1, 2);
                case 3: return new Pair(2, 2);
                case 4: return new Pair(2, 1);
            }
        }
        int half = side / 2;
        int section = half * half;
        if (walk <= section) {
            Pair pair = recursion(walk, half);
            return new Pair(pair.y, pair.x);
        }
        else if (walk <= 2 * section) {
            Pair pair = recursion(walk - section, half);
            return new Pair(pair.x , pair.y + half);
        }
        else if (walk <= 3 * section) {
            Pair pair = recursion(walk - section * 2, half);
            return new Pair(pair.x + half, pair.y + half);
        }
        else {
           Pair pair = recursion(walk - section * 3, half);
           return new Pair(2 * half - pair.y + 1, half - pair.x + 1);
        }
    }
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}