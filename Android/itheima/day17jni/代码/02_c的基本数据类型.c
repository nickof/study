#include<stdlib.h>  
#include<stdio.h>  
//sizeof() 
main(){ 
      printf("char类型占%d个字节\n",sizeof(char));
      printf("short类型占%d个字节\n",sizeof(short));
      printf("int类型占%d个字节\n",sizeof(int));
      printf("float类型占%d个字节\n",sizeof(float));
      printf("long类型占%d个字节\n",sizeof(long));
      printf("double类型占%d个字节\n",sizeof(double));
      
      unsigned char c = 256;
      printf("c = %d\n",c);
       system("pause"); 
       } 
