#include<stdio.h>

/*n = 1 ����������ֿ����ۣ���������ʽ���У�nΪ1ʱ���Ϊ1��
  ���ϣ���˺ϲ���һ��
 */
int josephus(int n, int m)
{
	int i, temp = 0;
	for (i = 1; i <= n; i++) 
		temp = (temp +  m + 1) % i;
	return temp+1;
}
int Josephus(int n, int m)
{
	if (n = 1)
		return 0;
	else
		return (Josephus(n-1,m) + m + 1) % n;
}

int main()
{
	int n,m;
	scanf("%d %d",&n,&m);
	printf("%d",josephus(n,m));
	return 0;
}
