import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();

        for (int i = 0; i < m; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] stones = new int[n];
            for (int j = 0; j < n; j++) {
                stones[j] = in.nextInt();
            }
            System.out.println(findMax(stones, k, n));
            System.out.println(findMin(stones, k, n));
        }


    }

    public static int findMin(int stone[], int k, int n) {
        int result = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(stone[i]);
        }
        int num = (n - 1) % (k - 1);
        if (num == 0) {
            while (pq.size() > 1) {
                int sum = 0;
                for (int i = 0; i < k; i++) {
                    sum += pq.poll();
                }
                result += sum;
                pq.add(sum);
            }
        } else {
            int Sum1 = 0;
            for (int i = 0; i < num + 1; i++) {
                Sum1 += pq.poll();
            }
            pq.add(Sum1);
            result += Sum1;
            while (pq.size() > 1) {
                int sum = 0;
                for (int i = 0; i < k; i++) {
                    sum += pq.poll();
                }
                result += sum;
                pq.add(sum);
            }
        }

        return result;
    }


    public static int findMax(int stone[], int k, int n) {
        int result = 0;
        Comparator<Integer> maxHeapComparator = (o1, o2) -> o2 - o1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(maxHeapComparator);
        for (int i = 0; i < n; i++) {
            pq.add(stone[i]);
        }
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int newStone = first + second;
            result += newStone;
            pq.add(newStone);
        }
        return result;
    }


}
