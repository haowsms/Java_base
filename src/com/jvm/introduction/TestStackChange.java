package com.jvm.introduction;

public class TestStackChange {
	public static void localvar1() {
		int a=0;
		System.out.println(a);
		int b=0;
	}
	public static void localvar2() {
		{
			int a=0;
			System.out.println(a);
		}
		int b=0;
	}
}
