#include <bits/stdc++.h>

using namespace std;

long long max_num = 0;
long long max_id = -1;
bool isfind = false;
struct Node {
public:
    int id;
    vector<long long > nb;
    vector<long long > we;
    int in = 0;
    bool out = false;
    bool isVisit = false;
    bool onPath = false;
};

void dfs_end_point(Node nodes[], long long cnt, Node &node) {
    node.isVisit = true;
    for (int i = 0; i < node.nb.size(); ++i) {
        if (!nodes[node.nb[i]].isVisit) {
            if (cnt + node.we[i] > max_num) {
                max_num = cnt + node.we[i];
                max_id = node.nb[i];
            }
            dfs_end_point(nodes, cnt + node.we[i], nodes[node.nb[i]]);
        }
    }
}

void dfs_end_point_b(int dis_on_path[], Node nodes[], long long cnt, Node &node) {
    node.isVisit = true;
    for (int i = 0; i < node.nb.size(); ++i) {
        if (!nodes[node.nb[i]].isVisit) {
            dis_on_path[node.nb[i]] = dis_on_path[node.id] + node.we[i];
            if (cnt + node.we[i] > max_num) {
                max_num = cnt + node.we[i];
                max_id = node.nb[i];
            }
            dfs_end_point_b(dis_on_path, nodes, cnt + node.we[i], nodes[node.nb[i]]);
        }
    }
}

void dfs_end(Node nodes[]) {
    long long cnt = 0;
    dfs_end_point(nodes, cnt, nodes[1]);
}

void dfs_d(int b, stack<int> &dia, Node nodes[], Node &node) {
    node.isVisit = true;
    if (node.id == b) {
        isfind = true;
        return;
    }
    for (int i = 0; i < node.nb.size(); ++i) {
        if (!nodes[node.nb[i]].isVisit && !isfind) {
            dia.push(node.nb[i]);
            dfs_d(b, dia, nodes, nodes[node.nb[i]]);
            if (!isfind) {
                dia.pop();
            }
        }
    }
}

void dfs_dis_to_path(int dis_out_path[], Node nodes[], Node &node, long long cnt, int t) {
    node.isVisit = true;
    for (int i = 0; i < node.nb.size(); ++i) {
        if (!nodes[node.nb[i]].isVisit && !nodes[node.nb[i]].onPath) {
            dis_out_path[t] = dis_out_path[t] < cnt + node.we[i] ? cnt + node.we[i] : dis_out_path[t];
            dfs_dis_to_path(dis_out_path, nodes, nodes[node.nb[i]], cnt + node.we[i], t);
        }
    }
}

//long long get_res(int dis_out_path[],int dis_on_path[],int path[],int len,long long s,int a,int b){
//
//    return min;
//}

int main() {
    int n;
    long long s;
    scanf("%d", &n);
    scanf("%lld", &s);
    Node nodes[n + 1];
    for (int i = 1; i < n + 1; ++i) {
        nodes[i] = Node();
        nodes[i].id = i;
    }
    for (int i = 0; i < n - 1; ++i) {
        int u, v, w;
        scanf("%d%d%d", &u, &v, &w);
        nodes[u].nb.push_back(v);
        nodes[u].we.push_back(w);
        nodes[v].nb.push_back(u);
        nodes[v].we.push_back(w);
    }

    dfs_end(nodes);
    int a = max_id;
    max_id = -1;
    max_num = 0;

    long long cnt = 0;
    for (int i = 1; i < n + 1; ++i) {
        nodes[i].isVisit = false;
    }
    int dis_on_path[n + 1];
    dis_on_path[a] = 0;
    dfs_end_point_b(dis_on_path, nodes, cnt, nodes[a]);
    int b = max_id;
//    cout << a << " " << b << "\n";
//    for (int i = 0; i < n + 1; ++i) {
//        cout << i << " " << dis_on_path[i] << "\n";
//    }
    //the path between a and b is d
    for (int i = 1; i < n + 1; ++i) {
        nodes[i].isVisit = false;
    }
    stack<int> dia;
    dfs_d(b, dia, nodes, nodes[a]);
    nodes[a].onPath = true;
    int path[dia.size()+1];
    path[0] = a;
    int j = dia.size();
    while (!dia.empty()) {
        nodes[dia.top()].onPath = true;
        path[j] = dia.top();
        j--;
        dia.pop();
    }
    for (int i = 1; i < n + 1; ++i) {
        nodes[i].isVisit = false;
    }
    int dis_out_path[n + 1];
    long long max_dis = 0;
    for (int i = 1; i < n + 1; ++i) {
        dis_out_path[i] = 0;
        if (nodes[i].onPath) {
            dfs_dis_to_path(dis_out_path, nodes, nodes[i],0,i);
            max_dis = dis_out_path[i] > max_dis ? dis_out_path[i] : max_dis;
        }
    }
//    cout << "_____________________ \n";
//    for (int i = 1; i < n + 1; ++i) {
//        cout << dis_out_path[i] << " \n";
//    }

    if (s > dis_on_path[b]) {
        printf("%lld", max_dis);
    } else{
        long queue[100010];
        int first = 0;
        int last = 0;
        int head = 0;
        int teal = 0;
        long long sum = 0;
        long long head_len = dis_on_path[b];
        long long teal_len = 0;
        long long min = LLONG_MAX;
        int len = sizeof (path)/sizeof (path[0]);
        while (head < len){
            if (sum <=s){
                head+=1;
                if (head == len) break;
                sum = dis_on_path[path[head]] - dis_on_path[path[teal]];
                head_len = dis_on_path[b] - dis_on_path[path[head]];
                if(dis_out_path[path[head]]!=0){
                    if (last == 0){
                        queue[last++] = dis_out_path[path[head]];
                    } else{
                        if (dis_out_path[path[head]] >= queue[last-1]){
                            while (queue[last -1] < dis_out_path[path[head]]){
                                queue[last--] = 0;
                            }
                            queue[last++] = dis_out_path[path[head]];
                        }
                    }
                }
            } else{
                teal+=1;
                sum = dis_on_path[path[head]] - dis_on_path[path[teal]];
                teal_len = dis_on_path[path[teal]];
                if (dis_out_path[path[teal-1]]!=0){
                    if (queue[first]==dis_out_path[path[teal-1]]){
                        queue[first++] = 0;
                    }
                }
            }
            if(sum<=s && head<len){
                long long max = head_len > teal_len? head_len:teal_len;
                max = max > queue[first] ? max:queue[first];
                min = max < min ? max: min;
            }
        }
        printf("%lld",min);
    }
    return 0;
}



