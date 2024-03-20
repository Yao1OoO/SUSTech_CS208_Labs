import java.util.*;

public class P1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int b = in.nextInt();
            int a = in.nextInt();
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
            inDegree[a]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);
            for (int adj : graph.getOrDefault(current, Collections.emptyList())) {
                inDegree[adj]--;
                if (inDegree[adj] == 0) {
                    queue.offer(adj);
                }
            }
        }

        if (order.size() < n) {
            System.out.println("-1");
        } else {
            for (int task : order) {
                System.out.print(task + " ");
            }
        }
    }
}