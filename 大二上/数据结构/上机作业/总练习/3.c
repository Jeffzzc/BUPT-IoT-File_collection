#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int a[1000],b[1000];
int main(){
	int k;
	int i=0;
	while(~scanf("%d,",&b[i])){
		a[i] = b[i];
		i++;
	}
	int j,flag=0;
	for(j=0;j<i/2-1;j++){
		if(a[j]!=a[i-j-1]){
			flag=1;
		}
	}
	if(flag) printf("No\n");
	else printf("Yes\n");
	return 0;
}
