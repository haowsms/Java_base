package com.jvm.introduction;

public class PermTest {
	public static void main(String[] args) {
		int i =0;
		try {
			for (int j = 0; j < 100000; j++) {
				CglibBean  bean = new CglibBean("");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
