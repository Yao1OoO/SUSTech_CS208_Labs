import java.util.Scanner;

public class q2 {
    private static final int[] pow = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192,
            16384, 32768, 65536,131072,262144,524288,1048576,2097152,4194304,8388608};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int ans = solve(l, r);
            System.out.println(ans);
        }
    }

    public static int solve(int l, int r) {
        return f(r) - f(l - 1);
    }

    private static int f(int x) {
        if (x == 0 || x == 1) return x;
        int n = getN(x);
        return x + 1 - pow[n] + f(pow[n + 1] - x - 1);
    }

    private static int getN(int x) {
        int n = 1;
        while (pow[n++] <= x);
        return n - 2;
    }
}
