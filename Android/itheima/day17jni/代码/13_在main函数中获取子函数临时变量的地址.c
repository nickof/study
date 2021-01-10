#include<stdlib.h>  
#include<stdio.h>  
function(int** q){
           int i = 123;
           *q = &i;
           printf("i的地址%#x\n",&i);
           }

main(){
       //声明int指针变量p 
      int* p;
      function(&p);
      printf("p的值%#x\n",p); 
       system("pause"); 
       } 
