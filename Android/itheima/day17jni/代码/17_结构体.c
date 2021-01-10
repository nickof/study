#include<stdlib.h>  
#include<stdio.h>  
/*
class Student{
      short age;
      char gender;
      int score;
      void study(){
           sytem.out.println("");
           }
      }
Student stu = new Student();
结构体的大小 大于或者等于所有成员大小的和 并且是最大的成员大小的整数倍 
//函数指针的定义方式 返回值(*函数指针的名字)(形参) 
*/ 
typedef struct Student{
      short age;  //2
      char gender;  //1
      double score;    //4
      void(*p)();
      } stud;
      
void study(){
           printf("good good study,day day up!");
           }
main(){ 
        //struct关键字 不能丢掉 
    //struct Student stu = {18,'f',100.0};  
    stud stu = {18,'f',100.0};
    stu.p = &study;
    printf("年龄= %hd\n",stu.age);
    printf("性别= %c\n",stu.gender);
    stu.p();
   // stu.score = 60;
   // printf("成绩= %d\n",stu.score);
    printf("stu结构体占%d个字节\n",sizeof stu);
     
    //声明了一个结构体的一级指针 这个指针指向了stu这个变量 
    struct Student* stup = &stu;
    //结构体指针访问结构体变量的成员 (*结构体指针).成员 
    (*stup).age = 80; 
    //结构体指针访问结构体变量的第二种方式 指针->成员 
    stup->p();
    
    struct Student** p2 = &stup; 
    
    (**p2).age = 70;
    printf("年龄= %hd\n",stu.age);
    (*p2)->age = 19;
    printf("年龄= %hd\n",stu.age);
       system("pause"); 
       } 
