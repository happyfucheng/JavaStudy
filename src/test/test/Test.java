package test.test;

import java.util.HashMap;

public class Test {

	public static void main(String[] args) {
		
		long t2 = System.currentTimeMillis();
		HashMap<String,String> map2 = new HashMap<String,String>(3355443, 0.75f);
		for(int i=0;i<2000000;i++) {
			map2.put(i+"", i+"");
		}
		System.out.println(System.currentTimeMillis() - t2);
		
		
		
		long t1 = System.currentTimeMillis();
		HashMap<String,String> map1 = new HashMap<String,String>(16, 0.75f);
		for(int i=0;i<2000000;i++) {
			map1.put(i+"", i+"");
		}
		System.out.println(System.currentTimeMillis() - t1);
		
		long t3 = System.currentTimeMillis();
		HashMap<String,String> map3 = new HashMap<String,String>(3355443, 0.75f);
		for(int i=0;i<2000000;i++) {
			map3.put(i+"", i+"");
		}
		System.out.println(System.currentTimeMillis() - t3);
		

	}
}
