#include<stdlib.h>  
#include<stdio.h>  
//scanf  
//c�����鲻���Խ�� ��Ҫ����Աע��  
main(){ 
      printf("������༶������\n");
      //����һ������ count�������û������� 
      int count;
      printf("count�ĵ�ַ%d\n",&count);
      scanf("%d",&count); //&ȡ��ַ���� 
      printf("�༶������Ϊ%d\n",count);
      printf("������༶������\n");
      //�����ַ����� �������հ༶������ 
      char name[18];
      scanf("%s",&name);
      printf("name�ĵ�ַ%d\n",&name);
      printf("�༶��������:%s,�༶������Ϊ%d\n",name,count); 
       system("pause"); 
       } 
