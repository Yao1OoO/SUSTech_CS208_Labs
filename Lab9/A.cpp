#include <bits/stdc++.h>

#define ll long long
using namespace std;

ll t, n, m;
ll dp[1010][1010];

ll solve() {
    dp[0][0] = 1;
    for (int i = 1; i < m; ++i) {
        dp[0][i] = 0;
    }
    for (int i = 1; i <= m; ++i) {
        for (int j = 0; j < n; ++j) {
            dp[i][j] = (dp[i - 1][(j + 1 + n) % n] + dp[i - 1][(j - 1 + n) % n]) % 1000000007;
        }
    }
    return dp[m][0];
}

int main() {
    scanf("%lld", &t);
    for (int i = 0; i < t; ++i) {
        scanf("%lld%lld", &n, &m);
        ll res = solve();
        printf("%lld\n", res);
    }
}