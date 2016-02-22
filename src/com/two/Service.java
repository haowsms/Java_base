package com.two;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private LinkedList<Data> array = new LinkedList<Data>();
	private boolean hasValue = false;
	private boolean isFinish = false;
	public void set(Data dt){
		try {
			lock.lock();
			while (hasValue) {
				condition.await();
			}
			array.add(dt);
			hasValue = true;
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public Data get(){
		try {
			lock.lock();
			while(!hasValue){
				condition.await();
			}
			Data dt = array.pollFirst();
			if (array.size() <= 0) {
				hasValue = false;
			}
			condition.signalAll();
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		return null;
	}
	
	/**
	 * 当前数据量
	 * @return 数据量
	 */
	public long getSize(){
		return array.size();
	}
}
