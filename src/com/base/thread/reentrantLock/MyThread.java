package com.base.thread.reentrantLock;

public class MyThread extends Thread{
	private MyService service;
	
	public MyThread(MyService service) {
		super();
		this.service = service;
	}
	@Override
	public void run() {
		this.service.testMethod();
	}
}
