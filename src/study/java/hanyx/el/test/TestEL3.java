package study.java.hanyx.el.test;

import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;

import study.java.hanyx.el.simpleImpl.InvokeEL;
import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;

public class TestEL3 {
	
	private static final String el = "单价*数量+运费";
	
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
		InvokeEL.instance.push("单价", 5000).push("数量", 12).push("运费", 7500);
		Object result = InvokeEL.instance.eval("单价*数量+运费");
		System.out.println("InvokeEL结果:" + result);
	}
	
	private static void fastEL() {
		FelEngine fel = new FelEngineImpl();
		FelContext ctx = fel.getContext();
		ctx.set("单价", 5000);
		ctx.set("数量", 12);
		ctx.set("运费", 7500);
		Object result = fel.eval(el);
		System.out.println("Fast-EL结果:" + result);
	}
	
	private static void aviatorEl() {
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("单价", 5000);
		params.put("数量", 12);
		params.put("运费", 7500);
		System.out.println("Aviator结果:" + AviatorEvaluator.execute(el,params));
	}
	
	private static void mvEL() {
		Map<String, Object> params = new HashMap<String, Object>();  
		params.put("单价", 5000);
		params.put("数量", 12);
		params.put("运费", 7500);
		Object result = MVEL.eval(el, params);  
		System.out.println("MVEL结果:" + result);
	}
}
