package com.jvm.introduction;

public class TestStackDeep {
	private static int count;

	public static void recursion(long x, long y, long z) {
		long a = 1, b = 2, c = 3, d = 4, e = 5, f = 6, g = 7, h = 8, i = 9, j = 10, k = 11, m = 12, n = 13;
		count++;
		recursion(x, y, z);
	}

	public static void main(String[] args) {
		try {
			recursion(0L, 0L, 0L);
		} catch (Throwable e) {
			System.out.println("deep of calling = " + count);
			e.printStackTrace();
		}
	}
}
