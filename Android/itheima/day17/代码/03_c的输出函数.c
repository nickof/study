#include<stdlib.h>  
#include<stdio.h>  
/*
%d  -  int
%ld �C long int
%lld - long long
%hd �C ������
%c  - char
%f -  float
%lf �C double
%u �C �޷�����
%x �C ʮ��������� int ����long int ����short int
%o -  �˽������
%s �C �ַ���

12345678 10111100 01100001 01001110
24910              1100001 01001110
*/

main(){ 
      char c = 'c';
      short s = 1234;
      int i = 12345678;
      long l = 1234567890;
      float f = 3.14;
      double d = 3.1415926;
      printf("c = %c\n",c); 
      printf("s = %hd\n",s); 
      printf("i = %d\n",i); 
      printf("l = %ld\n",l); 
      printf("f = %.2f\n",f); //printf���С����ʱ��Ĭ�ϱ���6λ��Ч���� ����ͨ��%.2f��ָ����Ч���ֵ�λ�� 
      printf("d = %.7lf\n",d); 
      
      printf("i�˽���%#o\n",i);
      printf("i16����%#x\n",i);// %#x %#o �����ǰ׺�İ˽��ƺ�ʮ��������
      //c�������� []������ڱ��������� 
      char str[] = {'a','b','c','d','\0'}; // c�����鲻���Խ�� \0 �����ַ��������ı�־ 
      char str1[] = "hello world! �������";//  
      printf("%s\n",str1);
       system("pause"); 
       } 
