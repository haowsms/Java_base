package com.jvm.introduction;

public class TestStackLocalVarGC {
	public static void localvarGc1() {
		byte[] a = new byte[6 * 1024 * 1024];
		System.gc();
	}

	public static void localvarGc2() {
		byte[] a = new byte[6 * 1024 * 1024];
		a = null;
		System.gc();
	}

	public static void localvarGc3() {
		{
			byte[] a = new byte[6 * 1024 * 1024];
		}
		System.gc();
	}

	public static void localvarGc4() {
		{
			byte[] a = new byte[6 * 1024 * 1024];
		}
		int c = 0;
		System.gc();
	}

	public static void localvarGc5() {
		localvarGc1();
		System.gc();
	}

	public static void main(String[] args) {
		TestStackLocalVarGC ins = new TestStackLocalVarGC();
		ins.localvarGc5();
	}
}
