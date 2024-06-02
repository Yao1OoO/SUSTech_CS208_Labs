import java.util.ArrayList;

public class B {
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        ver[] vertex = new ver[n + 1];
        for (int i = 1; i < n + 1; i++) {
            vertex[i] = new ver(i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vertex[u].nb.add(vertex[v]);
            vertex[v].nb.add(vertex[u]);
        }
        dfs(vertex[a],b);
        long ansA= getAns(vertex,b);
        dfs(vertex[b],a);
        long ansB = getAns(vertex,a);
        System.out.println(ansA*ansB);
    }

    public static void dfs(ver node ,int t){
        node.isVis = true;
        for (ver n : node.nb){
            if (!n.isVis && n.id != t) dfs(n,t);
        }
    }

    public static long getAns(ver[] nodes, int t){
        long sum = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (!nodes[i].isVis && i!=t) sum++;
            nodes[i].isVis = false;
        }
        return sum;
    }
}

class ver {
    int id;
    ArrayList<ver> nb = new ArrayList<>();
    boolean isVis = false;

    public ver(int id) {
        this.id = id;
    }
}
