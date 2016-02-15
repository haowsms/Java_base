package com.base.thread;

public class StopThread {
	public class MyThread extends Thread{
		@SuppressWarnings("static-access")
		@Override
		public void run() {
			super.run();
			for (int i = 0; i < 500000; i++) {
				if (this.interrupted()) {
					System.out.println("线程已经是停止状态。可以正常退出！");
					break;
				}
				System.out.println("i=" + (i+1));
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			MyThread thread = new StopThread().new MyThread();
			thread.start();
			Thread.sleep(2000);
			thread.interrupt();
		} catch (Exception e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}
