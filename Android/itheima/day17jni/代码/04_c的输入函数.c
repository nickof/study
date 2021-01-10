#include<stdlib.h>  
#include<stdio.h>  
//scanf  
//c的数组不检测越界 需要程序员注意  
main(){ 
      printf("请输入班级的人数\n");
      //定义一个变量 count来接收用户的输入 
      int count;
      printf("count的地址%d\n",&count);
      scanf("%d",&count); //&取地址符号 
      printf("班级的人数为%d\n",count);
      printf("请输入班级的名字\n");
      //定义字符数组 用来接收班级的名字 
      char name[18];
      scanf("%s",&name);
      printf("name的地址%d\n",&name);
      printf("班级的名字是:%s,班级的人数为%d\n",name,count); 
       system("pause"); 
       } 
