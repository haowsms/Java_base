package com.base.thread;

public class Volatile1 {
	
	public class PrintString implements Runnable{
		private boolean isContinuePrint = true;
		private boolean isContinuePrint(){
			return isContinuePrint;
		}
		
		public void setContinuePrint(boolean isContinuePrint) {
			this.isContinuePrint = isContinuePrint;
		}
		
		public void printStringMethod(){
			try {
				while (isContinuePrint){
					System.out.println("run printStringMethod threadName="
							+ Thread.currentThread().getName());
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			this.printStringMethod();
			System.out.println("已经停止了"+ Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {
		PrintString printString = new Volatile1().new PrintString();
		new Thread(printString).start();
		System.out.println("我要停止它！stopThread="
				+ Thread.currentThread().getName());
		printString.setContinuePrint(false);
	}
}
