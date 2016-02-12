package com.base.thread.reentrantLock.condition.mutil;

public class MyThreadA extends Thread{
	private MyService service;
	
	public MyThreadA(MyService service) {
		super();
		this.service = service;
	}
	@Override
	public void run() {
		this.service.awaitA();
	}
}
