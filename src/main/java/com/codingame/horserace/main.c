#include <stdlib.h>
#include <stdio.h>
#include <string.h>

static void splitMerge(int *a, int i0, int iN, int *b, int d);
static void merge(int *a, int i0, int iM, int iN, int *b);

int main()
{
    int N;
    scanf("%d", &N);
    int *p = (int *)malloc(N * sizeof(int));
    for (int i = 0; i < N; i++) {
        int Pi;
        scanf("%d", &Pi);
        p[i] = Pi;
    }

    int dl = (1 << 31) - 1;
    int d;
    int *ps = (int *)malloc(N * sizeof(int));
    memcpy(ps, p, N * sizeof(int));

    splitMerge(p, 0, N, ps, 0);

    for(int i = 0; i < N - 1; ++i) {
        d = p[i] - p[i+1];
        if(d < 0) {
            d = -d;
        }
        if(d < dl) {
            dl = d;
        }
    }

    // Write an action using printf(). DON'T FORGET THE TRAILING \n
    // To debug: fprintf(stderr, "Debug messages...\n");

    printf("%d\n", dl);

    return 0;
}

void splitMerge(int *b, int i0, int iN, int *a, int d) {
    if(iN - i0 < 2) {
        return;
    }
    int iM = (iN + i0) / 2;
    splitMerge(a, i0, iM, b, d + 1);
    splitMerge(a, iM, iN, b, d + 1);
    merge(b, i0, iM, iN, a);
}

void merge(int *a, int i0, int iM, int iN, int *b) {
    int i = i0, j = iM;

    for (int k = i0; k < iN; k++) {
        if (i < iM && (j >= iN || a[i] <= a[j])) {
            b[k] = a[i];
            ++i;
        } else {
            b[k] = a[j];
            ++j;
        }
    }
}
