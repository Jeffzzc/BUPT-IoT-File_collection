#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
#include<string.h>
#define INF 99999
#define M(a,x) memset(a,x,sizeof(a));
int g[11][11] ;
int vis[11];
int ans[11];
int main(){
	int i,j,k,now,cnt=0;
	for(i=1;i<=10;i++){
		for(j=1;j<=10;j++){
			g[i][j]=INF;
		}
	}
	g[1][2]=3;g[2][1]=3;g[2][3]=10;g[3][2]=10;
	g[1][4]=4;g[4][1]=4;g[1][5]=4;g[5][1]=4;g[2][5]=2;g[5][2]=2;
	g[2][6]=3;g[6][2]=3;g[3][6]=6;g[6][3]=6;g[3][7]=1;g[7][3]=1;
	g[4][5]=5;g[5][4]=5;g[5][6]=11;g[6][7]=2;
	g[4][8]=6;g[5][8]=2;g[5][9]=1;g[6][9]=4;
	g[6][9]=3;g[6][10]=11;g[9][10]=7;
	g[7][10]=8;
	for(i=1;i<=10;i++){
		for(j=1;j<=10;j++){
			if(g[i][j]!=INF){
				g[j][i] = g[i][j];
			}
		}
	}
	int l;
	char p,q;
	scanf("%c,%c,%d",&p,&q,&l);
	g[p-'A'+1][q-'A'+1]=l;g[q-'A'+1][p-'A'+1]=l;
	M(vis,0);
	M(ans,0);
	vis[1]=1;
	int lmin = INF;
	for(i=2;i<=10;i++){
		if(g[1][i] < lmin){
			lmin = g[1][i];
			now = i;
		}
	}
	ans[++cnt] = lmin;
//	printf("\n%d %d %d\n",cnt,lmin,now);
	vis[now] = 1;
	for(i=1;i<=8;i++){
		lmin = INF;
		for(j=1;j<=10;j++){
			if(vis[j]){
				for(k=1;k<=10;k++){
					if(!vis[k]&&g[j][k]<lmin){
						lmin = g[j][k];
						now = k;
					}
				}
			}
		}
		ans[++cnt] = lmin;
		vis[now] = 1;
	}
	for(i=1;i<=8;i++){
		for(j=1;j<=9-i;j++){
			if(ans[j] > ans[j+1]){
				int pp=ans[j];
				ans[j]=ans[j+1];
				ans[j+1]=pp;
			}
		}
	}
	for(i=1;i<=9;i++){
		printf("%d,",ans[i]);
	}
	return 0;
}
