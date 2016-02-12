package com.base.thread.single;

public class MyObject2 {
	private static MyObject2 myObject2;
	
	private MyObject2() {
	}
	
	// 这里使用了DCL双检查锁机制来实现多线程环境中的延迟加载单例模式。
	public MyObject2 getInstance(){
		if (null == myObject2){
			synchronized (MyObject2.class) {
				if (null == myObject2) {
					myObject2 = new MyObject2();
				}
			}
		}
		return myObject2;
	}
}
