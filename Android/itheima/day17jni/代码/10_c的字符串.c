#include<stdlib.h>  
#include<stdio.h>  
//c �ַ��� ��õĶ��巽ʽ  char* str = "hello" ;
//char str[] = "hello";
//char* p = &str;   str �� &str 
//char* p = str; 

main(){ 
      char str[] = {'a','b','c','d','\0'};
     // str[] ="hello";
      char* p = &str[0];
      p = str;
      char str2[] = "hello";
      //p = &str2;
      p = str2;
     char* p1 = "hello";
       printf("&str[0] = %#x\n",&str[0]);
      printf("str = %#x\n",str);
      printf("p1 = %#x\n",p1);
       system("pause"); 
       } 
