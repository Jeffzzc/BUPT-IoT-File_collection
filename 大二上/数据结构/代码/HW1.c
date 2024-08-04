#include<stdio.h>
#include<time.h>

#include<stdlib.h>

int main()
{
	int N=100;
	time_t start_time=0, end_time=0;
	int Sum=0;
	int i,j,k;
	start_time=clock();
	Sum=0;
	for(i=0;i<N;i++)
		for(j=0;j<i*i;j++)
		
				Sum++;
	end_time=clock();
	printf("Time Used: %lf\n",(double)(end_time-start_time)/CLOCKS_PER_SEC);
	return 0;
	
}
