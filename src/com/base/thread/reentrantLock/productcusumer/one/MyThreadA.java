package com.base.thread.reentrantLock.productcusumer.one;

public class MyThreadA extends Thread {
	private MyService service;
	public MyThreadA(MyService service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 100000; i++) {
			service.set();
		}
	}
}
