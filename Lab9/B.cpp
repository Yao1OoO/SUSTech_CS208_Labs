#include <bits/stdc++.h>

#define ll long long
using namespace std;

int n, k;
ll arr[510];
ll pre_xor[510];
ll dp[510][510];

ll xor_ij(int i, int j) {
    return pre_xor[j] ^ pre_xor[i - 1];
}
void solve() {
    for (int i = 1; i <= n; ++i) {
        dp[i][1] = xor_ij(1, i);
    }
    for (int i = 1; i <= n; ++i) {
        for (int j = 2; j <= min(i,k); ++j) {
            ll max_xor = LONG_LONG_MIN;
            for (int l = j; l <= i; ++l) {
                max_xor = max(max_xor, dp[l - 1][j - 1] + xor_ij(l, i));
            }
            dp[i][j] = max_xor;
        }
    }
}

int main() {
    scanf("%d%d", &n, &k);
    for (int i = 1; i <= n; ++i) {
        scanf("%lld", &arr[i]);
    }
    pre_xor[1] = arr[1];
    for (int i = 2; i <= n; ++i) {
        pre_xor[i] = pre_xor[i - 1] ^ arr[i];
    }
    solve();
    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= i; ++j) {
            printf("%lld ",dp[i][j]);
        }
        cout << endl;
    }
    printf("%lld",dp[n][k]);
    return 0;
}