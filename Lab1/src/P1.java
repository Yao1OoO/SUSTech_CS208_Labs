import java.util.*;
public class P1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        String[] num2boy = new String[n];
        String[] num2girl = new String[n];
        HashMap<String, Integer> boy2num = new HashMap<>();
        HashMap<String, Integer> girl2num = new HashMap<>();
        for (int i = 0; i < n; i++) {
            num2boy[i] = in.next();
            boy2num.put(num2boy[i], i);
        }
        for (int i = 0; i < n; i++) {
            num2girl[i] = in.next();
            girl2num.put(num2girl[i], i);
        }
        int[][] boyLove = new int[n][n];
        int[][] girlRank = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boyLove[i][j] = girl2num.get(in.next());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                girlRank[i][boy2num.get(in.next())] = j;
            }
        }

        stableMatch(num2boy, num2girl, boyLove, girlRank, n);
    }

    public static void stableMatch(String[] num2boy, String[] num2girl,
                                   int[][] boyLove, int[][] girlRank, int n) {
        int[] boyDate = new int[n];
        int[] girlDate = new int[n];
        boolean[] girlHave = new boolean[n];
        Queue<Integer> boy = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            boy.add(i);
        }

        while (!boy.isEmpty()) {
            int b = boy.poll();
            int gRank = boyDate[b];
            if (!girlHave[boyLove[b][gRank]]) {
                girlHave[boyLove[b][gRank]] = true;
                girlDate[boyLove[b][gRank]] = b;
            } else if (girlRank[boyLove[b][gRank]][b] < girlRank[boyLove[b][gRank]][girlDate[boyLove[b][gRank]]]) {
                boy.add(girlDate[boyLove[b][gRank]]);
                girlDate[boyLove[b][gRank]] = b;
            } else {
                boy.add(b);
            }
            boyDate[b] += 1;
        }
        QWriter out = new QWriter();
        for (int i = 0; i < n; i++) {
            out.println(num2boy[i] + " " + num2girl[boyLove[i][boyDate[i] - 1]]);
        }
        out.close();
    }
}

