#include<stdlib.h>  
#include<stdio.h>  
function(int** q){
           int i = 123;
           *q = &i;
           printf("i�ĵ�ַ%#x\n",&i);
           }

main(){
       //����intָ�����p 
      int* p;
      function(&p);
      printf("p��ֵ%#x\n",p); 
       system("pause"); 
       } 
