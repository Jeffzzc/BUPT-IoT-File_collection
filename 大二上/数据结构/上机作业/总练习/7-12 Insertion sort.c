#include<stdio.h>

#include<stdlib.h>
int a[25],b[25];
int n;
void show(){
	int i;
	for(i=1;i<=n;i++){
		printf("%d,",b[i]);
	}
	printf("\n");
}
int main(){
	int n,i,j,k,now,flag;
	scanf("%d",&n);
	for(i=1;i<=n;i++){
		scanf("%d,",&a[i]);
		b[i]=a[i];
	}
	for(i=2;i<=n;i++){
		now = b[i];
		flag=0;
		for(j=i-1;j>=1;j--){
			if(now < b[j]){
				b[j+1] = b[j];
			}
			else{
				b[j+1] = now;
				flag=1;
				break;
			}
		}
		if(!flag){
			b[j+1] = now;
		}
		for(k=1;k<=n;k++){
			printf("%d,",b[k]);
		}
		printf("\n");
	}
	return 0;
}
