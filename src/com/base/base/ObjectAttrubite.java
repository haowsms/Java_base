package com.base.base;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ObjectAttrubite {

	@Test
	public void hashCodeAndEquals(){
		String s1 = new String("test1");
		String s2 = new String("test1");
		Map<String, String> map = new HashMap<String, String>();
		map.put(s1, "s");
		map.put(s2, "s1");
		System.out.println(map.size());
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s2);
	}
}