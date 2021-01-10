package com.itheima.swap;

public class SwapDemo {
	public static void main(String[] args) {
		int i = 123;
		int j = 456;
		swap(i, j);
		System.out.println("i = "+i+"j="+j);
	}

	public static void swap(int i ,int j){
	       int temp = i;
	       i = j;
	       j = temp;
	      }

}
