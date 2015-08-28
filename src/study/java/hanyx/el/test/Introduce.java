package study.java.hanyx.el.test;

import org.mvel2.MVEL;

import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;

public class Introduce {
	public static void main(String[] args) {
		String el = "8+8*9+9";
		
		FelEngine fel = new FelEngineImpl();  
		System.out.println("Fast-EL结果:" + fel.eval(el));
		
		System.out.println("Fast-EL结果:" + FelEngine.instance.eval(el));

		System.out.println("Aviator结果:" + AviatorEvaluator.execute(el));
		
		System.out.println("MVEL结果:" + MVEL.eval(el));
	}
}
