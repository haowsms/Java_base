package com.base.thread.reentrantLock.productcusumer.one;

public class Run {
	public static void main(String[] args) {
		MyService service = new MyService();
		MyThreadA threadA = new MyThreadA(service);
		MyThreadB threadB = new MyThreadB(service);
		threadA.start();
		threadB.start();
	}
}
