//7-14 Quick Sort
//According to the basic idea of quick sort algorithm descirbed on class, we will modify a little bit of this algorithm.
//Only the first pivot is given by the input operation. The other pivots will still use Median-of-Three patitioning method.
//The inithial sequence is {49,38,65,97,76,13,27,50,2,8}. We will sort this sequence in ascending order.
//The parameter Cutoff is 3.
//
//输入格式:
//The index of first pivot in the sequence. The index begins from 0.
//
//输出格式:
//Several lines. Each line shows the temporary result of sort.
//In each interation, if the quicksort method is used, then add "Qsort:" before the result. 
//The first number in bracket is the left index of sequence processed by quick sort method, 
//and the second number is the right index of this sequence.
//If the insertion sort method is used, then add "Insert:" before the result. 
//The first number in brackets is the left index of sequence processed by insertion sort method,
// and the second number means the amount of the following elements.
//Add "\n" to the end of each line.
//
//输入样例:
//在这里给出一组输入。例如：
//
//1
//
//输出样例:
//在这里给出相应的输出。例如：
//
//Qsort(0,9):2,8,27,13,38,97,65,50,49,76,
//Qsort(0,3):2,8,27,13,38,97,65,50,49,76,
//insert(0,1):2,8,27,13,38,97,65,50,49,76,
//insert(2,2):2,8,13,27,38,97,65,50,49,76,
//Qsort(5,9):2,8,13,27,38,50,65,49,76,97,
//insert(5,3):2,8,13,27,38,49,50,65,76,97,
//insert(9,1):2,8,13,27,38,49,50,65,76,97,


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define CUTOFF 3

int a[]={49,38,65,97,76,13,27,50,2,8};

void Swap(int *a,int *b){
	int temp;
	temp = *a;
	*a = *b;
	*b = temp;
}
void InsertSort(int a[],int left,int right){
	int i,j,now;
	for(i = left + 1 ; i <= right ; i++){
		j = i - 1;
		now = a[i];
		while(j >= left && a[j] > now){
			a[j + 1] = a[j];
			j--;
		}
		a[j + 1] = now;
	}
}
int getmid(int a[],int left,int right){
	int mid = (left + right) / 2;
	if(a[left] > a[right]){
		Swap(&a[left],&a[right]);
	}
	if(a[left] > a[mid]){
		Swap(&a[left],&a[mid]);
	}
	if(a[mid] > a[right]){
		Swap(&a[right],&a[mid]);
	}
	Swap(&a[mid],&a[right - 1]);
	return a[right - 1];
}
void quicksort(int a[],int left,int right){
	int i,j,pivot;
	if(right - left + 1 > CUTOFF){
		pivot = getmid(a,left,right);
		i = left,j = right - 1;
		for(;;){
			while(a[++i] < pivot){
				
			}
			while(a[--j] > pivot){
				
			}
			if(i < j){
				Swap(&a[i],&a[j]);
			}
			else
				break;
		}
		Swap(&a[i],&a[right - 1]);
		int w = i;
		printf("Qsort(%d,%d):", left, right);
		for (i = 0; i <= 9; i++) {
            printf("%d,", a[i]);
        }
        printf("\n");
		quicksort(a , left , w - 1);
		quicksort(a , w + 1 , right);
	}
	else{
		int m = left;
		int n = right - left + 1;
		int tmp;
		for(i = m;i < m+n ; i++){
			tmp = a[i];
			for(j=i;j>m&&tmp<a[j-1];j--){
				a[j] = a[j-1];
			}
			a[j] = tmp;
		}
		printf("insert(%d,%d):", m, n);
        for (i = 0; i <= 9; i++) {
            printf("%d,", a[i]);
        }
        printf("\n");
	}
}
void Qsort(int a[],int left,int right){
	int i,j,w;
	for(;;){
		for(i=left;a[i]<a[right];i++){}
		for(j=right-1;a[j]>a[right];j--){}
		if(i<j){
			Swap(&a[j],&a[i]);
		}
		else break;
	}
	Swap(&a[i],&a[right]);
	w = i;
	printf("Qsort(%d,%d):",left,right);
	for(i=0;i<10;i++){
		printf("%d,",a[i]);
	}
	printf("\n");
	quicksort(a,left,w-1);
	quicksort(a,w+1,right);
}
int main(){
	int l;
	scanf("%d",&l);
	Swap(&a[l],&a[9]);
	Qsort(a,0,9);
	return 0;
}
