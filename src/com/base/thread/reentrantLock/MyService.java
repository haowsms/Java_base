package com.base.thread.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	private Lock lock = new ReentrantLock();
	public void testMethod(){
		try {
			this.lock.lock();
			for (int i = 0; i < 5; i++) {
				System.out.println("ThreadName=" + Thread.currentThread().getName() + (" " + (i +1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.lock.unlock();
		}
	}
}
