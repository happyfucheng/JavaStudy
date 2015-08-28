package study.java.hanyx.rpn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 逆波兰表达式计算的实现
 * @author 韩元旭
 */
public class ReversePolishNotation {
	
	private static final Map<String, Integer> SIGN_PRIORITY = new HashMap<String, Integer>();
	private static final String SIGN_LEFT = "(";
	private static final String SIGN_RIGHT = ")";
	private static final String SIGN_PLUS = "+";
	private static final String SIGN_MINUS = "-";
	private static final String SIGN_TIMES = "*";
	private static final String SIGN_DIVIDE = "/";
	
	static {
		// 设置符号优先级
		SIGN_PRIORITY.put(SIGN_LEFT, 0);
		SIGN_PRIORITY.put(SIGN_RIGHT, 0);
		SIGN_PRIORITY.put(SIGN_PLUS, 1);
		SIGN_PRIORITY.put(SIGN_MINUS, 1);
		SIGN_PRIORITY.put(SIGN_TIMES, 2);
		SIGN_PRIORITY.put(SIGN_DIVIDE, 2);
	}
	
	/**
	 * 计算表达式的值
	 * @param exp
	 * @return
	 */
	public static String calculate(String exp) {
		Stack<Double> stack = new Stack<Double>();
		List<String> rpnList = create(exp);
		int length = rpnList.size();
		for (int i = 0; i < length; i++) {
			String temp = rpnList.get(i);
			if (isNumber(temp)) {
				stack.push(Double.parseDouble(temp));
			} else {
				double number2 = stack.pop();
				double number1 = stack.pop();

				double rst = 0;

				if (SIGN_PLUS.equals(temp)) {
					rst = number1 + number2;
				} else if (SIGN_MINUS.equals(temp)) {
					rst = number1 - number2;
				} else if (SIGN_TIMES.equals(temp)) {
					rst = number1 * number2;
				} else if (SIGN_DIVIDE.equals(temp)) {
					rst = number1 / number2;
				}
				
				stack.push(rst);
			}
		}
		//返回结果
		double result = stack.pop();
		if ((int)result == result) {
			return String.valueOf((int)result); 
		} else {
			return String.valueOf(result); 
		}
	}
	
	/**
	 * 根据表达式字符串创建逆波兰表达式List
	 * @param exp
	 * @return
	 */
	private static List<String> create(String exp) {
		// 拆分表达式
		List<String> expList = parse(exp);

		// 存放逆波兰表达式
		List<String> rpnList = new LinkedList<String>();
		// 存放运算符
		Stack<String> stack = new Stack<String>();

		for (String c: expList) {

			// 如果是数字，直接放到逆波兰链表的最后
			if (isNumber(c)) {
				rpnList.add(c);
			} else {
				// 如果不是数字

				// 如果是左括号，则直接将左括号压入栈
				if (SIGN_LEFT.equals(c)) {
					stack.push(c);
				} else if (SIGN_RIGHT.equals(c)) {
					// 如果是右括号

					// 进行出栈操作，直到栈为空或者遇到第一个左括号
					while (!stack.isEmpty()) {
						// 将栈顶字符串做出栈操作
						String tempC = stack.pop();
						if (!SIGN_LEFT.equals(tempC)) {
							// 如果不是左括号，则将字符串直接放到逆波兰链表的最后
							rpnList.add(tempC);
						} else {
							// 如果是左括号，退出循环操作
							break;
						}
					}
				} else {
					// 如果栈内为空
					if (stack.isEmpty()) {
						// 将当前字符串直接压栈
						stack.push(c); 
					} else {
						// 如果栈不为空

						// 比较栈顶字符串与当前字符串的优先级，
							// 如果栈顶元素的优先级大于等于当前字符串,则进行出栈操作
							// 将栈顶元素直接放到逆波兰链表的最后
							// 直到栈内为空，或者当前元素的优先级不小于栈顶元素优先级
						while (!stack.isEmpty() && !isPrior(stack.peek(), c)) {
							rpnList.add(stack.pop());
						}
						// 如果当前符号大于栈顶元素优先级.
						// 将当前字符串直接压栈
						stack.push(c);
					}
				}

			}
		}

		// 如果栈不为空，则将栈中所有元素出栈放到逆波兰链表的最后
		while (!stack.isEmpty()) {
			rpnList.add(stack.pop());
		}

		return rpnList;
	}

	/**
	 * 拆分表达式为List
	 * @param exp
	 * @return
	 */
	private static List<String> parse(String exp) {
		// 四则运算解析   123 + 456
		List<String> expList = new ArrayList<String>();
		int length = exp.length();
		// 123+456
		StringBuffer sb = new StringBuffer();//123
		for (int i = 0; i < length; i++) {
			String t = exp.substring(i, i + 1);
			if (isNumber(t)) {
				//如果是数字和之前的数字拼到一起
				sb.append(t);
			} else {
				//如果是符号,先要把之前的数字加入并清空数字,再加入符号
				if (sb.length()>0) {
					//前提是数字存在..
					expList.add(sb.toString());
					sb.delete(0, sb.length());
				}
				expList.add(t);
			}
		}
		//如果存在,加入结尾的数字
		if (sb.length()>0) {
			expList.add(sb.toString());
		}
		return expList;
	}

	/**
	 * 优先级是否高于栈顶元素
	 * @param pop
	 * @param c
	 * @return
	 */
	private static boolean isPrior(String pop, String c) {
		return SIGN_PRIORITY.get(c) > SIGN_PRIORITY.get(pop);
	}

	/**
	 * 判断是否是数字
	 * @param sign
	 * @return
	 */
	private static boolean isNumber(String sign) {
		//如果不是符号即为数字
		return SIGN_PRIORITY.get(sign) == null;
	}
	
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		String exp = "1000+100.0*99-(600-3*15)/(((68-9)-3)*2-100)+10000+7*71";

		int times = 100000;
		
		long time1 = System.currentTimeMillis();
		
		for (int i = 0; i < times; i++) {
			calculate(exp);
		}
		
		long time2 = System.currentTimeMillis();
		System.out.println(times+"次耗时: " + (time2-time1) + " ms");

		System.out.println("The Answer is : " + calculate(exp));
		System.out.println("The Answer is : " + (1000+100.0*99-(600-3*15D)/(((68-9)-3)*2-100)+10000+7*71));
	}
}
