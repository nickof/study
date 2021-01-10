#android下JNI开发
##what 什么是JNI
* JNI java native interface native本地  java本地接口
* 通过JNI可以实现java和本地代码之间相互调用
* jni可以看做是翻译 实际上就是一套协议
## why 为什么要用JNI
* Java 一处编译到处运行
	* ①java运行在虚拟机上 无法直接操作硬件 JNI可以扩展java虚拟机的能力 让java代码可以调用驱动
	* ②java是解释型语言 运行效率相对较低 C/C++的效率要高很多 通过jni把耗时操作方法C/C++可以提高java运行效率
	* ③ java代码编译成的.class 文件安全性较差, 可以通过jni 把重要的业务逻辑放到c/c++去实现,c/c++反编译比较困难 安全性较高
* C历史悠久 1972年C 通过JNI可以调用优秀的C开源类库 

##How 怎么用JNI
* java
* c/c++ 能看懂 会调用
* JNI开发流程 NDK native develop kit  

##C基本语法

### CHelloWorld
	#include<stdio.h>    // 相当于 java的import .h c的头文件 stdio.h standard io 标准输入输出 
	#include<stdlib.h>   // stdlib standard library 标准函数库    java.lang 
	/**
	*/
	main(){    // public static void main(String[] args)
	       printf("helloworld!\n");  //System.out.println();   "\n"换行符 
	       system("javac Hello.java");
	       system("java Hello");
	       system("notepad");
	       system("pause"); //system执行windows的系统命令 
	       } 

### C的基本数据类型 
* java基本数据类型  C基本数据类型
boolean  1   
byte     1
char     2         char    1个字节
short    2         short   2 
int      4         int     4
long     8         long    4
float    4        float    4
double   8        double   8

char, int, float, double, long, short, signed, unsigned, void
* signed 有符号数 最高位是符号位 可以表示负数 但是表示的最大值相对要小 
* unsigned 无符号数 最高位是数值位 不可以表示负数  表示的最大值相对要大
* signed unsigned 只能用来修饰整形变量 char short int long
* C没有 boolean  byte C用0和非0表示false true
### C的输出函数 printf
	%d  -  int
	%ld – long int
	%lld - long long
	%hd – 短整型
	%c  - char
	%f -  float
	%lf – double
	%u – 无符号数
	%x – 十六进制输出 int 或者long int 或者short int
	%o -  八进制输出
	%s – 字符串
* printf("含有占位符的要输出的字符串",.....);	
* 占位符不要乱用 要选择正确的对应类型 否则可能会损失精度
* C字符串
	* C没有String类型 C的字符串实际就是字符数组
	* C数组定义 [ ]只能再变量名之后
	* C字符串两种定义方式
	
			char str[] = {'h','e','l','l','o','\0'};//注意'\0'字符串结束符
			char str[] = "你好"; //这种定义方式不用写结束符 可以表示汉字

### C的输入函数
* scanf("占位符", &地址);
* & 取地址符
* C字符串不检查下标越界 使用时要注意
### 内存地址的概念
* 声明一个变量,就会立即为这个变量申请内存,一定会有一个对应的内存地址
* 没有地址的内存是无法使用的
* 内存的每一个字节都有一个对应的地址
* 内存地址用一个16进制数来表示
* 32位操作系统最大可以支持4G内存
	* 32位系统的地址总线为32位,也就是说系统有2^32个数字可以分配给内存作为地址使用
### 指针概念    ******
	 		int i = 123;
	       //一般计算机中用16进制数来表示一个内存地址 
	       printf("%#x\n",&i); 
	       //int* int类型的指针变量  pointer指针  指针变量只能用来保存内存地址
	       //用取地址符&i 把变量i的地址取出来 用指针变量pointer 保存了起来
	       //此时我们可以说 指针pointer指向了 i的地址 
	       int* pointer = &i;   
	       printf("pointer的值 = %#x\n",pointer);
	       printf("*pointer的值%d\n",*pointer);
	       *pointer = 456;
	       printf("i的值是%d\n",i);
	       system("pause"); 
* 指针常见错误
	* 声明了指针变量后 未初始化直接通过*p 进行赋值操作 运行时会报错
		* * 未初始化直接使用的指针称为野指针
	* 指针类型错误 如int* p 指向了double类型的地址, 通过指针进行读取操作时,读取值会出错

