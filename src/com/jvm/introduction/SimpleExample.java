package com.jvm.introduction;

public class SimpleExample {
	private int id;
	public SimpleExample(int id) {
		this.id = id;
	}
	public void print(){
		System.out.println("id="+id);
	}
	
	public static void main(String[] args) {
		SimpleExample simple1 = new SimpleExample(1);
		SimpleExample simple2 = new SimpleExample(2);
		simple1.print();
		simple2.print();
	}
}
