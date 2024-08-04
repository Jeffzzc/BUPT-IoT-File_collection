#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
#include<string.h>
#define INF 99999
int g[8][8];

int main(){
    int i,j,k;
    for(i=1;i<=7;i++){
		for(j=1;j<=7;j++){
			g[i][j]=INF;
		}
	}
	g[1][2]=2;g[2][5]=10;g[3][1]=4;g[1][4]=1;
	g[2][4]=3;g[4][3]=2;g[4][5]=2;g[3][6]=5;
	g[4][6]=8;g[4][7]=4;g[7][6]=1;g[5][7]=6;
    int s,e;
    scanf("%d,%d",&s,&e);
    int ans[9];
    int vis[8];
    int A[8];
    for(i=1;i<=7;i++){
        ans[i] = INF;vis[i]=0;A[i]=0;
    }
    int now = s;
    vis[s]=1;ans[s]=0;
    vis[now]=1;ans[now]=0;
    int q;
    for(i=1;i<8;i++){
        for(j=1;j<8;j++){
            if(g[now][j]!=INF && ans[now]+g[now][j]<ans[j]){
                ans[j]=ans[now]+g[now][j];
                A[j] = now;
            }
        }
        int Min=9999;
        for(j=1;j<=7;j++){
            if(!vis[j]&&ans[j]<Min){
                Min=ans[j];
                q=j;
            }
        }
        vis[q]=1;
        now = q;
    }
    int show[8]={0};int cnt=0;
    if(vis[e] == 1){
        int w = e;
        while(A[w]!=s){
            show[cnt] = A[w];
            cnt++;
            w = A[w];
        }
        printf("%d,",s);
        while(cnt!=0){
            printf("%d,",show[cnt-1]);
            cnt--;
        }
        printf("%d,",e);
    }
    else{
        printf("-1");
    }
    return 0;
}
