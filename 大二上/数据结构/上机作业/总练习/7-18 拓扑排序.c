#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
#include<string.h>

int g[13][13];
int in[13],out[13];
int ans[13];
int getname(char c,char name[]){
	int i;
	for(i=0;i<11;i++){
		if(c == name[i]){
			return i;
		}
	}
	return -1;
}
int indegree(int j,int g[13][13]){
	int in=0;
	int i;
	for(i=0;i<11;i++){
		if(g[i][j]!=0){
			in = in + 1;
		}
	}
	return in;
}
int main(){
	int i,j,k,l;
	char name[11] = {
        'S',
        'A',
        'B',
        'C',
        'D',
        'E',
        'F',
        'G',
        'H',
        'I',
        'T',
    };
    int s,e;
    char din,dout;
    scanf("%c,%c",&din,&dout);
    s=getname(din,name);
    e=getname(dout,name);
    for(i=0;i<11;i++){
    	for(j=0;j<11;j++){
    		g[i][j]=0;
		}
	}
	for(i=0;i<12;i++){
		in[i]=0;out[i]=0;
	}
	g[1][2]=1;g[2][3]=1;g[4][1]=1;g[1][5]=1;
	g[4][5]=1;g[5][3]=1;g[5][6]=1;g[6][3]=1;g[7][4]=1;
	g[7][5]=1;g[7][8]=1;g[8][5]=1;g[8][9]=1;g[5][9]=1;g[9][6]=1;
	
	g[s][e]=0;

	
    int queue[11];
    int front=0;
    int rear=-1;
    int size=0;
    printf("S,");
    for(j=1;j<=9;j++){
    	int indeg = indegree(j,g);
    	if(indeg==0){
    		queue[++rear] = j;
    		size++;
		}
	}
	while(size != 0){
		int x = queue[front++];
		printf("%c,",name[x]);
		size--;
		for(j=1;j<=9;j++){
			if(g[x][j]==1){
				g[x][j]=0;
				if(indegree(j,g)==0){
					queue[++rear] = j;
					size++;
				}
			}
		}
	}
	printf("T,");
	return 0;
}
