package com.base.io;

public class TestIo {
	public int test1(){  
        int count = 1;  
        try{  
                 return ++count;  
        }finally{  
                 return ++count;  
        }  
}  
	
//以上代码最终返回值是：3  
public int test2(){  
        int count = 1;  
        try{  
                 return ++count;  
        }finally{  
                 return count++;  
        }  
}  
	public static void main(String[] args) {
		TestIo t = new TestIo();
		System.out.println(t.test1());
		System.out.println(t.test2());
}
}