package com.base.thread.single;

public class MyObject4 {
	private static MyObject4 myObject;
	private MyObject4(){
	}
	
	static {
		myObject = new MyObject4();
	}
	
	public static MyObject4 getInstance(){
		return myObject;
	}
}
