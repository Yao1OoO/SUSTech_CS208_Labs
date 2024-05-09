#include <bits/stdc++.h>

#define ll long long
using namespace std;

int arr[200010];
int max_v[200010];
int min_v[200010];
int cnt1[800040];
int cnt2[800040];
int n;

ll count(int l, int r) {
    if (l == r) {
        return 1;
    }
    int mid = (l + r) >> 1;
    ll res = 0;
    max_v[mid + 1] = arr[mid + 1];
    min_v[mid + 1] = arr[mid + 1];
    max_v[mid] = arr[mid];
    min_v[mid] = arr[mid];
    for (int i = mid + 2; i <= r; ++i) {
        max_v[i] = max(arr[i], max_v[i - 1]);
        min_v[i] = min(arr[i], min_v[i - 1]);
    }
    for (int i = mid - 1; i >= l; --i) {
        max_v[i] = max(arr[i], max_v[i + 1]);
        min_v[i] = min(arr[i], min_v[i + 1]);
    }
    int j1 = mid + 1;
    int j2 = mid + 1;
    for (int i = mid; i >= l; --i) {
        while (j1 <= r && min_v[i] < min_v[j1]) {
            ++cnt1[max_v[j1] - j1 + n];
            ++j1;
        }
        while (j2 <= r && min_v[i] < min_v[j2] && max_v[j2] < max_v[i]) {
            ++cnt2[max_v[j2] - j2 + n];
            ++j2;
        }
        res += (cnt1[min_v[i] - i + n] - cnt2[min_v[i] - i + n]);
        int x = i + max_v[i] - min_v[i];
        if (mid < x && x < j2) {
            ++res;
        }
    }
    for (int i = mid + 1; i <= r; ++i) {
        cnt1[max_v[i] - i + n] = 0;
        cnt2[max_v[i] - i + n] = 0;
    }
    int i1 = mid;
    int i2 = mid;
    for (int j = mid + 1; j <= r; ++j) {
        while (i1 >= l && min_v[j] < min_v[i1]) {
            ++cnt1[max_v[i1] + i1 + n];
            --i1;
        }
        while (i2 >= l && min_v[j] < min_v[i2] && max_v[i2] < max_v[j]) {
            ++cnt2[max_v[i2] + i2 + n];
            --i2;
        }
        res += (cnt1[min_v[j] + j + n] - cnt2[min_v[j] + j + n]);
        int x = j - max_v[j] + min_v[j];
        if (i2 < x && x <= mid) {
            ++res;
        }
    }
    for (int i = l; i <= mid; ++i) {
        cnt1[max_v[i] + +i + n] = 0;
        cnt2[max_v[i] + i + n] = 0;
    }
    return count(l, mid) + count(mid + 1, r) + res;
}


int main() {
    scanf("%d", &n);
    for (int i = 1; i <= n; ++i) {
        scanf("%d", &arr[i]);
    }
    ll res = count(1, n);
    printf("%lld", res);
    return 0;
}