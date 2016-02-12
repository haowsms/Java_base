package com.base.thread.reentrantReadWriteLock.readwrite;


public class Run {
	public static void main(String[] args) throws InterruptedException {
		Service service = new Service();
		MyThreadA threadA = new MyThreadA(service);
		threadA.setName("A");
		threadA.start();
		Thread.sleep(100);
		MyThreadB threadB = new MyThreadB(service);
		threadB.setName("B");
		threadB.start();
		System.out.println("开始运行b线程");
	}
}
