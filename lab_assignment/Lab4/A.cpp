#include <bits/stdc++.h>
using namespace std;
int main(){
    int t;
    scanf("%d", &t);
    for (int i = 0; i < t; i++) {
        int n;
        scanf("%d",&n);
        long long arr[n+1];
        arr[0] = 0;
        long long sub[n+1];
        for (int j = 0; j < n; j++) {
            scanf("%lld",&arr[j+1]);
            sub[j+1] = arr[j+1]-arr[j];
        }
        long long pos = 0, neg = 0;
        for (int j = 2; j < n+1; j++) {
            if (sub[j] >=0){
                pos += sub[j];
            } else{
                neg += sub[j];
            }
        }
        if (pos+neg >=0){
            printf("%lld\n",pos);
        } else{
            printf("%lld\n",0-neg);
        }
    }
    return  0;
}