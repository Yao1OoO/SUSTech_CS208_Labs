#include <bits/stdc++.h>

#define ll long long
using namespace std;

struct Node {
public:
    int id;
    ll we;

    Node() {

    }

    Node(int id) {
        this->id = id;
    }

    Node(int id, ll we) {
        this->id = id;
        this->we = we;
    }

    bool operator<(Node x) const {
        return we < x.we;
    }
};

void remove(int next[], int prev[], int i) {
    int n = next[i];
    int p = prev[i];
    next[p] = n;
    prev[n] = p;
    next[i] = -2;
    prev[i] = -2;
}

Node nodes[200010];
int ne_n[200010];
int pr_n[200010];
bool is_vis[200010];

int main() {
    int n, k;
    scanf("%d%d", &n, &k);
    priority_queue<Node> q;
    Node first = nodes[0];
    Node last = nodes[n + 1];
    nodes[0].id = 0;
    nodes[0].we = LONG_LONG_MIN;
    nodes[n + 1].id = n + 1;
    nodes[n + 1].we = LONG_LONG_MIN;
    ne_n[0] = 0;
    pr_n[0] = -1;
    ne_n[n + 1] = n + 2;
    pr_n[n + 1] = n;
    for (int i = 1; i < n + 1; ++i) {
        ll we;
        scanf("%lld", &we);
        nodes[i].id = i;
        nodes[i].we = we;
        pr_n[i] = i - 1;
        ne_n[i] = i + 1;
        is_vis[n] = false;
        q.push(nodes[i]);
    }
    ll ans = 0;
    int cnt = 0;
    while (!q.empty()) {
        Node node = q.top();
//        cout << node.id << " "<< node.we <<" \n";
//        for (int i = 1; i <= n ; ++i) {
//            cout << nodes[i].id << " "<< nodes[i].we << "\n";
//        }
        q.pop();
        if (node.we <= 0) break;
        if (!is_vis[node.id]) {
            ans += node.we;
            cnt++;
            if (cnt == k) break;
            if (pr_n[node.id] == 0 && ne_n[node.id] == n + 1) {
                break;
            } else if (pr_n[node.id] == 0) {
                is_vis[ne_n[node.id]] = true;
                nodes[node.id].we = nodes[ne_n[node.id]].we - node.we;
                remove(ne_n, pr_n, ne_n[node.id]);
                q.push(nodes[node.id]);
            } else if (ne_n[node.id] == n + 1) {
                is_vis[pr_n[node.id]] = true;
                nodes[node.id].we = nodes[pr_n[node.id]].we - node.we;
                remove(ne_n, pr_n, pr_n[node.id]);
                q.push(nodes[node.id]);
            } else {
                is_vis[pr_n[node.id]] = true;
                is_vis[ne_n[node.id]] = true;
                nodes[node.id].we = nodes[pr_n[node.id]].we + nodes[ne_n[node.id]].we - node.we;
                remove(ne_n, pr_n, pr_n[node.id]);
                remove(ne_n, pr_n, ne_n[node.id]);
                q.push(nodes[node.id]);
            }

        }

    }
    printf("%lld", ans);
    return 0;
};