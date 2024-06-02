#include <bits/stdc++.h>

using namespace std;


struct Node {
public:
    int id;
    vector<int> nb;
    int in =0;
    bool out = false;
};


int main() {
    int t;
    scanf("%d", &t);
    for (int i = 0; i < t; ++i) {
        int n, m;
        vector<int> res;
        scanf("%d%d", &n, &m);
        Node nodes[n+1];
        for (int j = 1; j < n+1; ++j) {
            nodes[j] = Node();
            nodes[j].id = j;
        }
        for (int j = 0; j < m; ++j) {
            int u,v;
            scanf("%d%d", &u, &v);
            nodes[v].nb.push_back(u);
            nodes[u].in = nodes[u].in+1;
        }
        priority_queue<int> queue1;
        for (int j = 1; j < n+1; ++j) {
            if(nodes[j].in == 0){
                queue1.push(j);
            }
        }
        while (!queue1.empty()){
            int a = queue1.top();
            nodes[a].out = true;
            res.push_back(a);
            queue1.pop();
            for (int b : nodes[a].nb) {
                if (!nodes[b].out){
                    nodes[b].in--;
                    if (nodes[b].in == 0){
                        queue1.push(b);
                    }
                }
            }
        }

        if (res.size() == n){
            for (int j = res.size()-1; j >=0; --j) {
                printf("%d ",res[j]);
            }
        } else{
            printf("-1");
        }
        printf("\n");
    }

    return 0;
}
