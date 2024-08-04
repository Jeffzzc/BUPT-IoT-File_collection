#include<stdio.h>

int main(){
    int n,m;
    scanf("%d,%d",&n,&m);
    int a[200];
    int ans[200];
    int vis[200];
    int i,j,k,now=0;
    for(i=0;i<n;i++){
        vis[i] = 0;
        a[i]=i+1;
    }
    for(i=0;i<n-1;i++){
        for(j=1;j<=m;j++){
            if(!vis[(now+1)%n]){
                now = (now+1)%n;
            }
            else{
                now = (now+1)%n;
                j--;
            }
        }
        vis[now] = 1;
        printf("%d,",a[now]);
        for(k=0;k<n;k++){
            if(!vis[(now+1)%n]){
                now = (now+1)%n;
                break;
            }
            else{
                now=(now+1)%n;
            }
        }
    }
    for(i=0;i<n;i++){
        if(!vis[i]){
            printf("%d",a[i]);
        }
    }
    return 0;
}
