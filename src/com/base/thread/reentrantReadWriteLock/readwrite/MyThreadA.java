package com.base.thread.reentrantReadWriteLock.readwrite;

public class MyThreadA extends Thread {
	private Service service;

	public MyThreadA(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		super.run();
		service.read();
	}
}
