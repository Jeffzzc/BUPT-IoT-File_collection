#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define full 1
#define empty 0
#define delete 2
struct Node;
typedef struct Node *ptrtoNode;
typedef ptrtoNode position;

struct Node
{
    long long x;
    int kind;
};
void insert(long long x, ptrtoNode array[]);

int main()
{
    ptrtoNode array[10]; // 相当于hashtable
    for (int i = 0; i < 10; i++)
    {
        array[i] = malloc(sizeof(struct Node));
        array[i]->kind = empty;
    }

    long long x;
    char y;
    do
    {
        scanf("%lld", &x);
        insert(x, array);
    } while ((y = getchar()) != '\n' && y != EOF);

    for (int i = 0; i < 9; i++)
    {
        if (array[i]->kind == empty)
        {
            printf("0,");
        }
        else
        {
            printf("%lld,", array[i]->x);
        }
    }
    if (array[9]->kind == empty)
    {
        printf("0");
    }
    else
    {
        printf("%lld", array[9]->x);
    }

    return 0;
}
void insert(long long num, ptrtoNode array[])
{
    int pos, pos0; //pos0为最开始的下标
    int x = num % 10;
    pos = pos0 = x % 10;
    int i = 1;
    while (array[pos]->kind != empty)
    {
        pos = pos0 + pow(i, 2);
        i++;
        pos = pos % 10;
    }
    array[pos]->x = num;
    array[pos]->kind = full;
}

