#include<stdio.h>
#include<math.h>
#include<stdlib.h>
#include<string.h>

int KMP(char a[],char key[],int next[],int i){
	int j=1;
	int alen = strlen(a) - 1;
	int keylen = strlen(key) - 1;
	while(i <= alen && j <= keylen){
		if(j == 0 || a[i] == key[j]){
			i++;
			j++;
		}
		else{
			j = next[j];
		}
	}
	if(j == keylen + 1){
		return i - keylen;
	}
	else{
		return 0;
	}
} 
int main(){
	char *a;
	char *key;
	a = malloc(sizeof(char) * 220);
	key = malloc(sizeof(char) * 22);
	a[0]=1;
	key[0]=1;
	
	int i = 0;
	char y;
	while((y=getchar()) != '\n' && y != EOF){
		a[++i] = y;
	}
	i = 0;
	while((y=getchar())!='\n'&&y!=EOF){
		key[++i]=y;
	}
	int next[22];
	int j,k;
	int keylen;
	keylen=strlen(key)-1;
	j=1,k=0;
	next[1]=0;
	while(j <= keylen){
		if(k==0 || key[k]==key[j]){
			j++;k++;
			next[j]=k;
		}
		else{
			k = next[k];
		}
	}
	for(i=1;i<=keylen;i++){
		printf("%d,",next[i]);
	}
	printf("\n");
	
	int index;
	index = KMP(a,key,next,0);
	int flag=0;
	while(index!=0){
		flag=1;
		printf("%d\n", index);
        index = KMP(a, key, next, index + 1);
	}
	if (flag == 0)
    {
        printf("-1");
    }

    return 0;
}
