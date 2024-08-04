#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<math.h>

struct node;
typedef struct node *ptr;
typedef ptr tree;
struct node{
	int data;
	ptr l;
	ptr r;
};
tree Insert(int x,tree T){
	if(T == NULL){
		T = malloc(sizeof(struct node));
		T->data = x;
		T->l = NULL;
		T->r = NULL;
	}
	else{
		if(x < T->data){
			T->l = Insert(x,T->l);
		}
		else if(x > T->data){
			T->r = Insert(x,T->r);
		}
	}
	return T;
}
void preorder(tree T){
	if(T != NULL){
		printf("%d,",T->data);
		preorder(T->l);
		preorder(T->r);
	}
}
int main(){
	tree T;
	int i,j,k;
	T = NULL;
	while(scanf("%d",&k)!=EOF){
		getchar();
		T = Insert(k,T);
	}
	preorder(T);
	return 0;
}
