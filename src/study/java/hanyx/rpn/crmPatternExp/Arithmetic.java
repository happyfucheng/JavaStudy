package study.java.hanyx.rpn.crmPatternExp;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Copyright: Asiainfo-Linkage
 * 
 * @desc: 采用逆波兰表达式进行算术运算
 * @version: v1.0
 * @author: lvyh
 * @date: 2011.02.20
 */
public class Arithmetic {

	private final static String OP1 = "+";

	/**
	 * -
	 */
	private final static String OP2 = "-";

	/**
	 * *
	 */
	private final static String OP3 = "*";

	/**
	 * /
	 */
	private final static String OP4 = "/";

	/**
	 * (
	 */
	private final static String OPSTART = "(";

	/**
	 * )
	 */
	private final static String OPEND = ")";

	public Arithmetic(String exp) {
	}

	/**
	 * 分析四则运算表达式，将数字与运算符进行分解
	 */
	public static List parse(String exp) {
		// 四则运算解析
		List expList = new ArrayList();
		int length = exp.length();

		String tempStr = "";
		for (int i = 0; i < length; i++) {
			String tempChar = exp.substring(i, i + 1);
			if (isNumber(tempChar)) {
				tempStr += tempChar;
			} else {
				if (!tempStr.equals("")) {
					expList.add(tempStr);
				}
				expList.add(tempChar);
				tempStr = "";
			}
		}
		if (!tempStr.equals("")) {
			expList.add(tempStr);
		}

		return expList;

	}

	/**
	 * 判断当前字符或字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return str.startsWith("0") || str.startsWith("1") || str.startsWith("2") || str.startsWith("3") || str.startsWith("4") || str.startsWith("5") || str.startsWith("6") || str.startsWith("7") || str.startsWith("8") || str.startsWith("9") || str.startsWith(".");

	}

	/**
	 * 判断当前字符是否是 (
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isParenthesesStart(String str) {
		return str.equals(OPSTART);
	}

	/**
	 * 判断当前字符是否是 )
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isParenthesesEnd(String str) {
		return str.equals(OPEND);
	}

	/**
	 * 判断当前字符是否是高优先级运算符 * /
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isHeighOperator(String str) {
		if (str.equals(OP3) || str.equals(OP4)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对比两个字符串的优先级
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compare(String str1, String str2) {
		if (str1.equals(OPSTART)) {
			return false;
		}
		if (isHeighOperator(str2)) {
			return false;
		} else {
			if (isHeighOperator(str1)) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 将分解后的四则运算列表构建成逆波兰表达式列表
	 */
	public static List createRPN(String exp) {
		List expList = Arithmetic.parse(exp);

		// 存放逆波兰表达式
		List rpnList = new ArrayList();
		Stack stack = new Stack();

		int length = expList.size();
		for (int i = 0; i < length; i++) {
			String c = (String) expList.get(i);

			// 如果是数字，直接放到逆波兰链表的最后
			if (isNumber(c)) {
				rpnList.add(c);
			} else {
				// 如果不是数字

				// 如果是左括号，则直接将左括号压入栈
				if (isParenthesesStart(c)) {
					stack.push(c);
				} else if (isParenthesesEnd(c)) {
					// 如果是右括号

					// 进行出栈操作，直到栈为空或者遇到第一个左括号
					while (!stack.isEmpty()) {
						// 将栈顶字符串做出栈操作
						String tempC = stack.pop();
						if (!tempC.equals(OPSTART)) {
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
						if (compare(stack.top(), c)) {
							// 如果栈顶元素的优先级大于当前字符串,则进行出栈操作
							// 将栈顶元素直接放到逆波兰链表的最后
							// 直到栈内为空，或者当前元素的优先级不小于栈顶元素优先级
							while (!stack.isEmpty() && compare(stack.top(), c)) {
								rpnList.add(stack.pop());
							}
						}
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
	 * 通过逆波兰表达式计算结果
	 * 
	 * @return
	 */
	public static String calculate(String exp) {
		Stack stack = new Stack();
		List rpnList = Arithmetic.createRPN(exp);
		int length = rpnList.size();
		for (int i = 0; i < length; i++) {
			String temp = (String) rpnList.get(i);
			if (isNumber(temp)) {
				stack.push(temp);
			} else {
				int number1 = Integer.parseInt(stack.pop());
				int number2 = Integer.parseInt(stack.pop());

				int rst = 0;

				if (temp.equals(OP1)) {
					rst = number1 + number2;
				} else if (temp.equals(OP2)) {
					rst = number2 - number1;
				} else if (temp.equals(OP3)) {
					rst = number2 * number1;
				} else if (temp.equals(OP4)) {
					rst = number2 / number1;
				}
				stack.push(Integer.toString(rst));
			}
		}

		return stack.pop();
	}

	/**
	 * 获取逆波兰表达式字符串
	 * 
	 * @return
	 */
	public static String getRPN(List rpnList) {
		String rpn = "";
		int rpnLength = rpnList.size();
		for (int i = 0; i < rpnLength; i++) {
			rpn += rpnList.get(i) + " ";
		}
		return rpn;
	}

	public static void main(String[] args) {
		String str = "1+(2+3)*(100-5*(9+8))/5";
		// 将四则运算字符串分解为逆波兰表达式后计算结果
		System.out.println("result: " + Arithmetic.calculate(str));
	}
}
