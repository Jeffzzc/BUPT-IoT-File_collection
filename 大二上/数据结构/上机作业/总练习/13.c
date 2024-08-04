#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int n,i,j,k,l;
int a[1000];
int b[1000];
int main(){
	int m;
	scanf("%d",&n);
	for(i=1;i<=n;i++){
		scanf("%d,",&a[i]);
		b[i]=a[i];
	}
	m=n;
	while(m!=1){
		m = m / 2;
//		printf("%d\n",m);
		for(i=1;i<=n;i++){
			for(k=1;k<=n/m;k++){
				for(j=1;j+k*m<=n;j++){
					if(b[j] < b[j + k*m]){
						int tmp = b[j];
						b[j] = b[j + k*m];
						b[j + k*m] = tmp;
					}
				}
			} 
		}
//		m = m / 2;
		for(i=1;i<=n;i++){
			printf("%d,",b[i]);
		}
		printf("\n");
	}
	return 0;
}
