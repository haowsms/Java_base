package com.base.thread.reentrantLock.productcusumer.mutil;

public class MyThreadA extends Thread {
	private MyService service;
	public MyThreadA(MyService service, int i) {
		this.service = service;
		this.setName(String.valueOf(i));
	}
	@Override
	public void run() {
		super.run();
		service.set();
	}
}
