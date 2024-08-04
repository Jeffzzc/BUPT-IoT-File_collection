#include <stdio.h>
#include <stdlib.h>

// 基数排序的一个通用函数，可以处理不同的位数
void radixSort(int* arr, int n, int exp) {
    int output[n]; // 输出数组
    int i, count[10] = {0};

    // 计算每个数位的出现次数
    for (i = 0; i < n; i++) {
        count[(arr[i] / exp) % 10]++;
    }

    // 更改 count[i]，使其包含实际位置信息
    for (i = 1; i < 10; i++) {
        count[i] += count[i - 1];
    }

    // 构建输出数组
    for (i = n - 1; i >= 0; i--) {
        output[count[(arr[i] / exp) % 10] - 1] = arr[i];
        count[(arr[i] / exp) % 10]--;
    }

    // 将结果复制回原数组
    for (i = 0; i < n; i++) {
        arr[i] = output[i];
    }
}

// 主排序函数，处理不同的步骤
void sortAtPass(int* arr, int n, int pass) {
    int exp = 1;
    int i;
    for (i = 0; i < pass; i++) {
        radixSort(arr, n, exp);
        exp *= 10;
    }
}
void getArr(char array[], int newarr[]) {
	int i = 0;
	int j = 0;
	int tmp = 0;
	while(array[i] != '\0') {
		while(array[i] != ',') {
			tmp *= 10;
			tmp += (array[i] - '0');
			i++;
		}
		newarr[j] = tmp;
		
		tmp = 0;
		i++;
		j++;
	}
	newarr[j] = -1;
}
// 主函数
int main() {
	int i;
    int a[20];
    int num = 0;
    char array[10000];
    scanf("%s", array);
    int arr[10000];
    getArr(array,arr);
    while (arr[num]!=-1){
        a[num] = arr[num];
        num++;
    }

    int pass;
    scanf("%d", &pass);

    // 进行排序
    sortAtPass(a, num, pass);

    // 输出排序后的数组
    for (i = 0; i < num; i++) {
        printf("%d,", a[i]);
        
    }

    return 0;
}
