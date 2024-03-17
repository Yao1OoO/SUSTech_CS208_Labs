import java.util.ArrayList;

public class A {

    public static void main(String[] args) {
        Node start = new Node(".", 1, null);
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        FileSys sys = new FileSys();
        sys.start = start;
        for (int i = 0; i < n; i++) {
            String[] com = in.nextLine().split(" ");
            if (com[0].equals("echo")) {
                sys.echo(com);
            } else if (com[0].equals("mkdir")) {
                sys.mkdir(com);
            } else if (com[0].equals("rm") && !com[1].equals("-rf")) {
                sys.rm(com);
            } else if (com[0].equals("rm")) {
                sys.rm_rf(com);
            } else if (com[0].equals("mv")) {
                sys.mv(com);
            }
        }
        for (int i = 0; i < m; i++) {
            String[] com = in.nextLine().split(" ");
            if (com[0].equals("cat")) {
                sys.cat(com, out);
            } else {
                sys.find(com, out);
            }
        }
        out.close();

    }

}

class FileSys {
    public Node start;

    private Node findPath(String[] path) {
        Node node = this.start;
        for (String s : path) {
            if (s.matches("\\.+")) {
                for (int j = 0; j < s.length() - 1; j++) {
                    node = node.fa;
                }
            } else {
                for (Node n : node.ch) {
                    if (n.name.equals(s)) {
                        node = n;
                        break;
                    }
                }
            }
        }

        return node;
    }

    private Node creatPath(String[] path, int type) {
        Node node = this.start;
        for (int i = 0; i < path.length - 1; i++) {
            if (path[i].matches("\\.+")) {
                for (int j = 0; j < path[i].length() - 1; j++) {
                    node = node.fa;
                }
            } else {
                for (Node n : node.ch) {
                    if (n.name.equals(path[i])) {
                        node = n;
                        break;
                    }
                }
            }
        }
        Node newNode = new Node(path[path.length - 1], type, node);
        node.ch.add(newNode);
        return newNode;
    }

    public void echo(String[] com) {
        Node node = this.creatPath(com[com.length - 1].split("/"), 0);
        node.content = com.length == 3 ? "" : com[1];
    }

    public void mkdir(String[] com) {
        this.creatPath(com[1].split("/"), 1);
    }

    public void rm(String[] com) {
        Node node = this.findPath(com[1].split("/"));
        node.fa.ch.remove(node);
        node.fa = null;
    }

    public void rm_rf(String[] com) {
        Node node = this.findPath(com[2].split("/"));
        node.fa.ch.remove(node);
        node.fa = null;
    }

    public void mv(String[] com) {
        Node src = this.findPath(com[1].split("/"));
        Node dst = this.findPath(com[2].split("/"));
        src.fa.ch.remove(src);
        src.fa = dst;
        dst.ch.add(src);
    }

    public void cat(String[] com, QWriter out) {
        Node node = findPath(com[1].split("/"));
        out.println(node.content);
    }

    public void find(String[] com, QWriter out) {
        boolean haveName = false;
        int name = -1;
        boolean haveType = false;
        int type = -1;
        boolean havePath = false;
        int path = -1;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < com.length; i++) {
            if (com[i].equals("-name")) {
                haveName = true;
                name = i;
            } else if (com[i].equals("-type")) {
                haveType = true;
                type = i;
                break;
            }
        }
        if (com.length == 1) {
            list = findAll(this.start, ".");
        } else if (com.length > 1 && !com[1].equals("-name") && !com[1].equals("-type")) {
            Node node = this.findPath(com[1].split("/"));
            if (com[1].endsWith("/"))com[1] = com[1].substring(0,com[1].length()-1);
            if (!haveName && !haveType) {
                list = findAll(node, com[1]);
            } else if (haveName && !haveType) {
                list = findName(node,com[1],com[name+1]);
            } else if (!haveName && haveType) {
                list = findType(node,com[1],com[type+1]);
            }else {
                list =findNameType(node,com[1],com[name+1],com[type+1]);
            }
        } else {
            if (!haveName && !haveType) {
                list = findAll(this.start,"./");
            } else if (haveName && !haveType) {
                list = findName(this.start,"./",com[name+1]);
            } else if (!haveName && haveType) {
                list = findType(this.start,"./",com[type+1]);
            }else {
                list =findNameType(this.start,"./",com[name+1],com[type+1]);
            }
        }
        out.println(list.size());
        for (String str : list) {
            out.println(str);
        }
    }


    public static ArrayList<String> findAll(Node node, String path) {
        ArrayList<String> list = new ArrayList<>();
        list.add(path);
        for (int i = 0; i < node.ch.size(); i++) {
            dfs(path, node.ch.get(i), list);
        }
        return list;
    }

    public static ArrayList<String> findName(Node node, String path, String name) {
        ArrayList<String> list = new ArrayList<>();
        if (node.name.equals(name)) list.add(path);
        for (int i = 0; i < node.ch.size(); i++) {
            dfs(path, node.ch.get(i), list, name);
        }
        return list;
    }

    public static ArrayList<String> findType(Node node, String path, String type) {
        int t = 0;
        if (type.equals("d")){
            t = 1;
        }
        ArrayList<String> list = new ArrayList<>();
        if (node.type == t) list.add(path);
        for (int i = 0; i < node.ch.size(); i++) {
            dfs(path, node.ch.get(i), list, t);
        }
        return list;
    }

    public static ArrayList<String> findNameType(Node node, String path, String name, String type) {
        int t = 0;
        if (type.equals("d")){
            t = 1;
        }
        ArrayList<String> list = new ArrayList<>();
        if (node.name.equals(name) && node.type == t) list.add(path);
        for (int i = 0; i < node.ch.size(); i++) {
            dfs(path, node.ch.get(i), list,name, t);
        }
        return list;
    }

    private static void dfs(String path, Node node, ArrayList<String> list) {
        String string = path + "/" + node.name;
        list.add(string);
        for (Node ch : node.ch) {
            dfs(string, ch, list);
        }
    }

    private static void dfs(String path, Node node, ArrayList<String> list, String name) {
        String string = path + "/" + node.name;
        if (node.name.equals(name)) list.add(string);
        for (Node ch : node.ch) {
            dfs(string, ch, list, name);
        }
    }

    private static void dfs(String path, Node node, ArrayList<String> list, int type) {
        String string = path + "/" + node.name;
        if (node.type == type) list.add(string);
        for (Node ch : node.ch) {
            dfs(string, ch, list, type);
        }
    }

    private static void dfs(String path, Node node, ArrayList<String> list, String name, int type) {
        String string = path + "/" + node.name;
        if (node.name.equals(name) && node.type == type) list.add(string);
        for (Node ch : node.ch) {
            dfs(string, ch, list, name,type);
        }
    }
}

class Node {
    String name;
    int type; //0 = file 1 = folder
    String content;
    Node fa;
    ArrayList<Node> ch = new ArrayList<>();

    public Node(String name, int type, String content, Node fa) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.fa = fa;
    }

    public Node(String name, int type, Node fa) {
        this.name = name;
        this.type = type;
        this.fa = fa;
    }
}
