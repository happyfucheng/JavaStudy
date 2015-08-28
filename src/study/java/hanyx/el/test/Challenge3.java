package study.java.hanyx.el.test;

import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;

import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.FelContext;

public class Challenge3 {
	
	private static final String el = "单价*数量+运费";
	
	public static void main(String[] args) {
		int times = 100000;
		
		long time1 = System.currentTimeMillis();
		
		for (int i = 0; i < times; i++) {
			mvEL();
		}
		
		long time2 = System.currentTimeMillis();
		
		for (int i = 0; i < times; i++) {
			fastEL();
		}
		
		long time3 = System.currentTimeMillis();
		
		for (int i = 0; i < times; i++) {
			aviatorEl();
		}
		
		long time4 = System.currentTimeMillis();
		
		System.out.println("Fast-EL耗时: " + (time3-time2) + " ms");
		System.out.println("Aviator耗时: " + (time4-time3) + " ms");
		System.out.println("MVEL耗时: " + (time2-time1) + " ms");
	}
	
	private static void fastEL() {
//		FelEngine fel = new FelEngineImpl();
		FelContext ctx = FelEngine.instance.getContext();
		ctx.set("单价", 5000);
		ctx.set("数量", 12);
		ctx.set("运费", 7500);
		FelEngine.instance.eval(el);
	}
	
	private static void aviatorEl() {
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("单价", 5000);
		params.put("数量", 12);
		params.put("运费", 7500);
		AviatorEvaluator.execute(el,params);
	}
	
	private static void mvEL() {
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("单价", 5000);
		params.put("数量", 12);
		params.put("运费", 7500);
		MVEL.eval(el, params);  
	}
}
