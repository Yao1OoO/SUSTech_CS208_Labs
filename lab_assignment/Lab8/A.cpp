#include <bits/stdc++.h>

#define ll long long
using namespace std;

struct Segment_tree {
    ll l, r, sum, max_sum, max_pre, max_suf;
};

ll arr[200010];
Segment_tree st[800010];
ll n;
ll m;

void update(ll node) {
    st[node].sum = st[node << 1].sum + st[(node << 1) | 1].sum;

    st[node].max_sum = max(st[node << 1].max_sum, st[(node << 1) | 1].max_sum);
    st[node].max_sum = max(st[node].max_sum, st[node << 1].max_suf + st[(node << 1) | 1].max_pre);

    st[node].max_pre = max(st[node << 1].max_pre, st[node << 1].sum + st[(node << 1) | 1].max_pre);

    st[node].max_suf = max(st[(node << 1) | 1].max_suf, st[(node << 1) | 1].sum + st[node << 1].max_suf);

}

void build(ll node, ll l, ll r) {
    st[node].l = l;
    st[node].r = r;
    if (l == r) {
        st[node].sum = arr[l];
        st[node].max_sum = arr[l];
        st[node].max_pre = arr[l];
        st[node].max_suf = arr[l];
        return;
    }
    ll mid = (l + r) >> 1;
    build(node << 1, l, mid);
    build((node << 1) | 1, mid + 1, r);
    update(node);
}

Segment_tree query(ll node, ll l, ll r) {
    if (l <= st[node].l && r >= st[node].r) {
        return st[node];
    }
    ll mid = (st[node].l + st[node].r) >> 1;
    if (l <= mid && r > mid) {
        Segment_tree new_tree;
        Segment_tree lt = query(node << 1, l, r);
        Segment_tree rt = query((node << 1) | 1, l, r);

        new_tree.sum = lt.sum + rt.sum;

        new_tree.max_sum = max(lt.max_sum, rt.max_sum);
        ll max1 = lt.max_suf + rt.max_pre;
        new_tree.max_sum = max(new_tree.max_sum, max1);

        new_tree.max_pre = max(lt.max_pre, lt.sum + rt.max_pre);

        new_tree.max_suf = max(rt.max_suf, rt.sum + lt.max_suf);

        return new_tree;
    } else if (l <= mid) {
        return query(node << 1, l, r);
    } else {
        return query((node << 1) | 1, l, r);

    }
}

int main() {
    scanf("%lld%lld", &n, &m);
    for (int i = 1; i <= n; ++i) {
        scanf("%lld", &arr[i]);
    }
    build(1, 1, n);
    for (int i = 0; i < m; ++i) {
        ll l ,r;
        scanf("%lld%lld",&l,&r);
        Segment_tree res = query(1,l,r);
        printf("%lld\n" , res.max_sum);
    }
    return 0;
}