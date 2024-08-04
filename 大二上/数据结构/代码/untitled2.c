#include <stdio.h>
#include <time.h>

int fibonacci(int n) {
	if(n == 0 || n == 1) return 1;
	return fibonacci(n-1) + fibonacci(n-2);
}

int main() {
	int n = 0;
	while(~scanf("%d",&n)) {
		time_t start_time=0, end_time=0;
		start_time=clock();
		printf("fibonacci(%d)=%d\n",n,fibonacci(n));
		end_time=clock();
		printf("Time Used: %f\n",(double)(end_time-start_time)/CLOCKS_PER_SEC);
	}
	return 0;
}

