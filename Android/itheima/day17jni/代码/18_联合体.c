#include<stdlib.h>  
#include<stdio.h>  
//������ �����������ͬһ���ڴ�  ��Сȡ�������ı��� 
//������ �Բ�ͬ��Ա��θ�ֵ ֻ�����һ�θ�ֵ��Ч 
union un{
      short s;
      int i;
      };
main(){ 
    union un u;
    printf("u�Ĵ�С%d\n",sizeof(u));
    u.s = 1234;
    u.i = 12345678;
    printf("u.s = %hd\n",u.s);
       system("pause"); 
       } 
