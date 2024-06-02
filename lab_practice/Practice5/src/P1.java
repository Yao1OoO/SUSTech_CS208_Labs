import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] s = new int[n][2];
        for (int i = 0; i < s.length; i++) {
            s[i][0] = in.nextInt();
            s[i][1] = in.nextInt();
        }
        System.out.println(intervalCover(s));
    }

    public static int intervalCover(int[][] arr) {
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int end = arr[0][1];
        int cnt  = 1;
        for (int i = 1; i < arr.length; i++) {
            int start = arr[i][0] ;
            if (start > end) {
                cnt++;
                end = arr[i][1];
            }
        }
        return cnt;
    }
}
