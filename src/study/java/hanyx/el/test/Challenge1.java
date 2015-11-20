package study.java.hanyx.el.test;

import org.mvel2.MVEL;

import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.FelEngine;

public class Challenge1 {
	
	private static final String el = "1000+100.0*99-(600-3*15)/(((68-9)-3)*2-100)+10000%7*71";
//	private static final String el = "6.7-100>39.6 ? 5==5? 4+5:6-1 : !(100%3-39.0<27) ? 8*2-199: 100%3";
	
	public static void main(String[] args) {
		int times = 100000;
		
		long time1 = System.currentTimeMillis();
		
		for (int i = 0; i < times; i++) {
			mvEL();
		}
		
		long time2 = System.currentTimeMillis();

		System.out.println("MVEL��ʱ: " + (time2-time1) + " ms");
		
		for (int i = 0; i < times; i++) {
			fastEL();
		}
		
		long time3 = System.currentTimeMillis();

		System.out.println("Fast-EL��ʱ: " + (time3-time2) + " ms");
		
		for (int i = 0; i < times; i++) {
			aviatorEl();
		}
		
		long time4 = System.currentTimeMillis();
		
		System.out.println("Aviator��ʱ: " + (time4-time3) + " ms");
	}

	
	private static void fastEL() {
		FelEngine.instance.eval(el);
	}
	
	private static void aviatorEl() {
		AviatorEvaluator.execute(el);
	}
	
	private static void mvEL() {
		MVEL.eval(el);
	}
}
