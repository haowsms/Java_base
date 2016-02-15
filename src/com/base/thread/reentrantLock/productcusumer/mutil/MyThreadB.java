package com.base.thread.reentrantLock.productcusumer.mutil;

public class MyThreadB extends Thread {
	private MyService service;
	public MyThreadB(MyService service, int i) {
		this.service = service;
		this.setName(String.valueOf(i));
	}
	@Override
	public void run() {
		super.run();
		service.get();
	}
}