### 指针的练习
* 值传递和引用传递(交换两个数的值)
	* 引用传递本质是把地址传递过去
	* 所有传递其实本质都是值传递，引用传递其实也是传递一个值，但是这个值是一个内存地址
	
			void swap(int* p, int* p2){
				int temp = *p;
				*p = *p2;
				*p2 = temp;	
			}
			main(){
				int i = 123;
				int j = 456;
				//将i, j的地址传递过去
				swap(&i,&j);
				printf("i = %d, j = %d", i, j);
			}
* 返回多个值
	* 把地址作为参数传入函数中，当函数执行完毕时，参数的值就已经被修改了
	
			"*" 号几种含义		
			int* p 声明一个int类型的指针变量
			*p   对p中保存的内存地址对应的内存进行操作
			i * j   乘法运算 
### 数组和指针的关系
* 数组占用的内存空间是连续的
* 数组名字变量保存的是第0个元素地址，也就是首地址
* 可以通过一个指针变量来保存一个数组的首地址
* 拿到了首地址之后 就可以通过指针的位移运算 遍历数组的每一个元素
* *(p + 1):指针位移一个单位，一个单位是多少个字节，取决于指针的类型
* 区分指针类型是为了指针位移运算方便

		main(){  
	      //char array[] ={'a','b','c','d','\0'};
	      int array[] = {1,2,3,4,5};
	      printf("array[0]的地址是%#x\n",&array[0]);
	      printf("array[1]的地址是%#x\n",&array[1]);
	      printf("array[2]的地址是%#x\n",&array[2]);
	      printf("array[3]的地址是%#x\n",&array[3]);
	      printf("array的地址是%#x\n",&array);
	   
	      char* p = &array[0];
	      //int* p = &array;
	      //char* p = &array;
	      printf("p+0 = %#x\n",p+0);
	      printf("p+1 = %#x\n",p+1);
	      printf("p+2 = %#x\n",p+2);
	      printf("p+3 = %#x\n",p+3);
	      /*
	      printf("*(p+0) = %c\n",*(p+0));
	      printf("*(p+1) = %c\n",*(p+1));
	      printf("*(p+2) = %c\n",*(p+2));
	      printf("*(p+3) = %c\n",*(p+3));
	      */
	      printf("*(p+0) = %d\n",*(p+0));
	      printf("*(p+1) = %d\n",*(p+1));
	      printf("*(p+2) = %d\n",*(p+2));
	      printf("*(p+3) = %d\n",*(p+3));
	      printf("*(p+3) = %d\n",*(p+4));
	      
	      system("pause"); 
	       }

###指针的长度
* 不管变量的类型是什么，它的内存地址的长度一定是相同的
* 32位环境下，内存地址长度都是4个字节，所以指针变量长度只需4个字节即可
* 64位环境下 指针变量的长度都是8个字节
 
		main(){  
	      int* p;
	      double* p1;
	      printf("p占%d个字节\n",sizeof p); 
	      printf("p1占%d个字节\n",sizeof p1); 
	      system("pause"); 
	       }  
### 多级指针
* int* p; int 类型的一级指针 int** p2; int 类型的二级指针 
* 二级指针变量只能保存一级指针变量的地址
* 有几个* 就是几级指针 int*** 三级指针
* 通过int类型三级指针 操作int类型变量的值 ***p

			int i = 123;
			//int类型一级指针 
			int* p = &i;
			//int 类型 二级指针 二级指针只能保存一级指针的地址 
			int** p2 = &p;
			//int 类型 三级指针  三级指针只能保存二级指针的地址 
			int*** p3 = &p2;
			//通过p3 取出 i的值
			printf("***p3 = %d\n", ***p3);
		
* 多级指针案例 取出子函数中临时变量的地址
* 实际上也是一个值传递引用传递的问题 
* 就是要通过子函数修改main函数中变量p的值 要用引用传递把p的地址传过去

		void function(int** p1){
	     int i = 123;
	     *p1 = &i;
	     printf("i的地址%#x\n",&i);
	     }
		main(){  
	      int* p;
	      function(&p);
	       printf("*p = %d\n",*p); 
	      printf("p的值%#x\n",p);
	     
	      system("pause"); 
	       }


