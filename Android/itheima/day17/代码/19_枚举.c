#include<stdlib.h>  
#include<stdio.h> 
//ͨ��enum����ö������ �������ȡֵֻ�ܴӶ���õ�ֵ��ȡ
//Ĭ�� ��һ��������ֵ��0 ����ı�������+1 �����ĳ������������ֵ �����ֵ����������Ļ�������+1 
enum Weekday{
     SUN,MON,TUE=4,WEND,THUR,FRI,SAT 
     }
main(){ 
    enum Weekday day = WEND;  
    printf("day = %d\n",day); 
       system("pause"); 
       } 
