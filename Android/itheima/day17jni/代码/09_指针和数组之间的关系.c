#include<stdlib.h>  
#include<stdio.h> 
// 数组占用一个块连续的内存 
// 指针变量在做 p+1 +2 这样的位移运算 每+1之后移动几个字节 取决于指针变量的类型
// int* p  p+1之后移动4个字节 char* p p+1之后移动1个字节 

main(){ 
        //声明一个char数组 
     // char arr[] = {'a','b','c','d','\0'};
      int arr[] = {10,20,30,40,50};
      printf("arr[0]的地址是%#x\n",&arr[0]);
       printf("arr[1]的地址是%#x\n",&arr[1]);
        printf("arr[2]的地址是%#x\n",&arr[2]);
         printf("arr[3]的地址是%#x\n",&arr[3]);
        
        printf("数组名字arr的地址是%#x\n",&arr);
        
        char* p = &arr[0];
       // int* p = arr;
        /*
         printf("*(p+0)=%c\n",*(p+0)); 
          printf("*(p+1)=%c\n",*(p+1)); 
           printf("*(p+2)=%c\n",*(p+2));  
           printf("*(p+3)=%c\n",*(p+3)); 
           */
           printf("p+0 = %#x\n",p+0);
           printf("p+1 = %#x\n",p+1);
           printf("p+2 = %#x\n",p+2);
           printf("p+3 = %#x\n",p+3);
         
            printf("*(p+0)=%d\n",*(p+0)); 
          printf("*(p+1)=%d\n",*(p+1)); 
           printf("*(p+2)=%d\n",*(p+2));  
           printf("*(p+3)=%d\n",*(p+3)); 
           printf("*(p+4)=%d\n",*(p+4)); 
            
       system("pause"); 
       } 
