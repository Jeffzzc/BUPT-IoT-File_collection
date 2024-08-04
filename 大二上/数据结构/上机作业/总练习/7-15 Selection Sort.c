#include<stdio.h>
#include<string.h>

int n;
int a[100000];

int main() {
	int i,j,k,l;
	scanf("%d",&n);
	for(i=1;i<=n;i++){
		scanf("%d,",&a[i]);
	}
	for(i=1;i<=n-1;i++){
		int now = i;
		for(j=i+1;j<=n;j++){
			if(a[j] < a[now]){
				now = j;
			}
		}
		int t=a[now];
		a[now]=a[i];
		a[i]=t;
		for(j=1;j<=n;j++){
			printf("%d,",a[j]);
		}
		printf("\n");
	}
	return 0;
}
