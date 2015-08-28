package study.java.hanyx.el.test;

import org.mvel2.MVEL;

import study.java.hanyx.el.simpleImpl.InvokeEL;
import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;

public class TestEL2 {
	
	private static final String el = "6.7-100>39.6 ? 5==5? 4+5:6-1 : !(100%3-39.0<27) ? 8*2-199: 100%3";
	
	public static void main(String[] args) {
		//正确结果为 1
		//System.out.println(6.7-100>39.6 ? 5==5? 4+5:6-1 : !(100%3-39.0<27) ? 8*2-199: 100%3);
		
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
		Object result = fel.eval(el);
		System.out.println("Fast-EL结果:" + result);
	}
	
	private static void aviatorEl() {
		System.out.println("Aviator结果:" + AviatorEvaluator.execute(el));
	}
	
	private static void mvEL() {
		System.out.println("MVEL结果:" + MVEL.eval(el));
	}
}
