#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int main(){
	char s[105];
	char ans[105];
	int i=0;
	char c;
	fgets(s,sizeof(s),stdin);
	int j=0;
	for(i=0;i<strlen(s);){
		if(s[i]=='1'&&s[i+1]=='0'&&s[i+2]=='1'&&s[i+3]=='1'){
			ans[j++]='h';
			i+=4;
		}
		if(s[i]=='0'&&s[i+1]=='0'){
			ans[j++]='b';
			i+=2;
		}
		if(s[i]=='0'&&s[i+1]=='1'){
			ans[j++]='g';
			i+=2;
		}
		if(s[i]=='1'&&s[i+1]=='1'){
			ans[j++]='e';
			i+=2;
		}
		if(s[i]=='1'&&s[i+1]=='0'&&s[i+2]=='0'&&s[i+3]=='0'&&s[i+4]=='0'){
			ans[j++]='c';
			i+=5;
		}
		if(s[i]=='1'&&s[i+1]=='0'&&s[i+2]=='0'&&s[i+3]=='0'&&s[i+4]=='1'){
			ans[j++]='f';
			i+=5;
		}
		if(s[i]=='1'&&s[i+1]=='0'&&s[i+2]=='0'&&s[i+3]=='1'){
			ans[j++]='d';
			i+=4;
		}
		if(s[i]=='1'&&s[i+1]=='0'&&s[i+2]=='1'&&s[i+3]=='0'){
			ans[j++]='a';
			i+=4;
		}
	}
	for(i=0;i<j;i++){
		printf("%c",ans[i]);
	}
	return 0;
}
