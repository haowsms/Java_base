package com.base.thread.reentrantLock.productcusumer.mutil;

public class Run {
	public static void main(String[] args) {
		MyService service = new MyService();
		MyThreadA[] threadA = new MyThreadA[10];
		MyThreadB[] threadB = new MyThreadB[10];
		for (int i = 0; i < 10; i++) {
			threadA[i] = new MyThreadA(service, i);
			threadB[i] = new MyThreadB(service, i);
			threadA[i].start();
			threadB[i].start();
		}
	}
}
