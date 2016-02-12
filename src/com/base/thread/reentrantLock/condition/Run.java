package com.base.thread.reentrantLock.condition;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		MyService service = new MyService();
		MyThread a1 = new MyThread(service);
		a1.start();
		Thread.sleep(3000);
		service.signal();
	}
}
