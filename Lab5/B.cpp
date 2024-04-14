#include <bits/stdc++.h>

#define ll long long
using namespace std;
ll floors[400020];
ll floors_af[400020];
ll floors_in[400020];
ll floors_co[400020];
ll up_cnt[400020];
ll down_cnt[400020];
ll connect[400020];
bool is_up[400020];
ll n;
ll m;
ll k;

int main() {
    scanf("%lld%lld%lld", &n, &m, &k);
    int j = 0, p = 0;
    for (int i = 0; i < n; ++i) {
        scanf("%lld%lld", &floors_af[2 * i], &floors_af[2 * i + 1]);
        floors_in[2 * i] = floors_af[2 * i];
        floors_in[2 * i + 1] = floors_af[2 * i + 1];
        if (floors_af[2 * i] < floors_af[2 * i + 1]) {
            is_up[i] = true;
            floors_af[2 * i + 1] = floors_af[2 * i + 1] - 1;
        } else {
            is_up[i] = false;
            floors_af[2 * i + 1] = floors_af[2 * i + 1] + 1;
        }

        floors[2 * i] = floors_af[2 * i];
        floors[2 * i + 1] = floors_af[2 * i + 1];

        floors_co[2 * i] = floors_af[2 * i];
        floors_co[2 * i + 1] = floors_af[2 * i + 1];
    }

    sort(floors, floors + 2 * n);
    ll x = unique(floors, floors + 2 * n) - floors;
    ll max_num = LLONG_MIN;
    ll max_id = -1;
    for (int i = 0; i < 2 * n; i++) {
        floors_af[i] = lower_bound(floors, floors + x, floors_af[i]) - floors;
        connect[floors_af[i]] = floors_co[i];
        if (floors_af[i] > max_num) {
            max_num = floors_af[i];
            max_id = i;
        }
    }
    for (int i = 0; i < n; ++i) {
        ll a = floors_af[2 * i];
        ll b = floors_af[2 * i + 1];
        if (is_up[i]) {
            up_cnt[a] += 1;
            up_cnt[b + 1] -= 1;
        } else {
            down_cnt[b] += 1;
            down_cnt[a + 1] -= 1;
        }
    }
    ll up_max = up_cnt[0];
    ll sum = up_cnt[0];
    for (int i = 1; i < n; ++i) {
        sum += up_cnt[i];
        up_max = sum > up_max ? sum : up_max;
    }
    ll down_max = down_cnt[0];
    sum = down_cnt[0];
    for (int i = 1; i < n; ++i) {
        sum += down_cnt[i];
        down_max = sum > down_max ? sum : down_max;
    }
    ll run_num = max(up_max, down_max) % m == 0 ? max(up_max, down_max) / m : max(up_max, down_max) / m + 1;
    ll ans = 0;
    ll s = max_num + 1;
    ll t = max_num + 1;
    ll current_up = -up_cnt[max_num + 2];
    ll current_down = -down_cnt[max_num + 2];

    for (int i = 1; i <= run_num; ++i) {
        ll time_up = 0;
        ll time_down = 0;
        while (current_up <= (i - 1) * m) {
            current_up = current_up - up_cnt[s];
            s -= 1;
            if (s == -1) break;
        }

        time_up = connect[s] + 1;
        while (current_down <= (i - 1) * m) {
            current_down = current_down - down_cnt[t];
            t -= 1;
            if (t == -1) break;
        }
        time_down = connect[t];
        ans += ((max(time_up, time_down) - 1) * 2);
    }

    printf("%lld", ans);
    return 0;
}
