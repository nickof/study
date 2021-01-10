#include<stdlib.h>  
#include<stdio.h>  
//常见错误① 野指针  指针变量没有初始化 就用 *p= 进行赋值 修改内存对应的内容 
//②指针声明的类型 要跟保存地址的类型一一对应 int类型的指针变量 指向int变量的地址
//double类型的指针变量指向double类型的地址 .... 
main(){ 
        int i;
        double d = 1.23345;
      double* p = &d;//声明了一个变量 就是给这个变量分配了一块内存空间 
      printf("p=%#x\n",p);
      //野指针 指针变量要初始化之后再去做 *p 赋值的操作
      //拿着当前程序中的变量地址 给指针变量进行初始化 
      *p = 123.45; 
     // printf("i = %d\n",i);
     printf("d = %lf\n",d);
       system("pause"); 
       } 
