#include<stdlib.h>  
#include<stdio.h>  
/*
void swap(int i ,int j){
       int temp = i;
      i = j;
      j = temp;
     }
 */   
 //ֵ����      �����˱����б����ֵ 
 //���ô���    �������ڴ��ֵַ  ����ͨ��ָ����������������ַ��Ӧ���ڴ� 
     void swap(int* p ,int* q){
       int temp = *p;
      *p = *q;
      *q = temp;
     }

main(){ 
      int i = 123;
      int j = 456;
      //ֵ����  ͨ��ֵ���� ����ʵ�� �Ӻ����޸���ʱ������ֵ 
      swap(i,j);
      //ֻ�����ô��� �ܹ�ͨ�������Ӻ��� �޸���ʱ������ֵ 
    swap(&i,&j);
      printf("i = %d,j = %d\n",i,j);
      
       system("pause"); 
       } 
