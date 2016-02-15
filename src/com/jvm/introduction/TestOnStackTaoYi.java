package com.jvm.introduction;

public class TestOnStackTaoYi {
	private static User u;
	public static void alloc(){
		u = new User();
		u.id=5;
		u.name="geym";
	}
	
	public static void alloc2(){
		User u = new User();
		u.id=5;
		u.name="geym";
	}
	
	public static class User{
		public int id=0;
		public String name="";
	}
	
	public static void main(String[] args) {
		long b = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			alloc();
		}
		long e = System.currentTimeMillis();
		System.out.println(e-b);
	}
	
}
