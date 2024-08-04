#include<stdio.h>
#include<math.h>
#include<stdlib.h>
#include<string.h>

int KMP(char a[],char key[],int next[],int i){
    int j=1;
    int alen=strlen(a)-1;
    int klen=strlen(key)-1;
    while(i<=alen && j<=klen){
        if(j==0||a[i]==key[j]){
            i++;
            j++;
        }
        else{
            j=next[j];
        }
    }
    if(j==klen+1){
        return i-klen;
    }
    else{
        return 0;
    }
}
int main(){
    char *a;
    char *key;
    a=malloc(sizeof(char)*220);
    key=malloc(sizeof(char)*22);
    a[0]=1;key[0]=1;
    int i=0;
    char y;
    while((y=getchar())!='\n'&&y!=EOF){
        a[++i]=y;
    }
    i=0;
    while((y=getchar())!='\n'&&y!=EOF){
        key[++i]=y;
    }
    i=0;
    int next[22];
    int j,k;
    int klen = strlen(key)-1;
    j=1,k=0;
    next[1]=0;
    while(j<=klen){
        if(k==0 || key[k]==key[j]){
            j++;k++;
            next[j]=k;
        }
        else{
            k=next[k];
        }
    }
    for(i=1;i<=klen;i++){
        printf("%d,",next[i]);
    }
    printf("\n");
    int index=KMP(a,key,next,0);
    int f=0;
    while(index!=0){
        f=1;
        printf("%d\n",index);
        index=KMP(a,key,next,index+1);
    }
    if(f==0){
        printf("-1");
    }
    return 0;
}
