#include<stdio.h>
#include<string.h>
#include<stdlib.h>
struct heap{
	int capacity;
	int top;
	int *array;
};
typedef struct heap *priorityQueue;

void Insert(int x , priorityQueue h){
	int i;
	for(i = ++h->top ; x < h->array[i / 2] ; i /= 2){
		h->array[i] = h->array[i/2];
	}
	h->array[i] = x;
}
void Down(int p, priorityQueue h){
	int i;
	int child;
	int x = h->array[p];
	for(i = p; 2 * i <= h->capacity ; i = child){
		child = 2 * i;
		if(child != h->capacity && h->array[child + 1] < h->array[child]){
			child++;
		}
		if(x > h->array[child]){
			h->array[i] = h->array[child];
		}
		else{
			break;
		}
	}
	h->array[i] = x;
}
int main(){
	int num;
	scanf("%d",&num);
	int array[num];
	int i;
	for(i=0;i<num;i++){
		scanf("%d",&array[i]);
		getchar();
	}
	priorityQueue h1,h2;
	h1 = malloc(sizeof(struct heap));
	h1->array = malloc(sizeof(int) * (num + 1));
	h1->top = 0;
	h1->capacity = num;
	
	h2 = malloc(sizeof(struct heap));
	h2->array = malloc(sizeof(int) * (num + 1));
	h2->top = 0;
	h2->capacity = num;
	
	for(i=0;i<num;i++){
		Insert(array[i],h1);
	}
	for(i=0;i<num;i++){
		h2->array[++h2->top] = array[i];
	}
	for(i = num / 2; i > 0 ; i--){
		Down(i,h2);
	}
	for(i=1;i<num;i++){
		printf("%d,",h1->array[i]);
	}
	printf("%d\n",h1->array[num]);
	for(i=1;i<num;i++){
		printf("%d,",h2->array[i]);
	}
	printf("%d\n",h2->array[num]);
	return 0;
}
