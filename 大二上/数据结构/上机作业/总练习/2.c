#include<stdio.h>
#include<stdlib.h>
struct node{
	char element;
	struct node* next;
};
typedef struct node *PtrToNode;
typedef PtrToNode Stack;
struct Node{
	float element;
	struct Node* next;
};
typedef struct Node* nstack;

int isEmpty(Stack s);
int isEmptyn(nstack ns);
void makeEmpty(Stack s);
void makeEmptyn(nstack ns);
Stack createStack();
nstack createStackn();
char pop (Stack s);
float popn (nstack ns);
void push (char x, Stack s);
void pushn (float x, nstack ns);
char top(Stack s);

main(){
//	Stack s=(Stack)malloc(sizeof(struct node));
//	s->next=NULL;
	Stack s=createStack();
	char str[100],re[100],tmp;
	int i=0,j=0,len=0;
	scanf("%s",str);//输入
	
	//中缀表达式转后缀表达式 
	while(str[i]!='\0'){
		if(str[i]>'0' && str[i]<='9'){
			//数字直接存入结果数组中 
			re[j++]=str[i];
		}
		else if(str[i]=='('){
			//(直接压栈 
			push(str[i],s);
		}
		else if(str[i]==')'){
			//)弹栈直至遇到(
			tmp=pop(s); 
			while(tmp!='('){
				re[j++]=tmp;
				tmp=pop(s);
			}
		}
		else if(str[i]=='+' || str[i]=='-'){
			//+-优先级较低，弹栈至遇到(或空栈，(还要压栈压回去
			if(!isEmpty(s)){
				//如果不是空栈 
				tmp=pop(s);
				while(tmp!='('){
					re[j++]=tmp;
					if(!isEmpty(s)){
						tmp=pop(s);
					}else{
						break;
					}
				}
				if(tmp=='('){
					push(tmp,s);
				}
			}
			push(str[i],s);
		}
		else if(str[i]=='*' || str[i]=='/'){
			//优先级高，只有乘除才弹栈 
			if(!isEmpty(s)){
				//不是空栈
				tmp=pop(s);
				while(tmp=='*' || tmp=='/'){
					re[j++]=tmp;
					if(!isEmpty(s)){
						tmp=pop(s);
					}else{
						break;
					}
				}
				if(tmp=='+' || tmp=='-' || tmp=='('){
					push(tmp,s);
				} 
			}
			push(str[i],s);
		}
		else{
			printf("\nWrong format\n");
		}
		i++;
	}
	while(!isEmpty(s)){
		tmp=pop(s);
		re[j++]=tmp;
	}
	len=j;
	//计算结果 
	float right=0,left=0,n=0;
	nstack ns=(nstack)malloc(sizeof(struct Node));
	ns->next=NULL;
	for(j=0;j<len;j++){
		switch(re[j]){
			case '+':
				right=popn(ns);
				left=popn(ns);
				pushn(right+left,ns);
				break;
			case '-':
				right=popn(ns);
				left=popn(ns);
				pushn(left-right,ns);
				break;
			case '*':
				right=popn(ns);
				left=popn(ns);
				pushn(left*right,ns);
				break;
			case '/':
				right=popn(ns);
				left=popn(ns);
				pushn(left/right,ns);
				break;
			default:
				n=re[j]-'0';
				pushn(n,ns);
				break;
		}
	}
	//输出结果
	n=popn(ns);
	printf("%.2lf\n",n);
	for(j=0;j<len;j++){
		printf("%c ",re[j]);
	} 
}

int isEmpty(Stack s){
	return s->next == NULL;
}
int isEmptyn(nstack ns){
	return ns->next==NULL;
}
void makeEmpty(Stack s) {
	if (s == NULL){
		printf("\nMust use CreateStack first\n");
	}else{
		while (!isEmpty(s)){
			pop(s);
		}
	}
}
void makeEmptyn(nstack ns){
	if(ns==NULL){
		printf("\nMust use CreateStack first\n");
	}else{
		while(!isEmptyn(ns)){
			popn(ns);
		}
	}
}
Stack createStack(){
	Stack s;
	s = (Stack)malloc (sizeof (struct node));
	if (s==NULL) {
		printf("\nOut of space!!\n");
	}
	s->next=NULL; 
	makeEmpty(s);
	return s;
}
nstack createStackn(){
	nstack ns;
	ns=(nstack)malloc(sizeof(struct Node));
	if(ns==NULL){
		printf("\nOut of space!!\n");
	}
	ns->next=NULL;
	makeEmptyn(ns);
	return ns;
}
char pop (Stack s) {
	char tmp;
	PtrToNode first;
	if (isEmpty(s)) {
		printf("\nempty stack\n");
	} else {
		first = s->next;
		s->next = s->next->next;
		tmp=first->element;
		free(first);
	}
	return tmp;
}
float popn (nstack ns) {
	float tmp;
	nstack first;
	if (isEmptyn(ns)) {
		printf("\nempty stack\n");
	} else {
		first = ns->next;
		ns->next = ns->next->next;
		tmp=first->element;
		free(first);
	}
	return tmp;
}
void push (char x, Stack s) {
	PtrToNode temp;
	temp = (PtrToNode)malloc(sizeof(struct node));
	if (temp == NULL) {
		printf("\nout of space!!\n");
	} else {
		temp->element = x;
		temp ->next = s->next;
		s->next = temp;
	}
	temp=NULL;
	free(temp);
}
void pushn (float x, nstack ns) {
	nstack temp;
	temp = (nstack)malloc(sizeof(struct Node));
	if (temp == NULL) {
		printf("\nout of space!!\n");
	} else {
		temp->element = x;
		temp ->next = ns->next;
		ns->next = temp;
	}
	temp=NULL;
	free(temp);
}
char top(Stack s) {
	if (!isEmpty(s)) {
		return s->next->element;
	}
	printf("\nEmpty stack\n");
	return 0;
}

