#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<math.h>
int a[5000];
int b[5000];
int vis[5000];
int n,m;
int i,j,k,w;
int Find(int x){
    w=1;
    while(w<=pow(2,n) + 5){
        if(b[x] > a[w]){
            w = w * 2 + 1;
        }
        else{
            w = w * 2;
        }
        if(!vis[w]){
            return w;
        }
    }
}
int main(){
    int flag=0;
    scanf("%d",&n);
    for(i=1;i<=n;i++){
        vis[i] = 0;
    }
    i=1;
    for(w=1;w<=n;w++){
        scanf("%d,",&b[w]);
    }
    a[1] = b[1];
    vis[1] = 1;
    for(i=2;i<=n;i++){
        a[Find(i)] = b[i];
        vis[Find(i)] = 1;
    }
    scanf("%d",&m);
    for(k=pow(2,m-1);k<pow(2,m);k++){
        if(vis[k]){
	        flag = 1;
	        printf("%d,",a[k]);
    	}
	}
    if(!flag){
        printf("-1");
    }
    return 0;
}
