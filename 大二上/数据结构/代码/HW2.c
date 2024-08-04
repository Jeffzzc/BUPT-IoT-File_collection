#include <stdio.h>
#include<math.h>
#include<stdlib.h>

#define size 100000
int a[size] = {4, -3, 5, -2, -1, 2, 6, -2};
void maxSubList1(int a[], int n)
{
	int max = 0;
	int i,j,k;
	int thisSum;
	for (i = 0; i < n; ++i)
	{
		for(j = i; j < n; ++j)
		{
			thisSum = 0;
			for(k = i; k < j; k++)
				thisSum += a[k];
				
			if(thisSum > max)
				max = thisSum;
			printf("(%d,%d)\n",thisSum,max);
		}
	}
//	return max;
}

void maxSubList2(int a[], int n)
{
	int max = 0;
	int i,j;
	int thisSum;
	for (i = 0; i < n; ++i)
	{
		thisSum = 0;
		for(j = i; j < n; ++j)
		{
			thisSum += a[j];
			if(max < thisSum)
				max = thisSum;
			printf("(%d,%d)\n",thisSum,max);
		}
	}
//	return max;
}

// 求ab的最大值
int max(int a, int b)
{
	return a > b ? a : b;
}

// 第三个算法
int maxSubList3(int a[], int left, int right)
{
	// 如果只有一个数
	int i;
	int center = (left + right) / 2; //中点
	int leftMaxSum, rightMaxSum, lrMaxSum;
	
	int maxLeftSum = 0;  
	int leftSum = 0;   //跨越边界的左端值最大
	
	int maxRightSum = 0;
	int rightSum = 0;
	
	if(left == right)
		return a[left];
	leftMaxSum = maxSubList3(a, left, center);// 整个出现在输入数据的左半部的最大子序列求和 
	rightMaxSum = maxSubList3(a, center+1, right);// 整个出现在输入数据的右半部的最大子序列求和 
	lrMaxSum = max(leftMaxSum, rightMaxSum); //计算左右两个子序列求和结果的最大值
	
	// 横跨左右两个部分的最大子序列求和
	
	//从center向左处理左半边  
	
	for (i = center; i >= left; i--)
	{  
		leftSum += a[i];  
		maxLeftSum = max(maxLeftSum, leftSum);  
		printf("1:%d 3:%d\n",leftSum,maxLeftSum);
	} 
	
	for (i = center+1; i <= right; i++)
	{
		rightSum += a[i];
		maxRightSum = max(maxRightSum, rightSum);
		printf("2:%d 4:%d\n",rightSum,maxRightSum);
	}
	return max(lrMaxSum, maxLeftSum + maxRightSum);
}

void maxSubList4(int A[], int N )  
{   int ThisSum, MaxSum;
	int i;
	ThisSum = MaxSum = 0;
	for( i = 0; i < N; i++ ) {
		ThisSum += A[i]; /* 向右累加 */
		if( ThisSum > MaxSum )
			MaxSum = ThisSum; /* 发现更大和则更新当前结果 */
		else if( ThisSum < 0 ) /* 如果当前子列和为负 */
			ThisSum = 0; /* 则不可能使后面的部分和增大，抛弃之 */
		printf("(%d,%d)\n",ThisSum,MaxSum);
	}
//	return MaxSum;  
}
int main()
{
	int i, n;
//	while(scanf("%d", &n) != EOF)
//	{
//		for (i = 0; i < n; ++i)
//		{
//			scanf("%d", &a[i]);
//		}
//		printf("%d\n", maxSubList1(a, n));
//	}
	printf("%d\n",maxSubList3(a,0,7));
	return 0;
}
