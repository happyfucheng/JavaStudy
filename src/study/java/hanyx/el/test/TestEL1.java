package study.java.hanyx.el.test;

import org.mvel2.MVEL;

import study.java.hanyx.el.simpleImpl.InvokeEL;
import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;

public class TestEL1 {
	
	private static final String el = "1000+100.0*99-(600.0-3*15)/(((68-9)-3)*2-100)+10000%7*71";
	
	public static void main(String[] args) {
		
		long time1 = System.currentTimeMillis();
		
		mvEL();
		
		long time2 = System.currentTimeMillis();
		
		fastEL();
		
		long time3 = System.currentTimeMillis();
		
		aviatorEl();
		
		long time4 = System.currentTimeMillis();
		
		InvokeEL();
		
		long time5 = System.currentTimeMillis();
		
		System.out.println("MVEL耗时: " + (time2-time1) + " ms");
		System.out.println("Fast-EL耗时: " + (time3-time2) + " ms");
		System.out.println("Aviator耗时: " + (time4-time3) + " ms");
		System.out.println("InvokeEL耗时: " + (time5-time4) + " ms");
	}

	private static void InvokeEL() {
		System.out.println("InvokeEL结果:" + InvokeEL.instance.eval(el));
	}
	
	private static void fastEL() {
		FelEngine fel = new FelEngineImpl();      
		System.out.println("Fast-EL结果:" + fel.eval(el));
	}
	
	private static void aviatorEl() {
		System.out.println("Aviator结果:" + AviatorEvaluator.execute(el));
	}
	
	private static void mvEL() {
		System.out.println("MVEL结果:" + MVEL.eval(el));
	}
}
