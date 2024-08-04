#include<stdio.h>
#include<stdlib.h>
struct Binnode
{
	int val;
	struct Binnode*left;
	struct Binnode*next;
};
struct Binqueue
{
	int size;
	struct Binnode*tree[18];
};
struct Binqueue *a()
{
	struct Binqueue *temp=malloc(sizeof(struct Binqueue));
	temp->size=0;
	for(int i=0;i<18;i++)
		temp->tree[i]=NULL;
	return temp;
}
struct Binnode *combine(struct Binnode*T1,struct Binnode*T2)
{
	if(T1->val>T2->val)
		return combine(T2,T1);
	if(T1->left)
	{
		struct Binnode *temp=T1->left;
		while(temp->next)
			temp=temp->next;
		temp->next=T2;
	}
	else
		T1->left=T2;
	return T1;
}
struct Binqueue*merge(struct Binqueue*H1,struct Binqueue*H2)
{
	struct Binnode*T1,*T2,*carry=NULL;
	int i,j;
	H1->size+=H2->size;
	for(i=0,j=1;j<=H1->size;i++,j*=2)
	{
		T1=H1->tree[i];
		T2=H2->tree[i];
		switch(!!T1+2*!!T2+4*!!carry)
		{
		case 0:
		case 1:
			break;
		case 2:
			H1->tree[i]=T2;
			H2->tree[i]=NULL;
			break;
		case 4:
			H1->tree[i]=carry;
			carry=NULL;
			break;
		case 3:
			carry=combine(T1,T2);
			H1->tree[i]=H2->tree[i]=NULL;
			break;
		case 5:
			carry=combine(T1,carry);
			H1->tree[i]=NULL;
			break;
		case 6:
			carry=combine(T2,carry);
			H2->tree[i]=NULL;
			break;
		case 7:
			H1->tree[i]=carry;
			carry=combine(T1,T2);
			break;
		}
	}
	return H1;
}
struct Binqueue*insert(int n,struct Binqueue*H)
{
	struct Binqueue*temp=a();
	temp->tree[0]=malloc(sizeof(struct Binnode));
	temp->size=1;
	temp->tree[0]->val=n;
	temp->tree[0]->left=temp->tree[0]->next=NULL;
	merge(H,temp);
	return H;
}
int* levelorder(struct Binnode*T)
{
	struct Binnode*queue[100],*node;
	int rear=0;
	int front=0;
	queue[rear++]=T;
	int *ans=malloc(sizeof(int)*100);
	int i=0;
	while(rear>front)
	{
		node=queue[front++];
		ans[i++]=node->val;
		if(node->next)
			queue[rear++]=node->next;
		if(node->left)
			queue[rear++]=node->left;
	}
	return ans;
}
int main()
{
	int x[100];
	int k;
	int i=0;
	while((scanf("%d,",&x[i]))!=EOF)
		i++;
	struct Binqueue*q=a();
	
	for(int n=0;n<i-1;n++)
		insert(x[n],q);
	int s=x[i-1];
	if(q->tree[s]==NULL)
	{
		printf("0,");
		return 0;
	}
	int *ans;
	ans=levelorder(q->tree[s]);
	i=0;
	while(ans[i]!='\0')
		printf("%d,",ans[i++]);
}
