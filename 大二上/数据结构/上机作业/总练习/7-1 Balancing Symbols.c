#include<stdio.h>
#include<stdlib.h>
#include<string.h>
char a[70];
char p[70];
int main(){
	int i=0;
	int j=0;
	int k;
	int t=0,q=0,r=0;
	while(~scanf("%c",&a[i])){
		if(a[i]=='('||a[i]=='{'||a[i]=='['){
			p[j++]=a[i];
		}
		else if(a[i]==')'){
			if(p[j-1]!='('){
				p[j++]=a[i];
			}
			else{
				p[j]=' ';
				p[j-1]=' ';
				j-=1;
			}
		}
		else if(a[i]==']'){
			if(p[j-1]!='['){
				p[j++]=a[i];
			}
			else{
				p[j]=' ';
				p[j-1]=' ';
				j-=1;
			}
		}
		else if(a[i]=='}'){
			if(p[j-1]!='{'){
				p[j++]=a[i];
			}
			else{
				p[j]=' ';
				p[j-1]=' ';
				j-=1;
			}
		}
		i++;
	}
	for(;i>=0;i--){
		if(p[i]=='('||p[i]==')'){
			t++;
		}
		if(p[i]=='['||p[i]==']'){
			q++;
		}
		if(p[i]=='{'||p[i]=='}'){
			r++;
		}
	}
	if(!t&&!q&&!r){
		printf("0");
	}
	for(k=0;k<t;k++){
		printf("1,");
	}
	for(k=0;k<q;k++){
		printf("2,");
	}
	for(k=0;k<r;k++){
		printf("3,");
	}
//	if(t){
//		printf("1,");
//	}
//	if(q){
//		printf("2,");
//	}
//	if(r){
//		printf("3,");
//	}
	printf("\n");
	return 0;
}
