#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
#include<string.h>
#define INF 99999
int g[205][205];
int n,m,k,cnt;
int dis[205];
int vis[205];
int main(){
	scanf("%d %d",&n,&m);
	int i,j,k;
	for(i=1;i<=n;i++){
		for(j=1;j<=n;j++){
			g[i][j]=0;
		}
	}
	for(i=0;i<m;i++){
		int p,q;
		scanf("%d %d",&p,&q);
		g[p][q] = 1;
		g[q][p] = 1;
	}
	scanf("%d",&cnt);
	for(i=0;i<cnt;i++){
		int x;
		scanf("%d",&x);
		int flag=0;
		memset(vis,0,sizeof(vis));
		for(j=1;j<=x;j++){
			scanf("%d",&dis[j]);
		}
		if(x!=(n+1)){
			printf("NO\n");
			continue;
		} 
		for(j=1;j<=x;j++){
			if((vis[dis[j]] && j != x) || (vis[dis[j]] && j==x && dis[1] != dis[j])){
				printf("NO\n");
				flag=1;
				break;
			}
			if(j > 1){
				if(!g[dis[j-1]][dis[j]]){
					printf("NO\n");
					flag=1;
					break;
				}
			}
			vis[dis[j]] = 1;
		}
		if(flag) continue;
		printf("YES\n");
	}
	return 0;
}
