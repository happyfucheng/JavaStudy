package study.java.hanyx.rpn;

import org.mvel2.MVEL;

import study.java.hanyx.rpn.crmPatternExp.Arithmetic;

import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.FelEngine;

/**
 * 测试逆波兰表达式
 * @author 韩元旭
 */
public class TestRPN {
	
	public static void main(String[] args) {
		crmBug();
		speed();
	}
	
	/**
	 * 测试CRM的逆波兰表达式实现的bug
	 */
	private static void crmBug() {
		//测试逆波兰表达式.
		String exp1 = "1-1+1";
		System.out.println("1.CRM实现的BUG:");
		System.out.println("	" + Arithmetic.calculate(exp1));
		System.out.println("	" + ReversePolishNotation.calculate(exp1));
	}
	
	/**
	 * 测试表达式效率
	 */
	private static void speed() {
		System.out.println("2.计算速度:");
		int times = 10_0000;
		String exp = "1000+100.0*99-(600.0-3*15)/(((68-9)-3)*2-100)+10000.0/7*71";
		System.out.println("Answer:"+(1000+100.0*99-(600.0-3*15)/(((68-9)-3)*2-100)+10000.0/7*71));
		System.out.println("RPN:"+ReversePolishNotation.calculate(exp));
		System.out.println("MVEL:"+MVEL.eval(exp));
		System.out.println("Fast-EL:"+FelEngine.instance.eval(exp));
		System.out.println("Aviator:"+AviatorEvaluator.execute(exp));
		
		long time = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			ReversePolishNotation.calculate(exp);
		}
		System.out.println("逆波兰表达式耗时:" + (System.currentTimeMillis() - time) + "ms");

		
		long time1 = System.currentTimeMillis();
		
		for (int i = 0; i < times; i++) {
			MVEL.eval(exp);
		}
		
		long time2 = System.currentTimeMillis();

		System.out.println("MVEL耗时: " + (time2-time1) + " ms");
		
		for (int i = 0; i < times; i++) {
			FelEngine.instance.eval(exp);
		}
		
		long time3 = System.currentTimeMillis();

		System.out.println("Fast-EL耗时: " + (time3-time2) + " ms");
		
		for (int i = 0; i < times; i++) {
			AviatorEvaluator.execute(exp);
		}
		
		long time4 = System.currentTimeMillis();
		
		System.out.println("Aviator耗时: " + (time4-time3) + " ms");
	}

}
