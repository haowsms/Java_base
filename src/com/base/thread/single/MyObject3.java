package com.base.thread.single;

public class MyObject3 {
	private static class MyObjectHandler {
		private static MyObject3 myObject = new MyObject3();
	}
	
	private MyObject3(){
		
	}
	
	public static MyObject3 getInstance(){
		return MyObject3.MyObjectHandler.myObject;
	}
}
