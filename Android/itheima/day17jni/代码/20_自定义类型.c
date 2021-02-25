#include<stdlib.h>  
#include<stdio.h>  
typedef int i;

//用法
typedef struct Student{
	short age;
	char gender;
	double score;
	void(*p)();
} stud;

//用法jni,传参的时候可以增加代码的可读性
typedef void* jobject;
typedef jobject jclass;
typedef jobject jstring;

main(){ 
      i j = 123;
      printf("j = %d\n",j);
      system("pause"); 
	  stud stu={18,'f',100.0 }
	   
       } 
