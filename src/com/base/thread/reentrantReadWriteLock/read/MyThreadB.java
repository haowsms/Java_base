package com.base.thread.reentrantReadWriteLock.read;

public class MyThreadB extends Thread {
	private Service service;

	public MyThreadB(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		super.run();
		service.read();
	}
}
