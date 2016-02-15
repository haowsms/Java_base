package com.base.thread;

public class SysnchronizedStaticTest {
	public static synchronized void mthdA(){
		System.out.println("run mthdA begin");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("run mthdA end");
	}
	
	public void mthdB(){
		synchronized (SysnchronizedStaticTest.class) {
			System.out.println("run mthdB begin");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("run mthdB end");
		}
	}
	public static void main(String[] args) {
		final SysnchronizedStaticTest t = new SysnchronizedStaticTest();
		new Thread(){
			public void run() {
				 t.mthdA();
			};
		}.start();
		
		new Thread(){
			public void run() {
				 t.mthdB();
			};
		}.start();
	}
}
