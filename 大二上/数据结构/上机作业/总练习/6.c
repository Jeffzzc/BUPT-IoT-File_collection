#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<math.h>
int a[5000];
int b[5000];
int vis[5000];
int n,m;
int i,j,k,w;
int Find(int x){
    w=1;
    while(w<=pow(2,n) + 5){
        if(b[x] > a[w]){
            w = w * 2 + 1;
        }
        else{
            w = w * 2;
        }
        if(!vis[w]){
            return w;
        }
    }
}
void show(){
	for(w=1;w<=n;w++){
    	if(vis[w]){
    		printf("%d,",a[w]);
    		show(w*2);
    		show(w*2+1);
		}
	}
}
int main(){
    int flag=0;
    i=1;
    char q;
    while((q=getchar())!='\n'){
    	if(q!=','){
    		a[i++] = q - '0';
		}
	}
	n = i - 1;
	for(i = 1;i<=n;i++){
        vis[i] = 0;
    }
    a[1] = b[1];
    vis[1] = 1;
    for(i=2;i<=n;i++){
        a[Find(i)] = b[i];
        vis[Find(i)] = 1;
    }
    show();
    return 0;
}