##堆栈概念 静态内存分配 动态内存分配
* 栈内存
	* 系统自动分配
	* 系统自动销毁
	* 连续的内存区域
	* 向低地址扩展
	* 大小固定
	* 栈上分配的内存称为静态内存 
* 静态内存分配
 * 子函数执行完，子函数中的所有局部变量都会被销毁，内存释放，但内存地址不可能被销毁，只是地址上的值没了
* 堆内存
	* 程序员手动分配
		* java：new
		* c：malloc
	* 空间不连续
	* 大小取决于系统的虚拟内存
	* C：程序员手动回收free
	* java：自动回收
	* 堆上分配的内存称为动态内存
### 结构体
* 结构体中的属性长度会被自动补齐，这是为了方便指针位移运算
	* 结构体的大小 
		* ① 大于等于所有元素的和
        * ② 是最大的元素大小的整数倍 
	
* 结构体中不能定义函数，可以定义函数指针 
* 程序运行时，函数也是保存在内存中的，也有一个地址
* 结构体中只能定义变量
* 函数指针其实也是变量，它是指针变量
* 函数指针的定义 返回值类型(*变量名)(接收的参数);
* 函数指针的赋值: 函数指针只能指向跟它返回值和接收的参数相同的函数

		typedef struct Student{
		      double score;  //8
		      short age;    //2
		      char sex;     //1
		      //声明了一个函数指针study 
		     void (*study)();
		      } s;
		 void study(){
		           printf("好好学习,天天向上!\n");
		           }
		main(){  
		   s stu = {99.9,18,'f',&study};
		   stu.age = 20;
		   stu.study();
		   printf("stu.score = %.1lf\n",stu.score);
		   printf("stu.age = %hd\n",stu.age);
		   printf("stu.sex = %c\n",stu.sex);
		   printf("stu的大小占%d个字节\n",sizeof(stu));
		   //声明了一个结构体指针p 来保存结构体变量stu的地址 
		   struct Student* p = &stu;
		   (*p).age = 30;
		   //-> 间接引用运算符 
		   p->age = 40;
		   p->study();
		    printf("stu.age = %hd\n",stu.age);
		      system("pause"); 
		       } 
	
### 联合体
* 长度等于联合体中定义的变量当中最长的那个
* 联合体只能保存一个变量的值
* 联合体共用同一块内存
###枚举
* 枚举的作用 规定了 enum weekday 的取值范围 只能取枚举值
* 默认枚举值从0开始 每个元素依次+1 

###结构体的练习
* C面向过程的而语言  以函数为中心
* java面向对象的语言 以数据(对象为中心)

	
		#include<stdlib.h>   
		#include<stdio.h>   
		
		//给结构体struct Light 起别名 light
		typedef struct Light light;
		struct Light{
		       //用来表示等开关的状态 
		       int state;
		       //声明两个函数指针 
		       //关灯的函数指针 通过函数修改light的状态 
		       //要接收一个light的地址 所以形参为light*类型 
		       void(*turnOff)(light*);
		       //开灯的函数指针 
		       void(*turnON)(light*);
		       };
		//关灯的函数 
		void turnOff(light* l){
		     //通过结构体的指针修改结构体的变量state 
		     l->state = 0;
		     printf("关灯\n");
		     }
		//开灯的函数 
		void turnON(light* l){
		     //通过结构体的指针修改结构体的变量state 
		     l->state = 1;
		     printf("开灯\n");
		     }
		//模仿一个构造方法 做两件事儿 ① 申请一块堆内存 ② 给结构体的成员赋初始值 
		light* newLight(){
		     //申请一块堆内存 用来保存light这个结构体的实例 
		     light* led = (light*)malloc(sizeof(light));
		     //初始化 
		     led->state = 0;
		     //给函数指针turnOff赋初始值 使函数指针指向具体的函数 
		     led->turnOff = &turnOff;
		     // 给函数指针turnON赋初始值 使函数指针指向具体的函数 
		     led->turnON = &turnON;
		     return led;
		     }
		main(){
		       //调用模仿的构造方法 返回一个结构体Light的引用  
		      light* led = newLight();
		      //调用结构体中turnON开灯这个函数指针 
		      led->turnON(led);
		      printf("led->state = %d\n",led->state);
		      system("pause"); 
		       }

