package com.base.thread.reentrantReadWriteLock.read;


public class Run {
	public static void main(String[] args) {
		Service service = new Service();
		MyThreadA threadA = new MyThreadA(service);
		threadA.setName("A");
		MyThreadB threadB = new MyThreadB(service);
		threadB.setName("B");
		threadA.start();
		threadB.start();
	}
}
