#include <stdio.h>
#include <stdlib.h>

// ���������һ��ͨ�ú��������Դ���ͬ��λ��
void radixSort(int* arr, int n, int exp) {
    int output[n]; // �������
    int i, count[10] = {0};

    // ����ÿ����λ�ĳ��ִ���
    for (i = 0; i < n; i++) {
        count[(arr[i] / exp) % 10]++;
    }

    // ���� count[i]��ʹ�����ʵ��λ����Ϣ
    for (i = 1; i < 10; i++) {
        count[i] += count[i - 1];
    }

    // �����������
    for (i = n - 1; i >= 0; i--) {
        output[count[(arr[i] / exp) % 10] - 1] = arr[i];
        count[(arr[i] / exp) % 10]--;
    }

    // ��������ƻ�ԭ����
    for (i = 0; i < n; i++) {
        arr[i] = output[i];
    }
}

// ��������������ͬ�Ĳ���
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
// ������
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

    // ��������
    sortAtPass(a, num, pass);

    // �������������
    for (i = 0; i < num; i++) {
        printf("%d,", a[i]);
        
    }

    return 0;
}
