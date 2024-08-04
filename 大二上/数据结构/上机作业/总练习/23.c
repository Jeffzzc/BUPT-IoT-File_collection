#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
#include<string.h>
#define INF 99999
#define M(a,x) memset(a,x,sizeof(a));
int pre[1005];
int deg[1005];
int n,m;

void init(){
	int i;
	for(i=0;i<1005;i++){
		pre[i] = i;
		deg[i] = 0;
	}
}
int Find(int k){
	if(k == pre[k]) return k;
	else return pre[k] = Find(pre[k]);
}
void Union(int x,int y){
	int p=Find(x);
	int q=Find(y);
	if(p!=q){
		pre[q]=p;
		return;
	}
	return;
}
int process(){
	int i,t=0;
	for(i=1;i<=n;i++){
		if(pre[i]==i) t++;
	}
	if(t!=1) return 0;
	for(i=1;i<=n;i++){
		if(deg[i] % 2 == 1) return 0;
	}
	return 1;
}
int main(){
	int i,x,y;
	init();
	scanf("%d %d",&n,&m);
	for(i=1;i<=m;i++){
		scanf("%d %d",&x,&y);
		Union(x,y);
		deg[x]++;
		deg[y]++;
	}
	if(process()){
		printf("1\n");
	}
	else printf("0\n");
	return 0;
}
