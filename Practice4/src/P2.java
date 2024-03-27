import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] s = new int[n][3];
        String[] name = new String[n];
        for (int i = 0; i < s.length; i++) {
            name[i] = in.next();
            s[i][0] = in.nextInt();
            s[i][1] = in.nextInt();
            s[i][2] = i;
        }
        ArrayList<Integer> res = intervalSchedule(s);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(name[res.get(i)]);
        }
    }

    public static ArrayList<Integer> intervalSchedule(int[][] arr) {
        ArrayList<Integer> res = new ArrayList<>();

        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        res.add(arr[0][2]);
        int end = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            int start = arr[i][0] ;
            if (start >= end) {
                res.add(arr[i][2]);
                end = arr[i][1];
            }
        }
        return res;
    }
}
