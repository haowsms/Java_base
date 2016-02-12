package com.base.thread;

public class Volatile2 {
	
	public class PrintString extends Thread{
		private boolean isContinuePrint = true;
		private boolean isContinuePrint(){
			return isContinuePrint;
		}
		
		public void setContinuePrint(boolean isContinuePrint) {
			this.isContinuePrint = isContinuePrint;
		}
		
		public void printStringMethod(){
			
		}
		
		@Override
		public void run() {
			System.out.println("进入run");
			try {
				while (isContinuePrint){
					System.out.println("ss");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("run已经停止了"+ Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		PrintString printString = new Volatile2().new PrintString();
		printString.start();
		Thread.sleep(1000);
		printString.setContinuePrint(false);
		System.out.println("我要停止它！stopThread="
				+ Thread.currentThread().getName());
	}
}
