#include<cstdio>
 
#pragma warning(disable :4996)
using namespace std;
 
void find_each_change(int a, int C[]) {
    C[0] = a / 25;
    a -= C[0] * 25;
    C[1] = a / 10;
    a -= C[1] * 10;
    C[2] = a / 5;
    a -= C[2] * 5;
    C[3] = a;
}
 
int main(){
    int T;
    int C[5] = { 0 };
    int cent;
    scanf("%d", &T);
    for (int i = 0; i < T; i++)
    {
        scanf("%d", &cent);
        find_each_change(cent, C);
        printf("%d %d %d %d\n", C[0], C[1], C[2], C[3]);
    }
    return 0;
}
 
