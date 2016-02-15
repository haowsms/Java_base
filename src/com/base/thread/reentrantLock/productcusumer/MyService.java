package com.base.thread.reentrantLock.productcusumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean hasValue = false;
	public void set(){
		try {
			lock.lock();
			while (hasValue) {
				System.out.println("有可能连续 设值------------:" + Thread.currentThread().getName());
				condition.await();
			}
			System.out.println("打印设值 完成:" + Thread.currentThread().getName());
			hasValue = true;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void get(){
		try {
			lock.lock();
			while(!hasValue){
				System.out.println("有可能连续 取值-----------------:" + Thread.currentThread().getName());
				condition.await();
			}
			System.out.println("打印获取值 完成:" + Thread.currentThread().getName());
			hasValue = false;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
