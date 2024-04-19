#include <bits/stdc++.h>

#define ll long long
using namespace std;

ll array1[200020];
ll sum[200020];
ll n;
ll L;
ll R;


ll merge(ll arr[], ll l, ll mid, ll r) {
    ll sum1 = 0;
    ll up = mid + 1;
    ll low = mid + 1;
//    for (int i = l; i <= r; ++i) {
//        cout << arr[i] <<" ";
//    }
//    cout << endl;
    ll p = l;
    while (p <= mid) {
        while (up <= r && arr[up] - arr[p] <= R) {
            up++;
        }
        while (low <= r && arr[low] - arr[p] < L) {
            low++;
        }
//        cout<< up << " " << low<<" "  <<up-low << endl;
        sum1 += (up - low);
        p++;
    }

    ll *temp = new ll[r - l + 1];
    ll i = l, j = mid + 1, k = 0;
    while (i <= mid && j <= r) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
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
    return sum1;
}

ll re(ll arr[], ll l, ll r) {
    if (l >= r) {
        return 0;
    } else {
        ll mid = (l + r) / 2;
        ll num1 = re(arr, l, mid);
        ll num2 = re(arr, mid + 1, r);
        ll num3 = merge(arr, l, mid, r);
        return num1 + num2 + num3;
    }

}


int main() {
    scanf("%lld%lld%lld", &n, &L, &R);
    sum[0] = 0;
    for (int i = 0; i < n; ++i) {
        scanf("%lld", &array1[i]);
        sum[i + 1] = sum[i] + array1[i];
    }
//    for (int i = 0; i < n; ++i) {
//        cout << array1[i]<<" ";
//    }
//    cout << endl;
//    for (int i = 0; i <=n; ++i) {
//        cout << sum[i] <<" ";
//    }
//    cout << endl;
    ll res = re(sum, 0, n);
    printf("%lld\n", res);
//    cout << endl;
//    for (int i = 0; i <=n; ++i) {
//        cout << sum[i] <<" ";
//    }
    return 0;
}

