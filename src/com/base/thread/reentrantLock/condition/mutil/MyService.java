package com.base.thread.reentrantLock.condition.mutil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	private Lock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();
	private Condition conditionB = lock.newCondition();	
	public void awaitA() {
		try {
			this.lock.lock();
			System.out.println("begin awaitA时间为=" + System.currentTimeMillis()
			+ " ThreadName=" + Thread.currentThread().getName());
			conditionA.await();			
			System.out.println(" end awaitA时间为=" + System.currentTimeMillis()
			+ " ThreadName=" + Thread.currentThread().getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}
	
	public void awaitB() {
		try {
			this.lock.lock();
			System.out.println("begin awaitB时间为=" + System.currentTimeMillis()
			+ " ThreadName=" + Thread.currentThread().getName());
			conditionB.await();			
			System.out.println(" end awaitB时间为=" + System.currentTimeMillis()
			+ " ThreadName=" + Thread.currentThread().getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}

	public void signalAll_A() {
		try {
			this.lock.lock();
			System.out.println("signalAll_A时间为=" + System.currentTimeMillis()
			+ " ThreadName=" + Thread.currentThread().getName());
			
			conditionA.signalAll();
		} finally {
			this.lock.unlock();
		}
	}
		public void signalAll_B() {
			try {
				this.lock.lock();
				System.out.println("signalAll_B时间为=" + System.currentTimeMillis()
				+ " ThreadName=" + Thread.currentThread().getName());
				
				conditionB.signalAll();
			} finally {
				this.lock.unlock();
			}
	}
}
