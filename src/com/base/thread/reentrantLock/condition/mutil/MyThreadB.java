package com.base.thread.reentrantLock.condition.mutil;

public class MyThreadB extends Thread{
	private MyService service;
	
	public MyThreadB(MyService service) {
		super();
		this.service = service;
	}
	@Override
	public void run() {
		this.service.awaitB();
	}
}
