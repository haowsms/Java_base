package com.base.thread.single;

public class MyObject1 {
	private static MyObject1 myObject = new MyObject1();
	private MyObject1() {
	}
	
	public static MyObject1 getInstance(){
		// 此代码为立即加载
		// 缺点是不能有其他实例变量
		// 因为getInstance()方法没有同步，所以有可能出现非线程安全问题
		return myObject;
	}
}
