#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
#include<string.h>

int in[12];
int out[12];
int g[13][13];
int ans[13];
int main(){
	int i,j;
	char din,dout;
	int s,e,flag=0,she=0;
	scanf("%c,%c",&din,&dout);
	if(din=='S'){
		s = 0;
		flag=1;
	}
	if(dout=='T'){
		e = 10;
		she=1;
	}
	if(!flag){
		s = din - 'A' + 1;
	}
	if(!she){
		e = dout - 'A' + 1;
	}
	memset(g,0,sizeof(g));
	memset(in,0,sizeof(in));
	memset(out,0,sizeof(out));
	g[1][2]=1;g[2][3]=1;g[4][1]=1;g[1][5]=1;
	g[4][5]=1;g[5][3]=1;g[5][6]=1;g[6][3]=1;g[7][4]=1;
	g[7][5]=1;g[7][8]=1;g[8][5]=1;g[8][9]=1;g[5][9]=1;g[9][6]=1;
	
	g[s][e]=0;
	int count=0;
	for(i=1;i<10;i++){
		for(j=1;j<10;j++){
			if(g[i][j]==1){
				in[j]++;
				out[i]++;
			}
		}
	}
	while(count != 9){
		count++;
		int Min = -1;
		int mini = 0;
		for(i=9;i>=1;i--){
			if(in[i]==0){
				mini = i;
			}
		}
		ans[count] = mini;
		for(i=0;i<12;i++){
			if(g[i][mini]==1){
				g[i][mini]=0;
				out[i]--;
			}
			if(g[mini][i]==1){
				g[i][mini]=0;
				in[i]--;
			}
		}
		in[mini] = -1;out[mini] = -1;
	}
	printf("S,");
	for(i=1;i<=9;i++){
		printf("%c,",'A'+ans[i] - 1);
	}
	printf("T,");
	return 0;
}
