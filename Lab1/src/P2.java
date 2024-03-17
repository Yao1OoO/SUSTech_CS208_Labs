import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(f(n));
    }

    public static long f(int n){
        long[] feb = new long[n+1];
        re(0,n,feb);
        return feb[n];
    }
    static void re(int i,int n, long[] feb){
        if (i == n+1) return;
        if (i == 0) {
            feb[0] = 1;
            re(1,n,feb);
        }
        if (i == 1){
            feb[1] = 1;
            re(2,n,feb);
        }
        if (i > 1){
            feb[i] = feb[i-1] + feb[i-2];
            re(i+1,n,feb);
        }
    }
}
