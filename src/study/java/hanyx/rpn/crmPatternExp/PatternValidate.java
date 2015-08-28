package study.java.hanyx.rpn.crmPatternExp;

import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Copyright: Asiainfo-Linkage
 * 
 * @desc: 模式表达式校验
 * @version: v1.0
 * @author: lvyh
 * @date: 2011.02.20
 */
public class PatternValidate {
	public static void main(String[] args) {
		String exp = "C(S(v,3,4),'4')==true";
		System.out.println(PatternValidate.isValidPara(exp));
	}

	// 校验contain函数的参数个数是否正确
	public static boolean isValidPara(String exp) {
		boolean rst = false;
		if (exp.startsWith("C")) {
			int i = exp.indexOf("(");
			String sub = exp.substring(i + 1, exp.length());
			if (sub.startsWith("S")) {
				int m = sub.indexOf("(");
				int n = sub.indexOf(")");
				String temp = sub.substring(m - 1, n + 1);// S(v,3,4)
				if (PatternValidate.isValidFunction(temp)) {
					exp = exp.replace(temp, "v");
				}
				if (PatternValidate.isValidFunction(exp)) {
					rst = true;
				}
			}
		}
		return rst;
	}

	// 校验函数是否有效-使用正则表达式
	public static boolean isValidFunction(String exp) {
		String regex = null;
		Pattern p = null;
		Matcher m = null;
		if (exp.startsWith("S")) {// S(v,30,40)
			regex = "[S][(][v][,]\\d*[,]\\d*[)]";
			p = Pattern.compile(regex);
			m = p.matcher(exp);
		} else if (exp.startsWith("C")) {// C(v,'4')
			regex = "[C][(][v][,][']\\d*['][)]";
			p = Pattern.compile(regex);
			m = p.matcher(exp);
		} else if (exp.startsWith("L")) {// L(v)
			regex = "[L][(][v][)]";
			p = Pattern.compile(regex);
			m = p.matcher(exp);
		}

		return m.find();
	}

	// 校验左右括号个数是否匹配
	public static boolean isParenthesesMatch(String exp) {
		boolean rst = false;
		int left = 0;
		int right = 0;
		int length = exp.length();
		for (int i = 0; i < length; i++) {
			char c = exp.charAt(i);
			if (c == '(')
				left++;
			else if (c == ')')
				right++;
		}
		if (left == right) {
			rst = true;
		}
		return rst;
	}
	
	
	public static boolean  chk_resPhoneBitRelSimple(String resId, int bitOrder,
			String bitrel) throws Exception, RemoteException {
		boolean bCheck = false;
		int idx = 0;
		int idx2 = 0;
		char[] pChkResId = resId.toCharArray();
		char[] pPattern = bitrel.toCharArray();
		int idx3 = pChkResId.length - pPattern.length;
		for (int i = 0; i < pPattern.length; i++) {
			char curchar = pPattern[i];
			if (curchar == '*') {
				idx++;
				continue;
			}
			if (Character.isDigit(curchar)) {
				if (pChkResId[idx+idx3] == curchar) {
					idx++;
					continue;
				} else {
					bCheck = false;
					return bCheck;
				}
			}
			if (Character.isLetter(curchar)) {
				char[] pPatternNext = pPattern;
				idx2 = idx + 1;
				for (int j = i + 1; j < pPatternNext.length; j++) {
					char nChar = pPatternNext[j];
					if (nChar == '*' || Character.isDigit(nChar)) {
						idx2++;
						continue;
					}
					if (Character.isLetter(nChar))// 字母
					{
						// 判断如果字母相等 判断手机号码位是否相等
						if ((pPattern[i] == pPatternNext[j])
								&& (pChkResId[idx + idx3] != pChkResId[idx2
										+ idx3])) {
							bCheck = false;
							return bCheck;
						}
						if ((pPattern[i] == pPatternNext[j])
								&& (pChkResId[idx + idx3] == pChkResId[idx2
										+ idx3])) {
							idx2++;
							continue;
						}
						if (bitOrder == 1) // 要求不等AABB A!=B
						{
							if (pChkResId[idx + idx3] != pChkResId[idx2 + idx3]) {
								idx2++;
								continue;
							} else {
								bCheck = false;
								return bCheck;
							}
						}
						if (bitOrder == 2) // 要求步长一致 AABB 要求1122
						{
							if ((pPattern[i] - pPatternNext[j]) != (pChkResId[idx
									+ idx3] - pChkResId[idx2 + idx3])) {
								bCheck = false;
								return bCheck;
							} else {
								idx2++;
								continue;
							}
						}
						
					}
				}
				idx++;
				idx2 = idx;
				continue;
			}
		}
		bCheck = true;
		return bCheck;
	}
}
