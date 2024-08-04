#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define M(a,x) memset(a,x,sizeof(a));
int g[5][5];

int main(){
	int p,q,t;
	int i,j;
	int w[5]={0};
	int z=0;
	char initial[50];
	int number[50];
	int length;
	int n=0;
	memset(g,0,sizeof(g));
	scanf("%s",initial);
	scanf("%d",&t);
	length = strlen(initial);
	for(i=0;i<length;i++){
		if(initial[i]==','){
			i++;
		}
		if(i!=length){
			number[n] = initial[i] - '0';
			n++;
		}
	}
	for(i=0;i<n;i+=2){
		g[number[i]][number[i+1]] = 1;
	}
//	while(~scanf("%d,%d,",&p,&q)){
//		g[p][q]=1;
//	}
	getchar();
	int flag=0;
	int ans=0;
	for(i=0;i<=4;i++){
		flag=0;
		for(j=0;j<=4;j++){
			if(g[i][j] == 1){
				flag++;
				if(flag % 2 == 1 && j == t){
					ans = 1;
					w[z++] = i;
				}
			}
		}
	}
	if(!ans){
		printf("-1");
	}
	else{
		for(i=0;i<z;i++){
			printf("%d,",w[i]);
		}
		printf("\n");
	}
	return 0;
} 
