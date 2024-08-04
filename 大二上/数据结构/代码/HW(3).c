#include<stdio.h>
#include<stdlib.h>
struct node;
typedef struct node *ptrtonode;
typedef ptrtonode list;
typedef ptrtonode position;
struct node {
	ptrtonode prior;
	int data;
	ptrtonode next;
};
int input(void);
list InitList(void);
void CreatList(list L, int n);
int Josephus(list L, int n, int m);
void DeleteList(list L);
int main(void)
{
	int n, m, s;
	list L;
	printf("Please enter the number of people: ");
	n = input();
	printf("Please enter the number of m: ");
	m = input();
	m = m % n;//避免一次游戏时循环几次
	L = InitList();
	CreatList(L,n);
	s = Josephus(L,n,m);
	printf("\nThe winner is %d\n",s);
	DeleteList(L);
	return 0;
}
int input(void)
{
	int n, status, ok = 1;
	while (ok) {
		status = scanf("%d",&n);
		if (status != 1 || n < 1 || getchar() != '\n') {
			while (getchar() != '\n')
				continue;
			printf("Enter again (n > 0): ");
			continue;
		}
		return n;
	}
}
//链表初始化，创建头节点，头指针
list InitList(void)
{
	list L = (list)malloc(sizeof(struct node));
	if (L == NULL) {
		fprintf(stderr,"Out of space\n");
		exit(EXIT_FAILURE);
	}
	L->prior = NULL;
	L->next = NULL;
	return L;
}
void InsertTail(position p, position h, int n)
{
	list tem = InitList();
	tem->data = n;
	p->next = tem;
	tem->prior = p;
	tem->next = h;//每次让尾节点指向头节点
	h->prior = tem;
}
/*建立循环链表，带有头节点，为了让插入第一个节点和其他节点
  操作一致。
  先让尾节点指向头节点，链表建立完成后，再让尾节点指向首节点。
 */
void CreatList(list L, int n)
{
	int num;
	position p = L, head = L;//head指向头节点
	for (int i = 0; i < n; i++) {
		InsertTail(p,head,i);
		p = p->next;
	}
	//链表创建完成让首节点和尾节点相连
	position pfirst = head->next, pend = head->prior;
	pfirst->prior = pend;
	pend->next = pfirst;
}
int IsFirst(position p, position h)
{
	return h->next == p;
}
position DeleteNode(position p)
{
	position pbefore = p->prior, pafter = p->next;
	pbefore->next = pafter;
	pafter->prior = pbefore;
	free(p);
	return pafter;
}
position AntiDelete(position p, position h, int m)
{
	for (int i = 0; i < m; i++) 
		p = p->prior;
	//判断要删除的节点是不是首节点，
	//删除首节点要改变头节点指向地址
	if (IsFirst(p,h)) 
		h->next = p->next;
	return DeleteNode(p);
}
position Delete(position p, position h, int m)
{
	for (int i = 0; i < m; i++)
		p = p->next;
	if (IsFirst(p,h))
		h->next = p->next;
	return DeleteNode(p);
}
int Josephus(list L, int n, int m)
{
	position h = L, p = h->next;
	int num = n;
	while (p->next != p) {
		//如果 m 大于人数的一半，逆时针删节点
		//顺时针移动m，则逆时针移动num-m
		if (m > (int)num/2) 
			p = AntiDelete(p,h,num-m);
		else
			p = Delete(p,h,m);
		num = n-1;
		m = m % num;
	}
	//退出时说明只剩一个节点（不包括头节点）
	//将最后的节点数据加1为实际编号
	return p->data + 1;
}
void DeleteList(list L)
{
	position p = L->next, tem;
	L->next = NULL;
	L->prior = NULL;
	while (p->next != p) {
		tem = p->next;
		tem->prior = p->prior;
		p->prior->next = tem;
		free(p);
		p = tem;
	}
	free(p);
}

