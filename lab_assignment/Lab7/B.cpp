#include <bits/stdc++.h>

#define ll long long
using namespace std;
ll a_in[100010];
ll b_in[100010];
ll a_af[100010];
ll b_af[100010];
ll con[100010];
int n;

ll merge(ll arr[], ll connect[], ll l, ll mid, ll r) {
    ll sum = 0;
    int *temp = new int[r - l + 1];
    ll i = l, j = mid + 1, k = 0;
    while (i <= mid && j <= r) {
//        cout <<i <<": " <<arr[i] << endl<<j << ": " << arr[j] << endl;
        if (connect[arr[i]] <= connect[arr[j]]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
            sum +=mid + 1 - i;
        }
    }
    while (i <= mid) {
        temp[k++] = arr[i++];
    }
    while (j <= r) {
        temp[k++] = arr[j++];
    }
    for (int m = 0; m < r - l + 1; ++m) {
        arr[l + m] = temp[m];
    }
    delete[]temp;
    return sum;
}

ll re(ll arr[], ll connect[], ll l, ll r) {
    if (l >= r) {
        return 0;
    } else {
        ll mid = (l + r) / 2;
        ll num1 = re(arr, connect, l, mid);
        ll num2 = re(arr, connect, mid + 1, r);
        ll num3 = merge(arr, connect, l, mid, r);
        return num1 + num2 + num3;
    }

}


int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) {
        scanf("%lld", &a_in[i]);
        a_af[i] = a_in[i];
    }
    for (int i = 0; i < n; ++i) {
        scanf("%lld", &b_in[i]);
        b_af[i] = b_in[i];
    }

    sort(a_in, a_in + n);
    sort(b_in, b_in + n);

    for (int i = 0; i < n; i++) {
        a_af[i] = lower_bound(a_in, a_in + n, a_af[i]) - a_in + 1;
    }
    for (int i = 0; i < n; i++) {
        b_af[i] = lower_bound(b_in, b_in + n, b_af[i]) - b_in + 1;
    }
    for (int i = 0; i < n; ++i) {
        con[a_af[i]] = i + 1;
    }

//

    ll res = re(b_af, con, 0, n - 1);
    cout << res << endl;

    return 0;
}

