#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define INF 9999
int g[9][9];
int vis[9]={0};
void dfs(int p,int num){
	if(num == 8){
		return;
	}
	int j;
	vis[p]=1;
	printf("%d,",p);
	for(j=1;j<=8;j++){
		if(g[p][j]==1 && !vis[j]){
			dfs(j,++num);
		}
	}
}
int main(){
	int i,j,k;
	for(i=1;i<9;i++)
		for(j=1;j<=9;j++){
			g[i][j]=0;
		}
	
	g[1][2]=1;g[2][1]=1;g[1][3]=1;g[3][1]=1;g[2][4]=1;g[4][2]=1;
	g[2][5]=1;g[5][2]=1;g[3][6]=1;g[6][3]=1;g[3][7]=1;g[7][3]=1;
	g[4][8]=1;g[8][4]=1;g[5][8]=1;g[8][5]=1;g[6][8]=1;g[8][6]=1;
	g[7][8]=1;g[8][7]=1;
	int s,now;
	scanf("%d",&s);
	dfs(s,1);
	return 0;
}
	
