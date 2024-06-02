#include <bits/stdc++.h>

#define ll long long
using namespace std;
ll games[100010];

int main() {
    ll n,i,m,k;
    scanf("%lld%lld%lld%lld",&n,&i,&m,&k);
    for (int j = 0; j < m; ++j) {
        ll game;
        scanf("%lld",&game);
        games[j] = game;
    }
    ll sum =0;
    for (int j = 0; j <m; ++j) {
        sum += games[j];
    }
    ll money = n;
    if (k == 0){
        if (sum <= money) {
            printf("1 morning\n");
            return 0;
        }
        ll res = (sum-n-1)/i +1;
        printf("%lld evening\n" ,res);
    } else{
        std::sort(games,games+m);

//        for (int j = 0; j < m; ++j) {
//            cout << games[j]<<" ";
//        }
//        int a = upper_bound(games,games+m,100)-games;
//        cout << a <<endl;
        ll start = 0;
        ll x = 1;
        if (sum <= money) {
            printf("1 morning\n");
            return 0;
        }
        money += i;
        if (sum <= money){
            printf("1 evening\n");
            return 0;
        }
        while (sum > money){
            start = upper_bound(games, games + m, k * x) - games;
            if (start >=0 && start <m) sum -= (m - start) * k;
            if (sum <= money){
                printf("%lld morning\n",x+1);
                break;
            }
            money += i;
            if (sum <= money){
                printf("%lld evening\n",x+1);
                break;
            }
            x++;
        }

    }
    return 0;
};