#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h>
#define INF 99999
#define M(a,x) memset(a,x,sizeof(a));
int a[8][8];
int main(){
	int i,j,k;
	for(i=0;i<=7;i++){
		for(j=0;j<=7;j++){
			a[i][j]=INF;
		}
	}
	a[1][2] = 3;
    a[1][3] = 3;
    a[1][4] = 6;
    a[2][4] = 2;
    a[2][5] = 5;
    a[3][4] = 3;
    a[3][6] = 3;
    a[4][5] = 2;
    a[4][6] = 2;
    a[4][7] = 5;
    a[5][7] = 3;
    a[6][7] = 4;
    
    int p,q,r;
    int array[150];
    scanf("%d,%d,%d,",&p,&q,&r);
    a[p][q] = INF;
    int E[8],L[8];
    for(i=0;i<8;i++){
    	E[i]=0;
    	L[i]=INF;
	}
    int now;
    
    int front=0;
    int rear = -1;
    int size = 0;
    int v,w;
    array[++rear]=1;
    size++;
    E[1] = 0;
    while(size!=0){
    	v = array[front++];
    	size--;
    	for(w=1;w<=7;w++){
    		if(a[v][w]!=INF){
    			array[++rear] = w;
    			size++;
    			if(a[v][w] + E[v] > E[w]){
    				E[w]=E[v]+a[v][w];
				}
			}
		}
	}
//	for(k=1;k<=7;k++){
//		printf("%d\n",E[k]);
//	}
	L[7] = E[7];
	front=0;
	rear=-1;
	size=0;
	array[++rear]=7;
	size++;
	
	while(size!=0){
		w = array[front++];
		size--;
		for(v=1;v<=7;v++){
			if(a[v][w]!=INF){
				array[++rear] = v;
				size++;
				if(L[w] - a[v][w] < L[v]){
					L[v]=L[w]-a[v][w];
				}
			}
		}
	}
//	for(k=1;k<=7;k++){
//		printf("%d\n",L[k]);
//	}
	printf("%d",L[r]-E[r]);
	return 0;
}


