package com.base.thread;

public class StopSleepThread {
	public class MyThread extends Thread {
		@Override
		public void run() {
			super.run();
			try {
				System.out.println("run begin");
				Thread.sleep(20000);
				System.out.println("run end");
			} catch (InterruptedException e) {
				System.out.println("停止沉睡中的线程, 被捕获，线程状态:" + this.isInterrupted());
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			MyThread thread = new StopSleepThread().new MyThread();
			thread.start();
			Thread.sleep(200);
			thread.interrupt();
		} catch (Exception e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}
