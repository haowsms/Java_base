package com.base.thread.reentrantLock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void await() {
		try {
			this.lock.lock();
			System.out.println("await时间为=" + System.currentTimeMillis());
			condition.await();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}

	public void signal() {
		try {
			this.lock.lock();
			System.out.println("signal时间为=" + System.currentTimeMillis());
			condition.signal();
		} finally {
			this.lock.unlock();
		}
	}
}
