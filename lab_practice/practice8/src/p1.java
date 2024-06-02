import java.util.Random;
import java.util.Scanner;

public class p1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < n; i++) {
            compareRes(m);
        }

    }
    public static void compareRes(int m){
        Point[] points = randomNum(m);

        long start = System.currentTimeMillis();
        long res1 = BF.Bf(points);
        long end = System.currentTimeMillis();
        System.out.println("BF use: "+ (end-start) +"ms");
        start = System.currentTimeMillis();
        long res2 = ClosestPair.closestPair(points);
        end = System.currentTimeMillis();
        System.out.println("divide and conquer use: "+ (end -start) +"ms");
        if (res1 == res2){
            System.out.println("same");
        }else {
            for (int i = 0; i < m; i++) {
                System.out.println(points[i].x + " "+ points[i].y);
            }
        }
    }

    public static Point[] randomNum(int n ){
        Random random = new Random();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(100000);
            int y = random.nextInt(100000);
            points[i] = new Point(x,y);
        }
        return points;
    }
}
