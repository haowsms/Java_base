package com.base.thread.reentrantLock.productcusumer.one;

public class MyThreadB extends Thread {
	private MyService service;
	public MyThreadB(MyService service) {
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 100000; i++) {
			service.get();
		}
	}
}
