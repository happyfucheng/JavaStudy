package study.java.hanyx.rpn.crmPatternExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * Copyright: Asiainfo-Linkage
 * 
 * @desc: 号码模式匹配
 * @version: v1.0
 * @author: lvyh
 * @date: 2011.02.20
 */
@SuppressWarnings({ "unchecked", "unused" , "rawtypes"})
public class PatternMatch {
	//从A开始，有10个字母可供模式定义
	public static int BIT_REL_LENGTH = 10;
	// 单个号码批量匹配号段表达式和分类表达式
	
	public static Map matchExp(List billIdList, List segmentExp, List typeDefExp, String regionId) throws Exception {
		Map map = new HashMap();
		StringBuffer matchRst = new StringBuffer();
		String billId = null;
		String segmentMatch = null;
		String typeDefMatch = null;
		for (int i = 0; i < billIdList.size(); i++) {
			billId = (String) billIdList.get(i);

			segmentMatch = PatternMatch.matchExp(billId, segmentExp, regionId);// 返回结果为模式号段编号
			// 如果匹配结果为空，则默认归属普通号码模式
			if (null == segmentMatch) {
				segmentMatch = "10000014";
			}

			typeDefMatch = PatternMatch.matchExp(billId, typeDefExp, regionId);// 返回结果为模式分类定义编号
			if (null == typeDefMatch) {
				typeDefMatch = "20000121";
			}

			if (null != segmentMatch && null != typeDefMatch)
				// matchRst.append("\"").append(segmentMatch).append("#").append(typeDefMatch).append("\"");
				matchRst.append(segmentMatch).append("#").append(typeDefMatch);
			else
				matchRst.append("");
			map.put(billId, matchRst.toString());// {15951942775,100001#100000005}
		}
		return map;
	}

	// 单个号码批量匹配号段表达式和分类表达式
	public static Map matchExp(String billId, HashMap segmentExp, HashMap typeDefExp, String regionId) throws Exception {
		Map map = new HashMap();
		StringBuffer matchRst = new StringBuffer();
		String segmentMatch = PatternMatch.matchExp(billId, segmentExp, regionId);
		String typeDefMatch = PatternMatch.matchExp(billId, typeDefExp, regionId);
		if (null != segmentMatch && null != typeDefMatch) {
			matchRst.append("\"").append(segmentMatch).append("#").append(typeDefMatch).append("\"");
		}
		map.put(billId, matchRst.toString());// {15951942775,100001#100000005}
		return map;
	}

	// 单个号码批量匹配模式表达式
	public static String matchExp(String billId, HashMap expMap, String regionId) throws Exception {
		boolean rst = false;
		Iterator it = expMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String value = (String) entry.getValue();
			String key = (String) entry.getKey();
			rst = PatternMatch.matchExp(billId, value, regionId);
			if (rst == true)
				return key;
		}
		return null;
	}

	// 单个号码批量匹配模式表达式
	public static boolean matchExp(String billId, String[] exps, String regionId) throws Exception {
		boolean rst = false;
		for (int i = 0; i < exps.length; i++) {
			rst = PatternMatch.matchExp(billId, exps[i], regionId);
			if (rst == true)
				break;
		}
		return rst;
	}

	// 单个号码批量匹配模式表达式
	public static String matchExp(String billId, List list, String regionId) throws Exception {
		boolean rst = false;
		PatternBean bean = null;
		String numExp = null;
		if (billId.startsWith("5")) {
			// 1.特殊处理：如果号码为无线铁通号码，则模式号段只需要匹配一个即可-提升性能考虑
			numExp = ResConst.WIRELESS_NUM_PATTERN_SEG_EXP;
			rst = PatternMatch.matchExp(billId, numExp, regionId);
			if (rst == true)
				return ResConst.WIRELESS_NUM_PATTERN_SEG_ID;
			else
				return null;
		} else if (billId.startsWith("0")) {
			// 2.特殊处理：如果号码为有线铁通固话号码，则模式号段只需要匹配一个即可-提升性能考虑
			numExp = ResConst.FIX_NUM_PATTERN_SEG_ID;
			rst = PatternMatch.matchExp(billId, numExp, regionId);
			if (rst == true)
				return ResConst.FIX_NUM_PATTERN_SEG_EXP;
			else
				return null;
		}

		for (int i = 0; i < list.size(); i++) {
			bean = (PatternBean) list.get(i);
			rst = PatternMatch.matchExp(billId, bean.getValue(), regionId);
			if (rst == true)
				return bean.getKey();// 返回模式编号
		}
		return null;
	}
	//可用字符A,B,C,D,E,F,*,其中相同字母代表在号码中数字也一样，比较从号码尾部开始，出现三种字母或以上意味着从A开始升序。
	//*代表匹配任何字母
	//举例 AABB = 1387495[3344],1387495[5566],ABC = 1387495[123],1387495[345],ABAB = 1387495[3434]
	//号码或者比较模式为空，或者号码长度小于模式长度的时候，默认返回true
	public static boolean checkBitRel(String resId,String bitRel,int bitOrder){
		if(bitRel == null ||bitRel.length() == 0)
			return true;
		else if(resId == null ||resId.length() == 0||resId.length() < bitRel.length())
			return false;
		Map chMap = new HashMap();
		//逆序，因为比较是从尾部开始
		char[] resIdReverseChs = (new StringBuilder(resId)).reverse().toString().toCharArray();
		char[] bitRelReverseChs = (new StringBuilder(bitRel)).reverse().toString().toCharArray();
		for (int i = 0; i < bitRelReverseChs.length;i++) {
			//将A，B，C等字符对应上号码具体的数字
			setChMap(chMap,bitRelReverseChs[i],resIdReverseChs[i]);
		}
		//说明模式字符串的字母种类多余两个，需要根据abc升序输出数字，比如a=3 那么 abc = 123
		if(chMap.size() > 2){
			//如果不等于1
			if(bitOrder != 1)
				setChMapAsc(chMap);
		}
		//如果只有两个字母，字母代表值有一样，那就要返回false 比如ABBA 对 1333333这个号码取值，回到A=3 B=3 而这不是我们的本意
		if(chMap.size() == 2){
			Iterator e = chMap.values().iterator();
			Character ch1 = (Character)e.next();
			Character ch2 = (Character)e.next();
			if(ch1.equals(ch2)){
				return false;
			}
		}
		//检查是否符合规则
		boolean re = true;
		for (int i = 0; i < bitRelReverseChs.length;i++) {
			if(bitRelReverseChs[i] == '*')
				continue;
			else if(isNum(bitRelReverseChs[i])){
				//如果是数字，需要完全相等。
				if(bitRelReverseChs[i] != resIdReverseChs[i]){
					re = false;
					break;
				}
			}else if(!chMap.get(bitRelReverseChs[i]).equals(resIdReverseChs[i])){
					re = false;
					break;
			}
		}
		return re;
	}
	public static boolean isNum(char ch){
		return ch >= '0' && ch <= '9';			
		//return StringUtils.isNumericSpace(String.valueOf(ch));
	}
	//将map里包含的ABCD等对应具体数字升序赋值
	private static void setChMapAsc(Map chMap){
		Set keys = chMap.keySet();;
		Iterator e =  keys.iterator();
		Character minCh = Character.MAX_VALUE;
		while(e.hasNext()){
			Character chRel = (Character)e.next();
			if(chRel.compareTo(minCh) < 0){
				minCh = chRel;
			}
		}
		char phoneCh = ((Character)chMap.get(minCh)).charValue();
		char patternCh = minCh.charValue();
		for(int i = 0;i<BIT_REL_LENGTH;i++){
			chMap.put(new Character((char) (patternCh + i)),new Character((char) (phoneCh + i)));
		}
	}
	private static void setChMap(Map chMap,char chRel,char chPhone){
		if(chRel == '*')
			return;
		if(!chMap.containsKey(chRel)){
			chMap.put(chRel, chPhone);
		}
	}
	public static boolean matchExp(String billId, String exp, String regionId) throws Exception {
		exp = PatternMatch.preProcessExp(billId, exp, regionId);
		// 预处理表达式后进行分支处理：两种不同风格的表达式类型
		if (PatternMatch.isSpecialExp(exp)) {
			return ExtPatternMatch.processExp(billId, exp);
		} else
			return PatternMatch.processExp(billId, exp);
	}

	public static boolean isSpecialExp(String exp) throws Exception {
		if (exp.contains(")&&(") || exp.contains(")||("))
			return true;
		return false;
	}

	public static String preProcessExp(String billId, String exp, String regionId) throws Exception {
		String newExp = PatternMatch.transform(exp, regionId);
		return newExp;
	}

	// S(v,L(v)-6,1) == '6'|| S(v,L(v)-6,1) == '8'|| S(v,L(v)-6,1) == '9'
	public static boolean processExp(String billId, String exp) throws Exception {
		Queue queue = PatternMatch.splitExp(exp);
		boolean flag = false;
		boolean flag2 = false;

		boolean rst = false;
		for (int i = 0; i < queue.size(); i++) {
			String temp = (String) queue.get(i);
			if (temp.startsWith("("))// 如果表达式带括号则需要预处理
				temp = PatternMatch.transfromExp(temp);// 预处理子表达式
			if (temp.startsWith("S") || temp.startsWith("C")) {
				flag = PatternMatch.processSubExp(billId, temp);
				rst = flag;
			} else if (temp.equals("||")) {
				temp = (String) queue.get(i + 1);
				if (temp.startsWith("("))// 如果表达式带括号则需要预处理
					temp = PatternMatch.transfromExp(temp);// 预处理子表达式
				flag2 = PatternMatch.processSubExp(billId, temp);
				if (flag == true || flag2 == true) {
					rst = true;
				} else {
					rst = false;
				}
				flag = rst;
				i++;

			} else if (temp.equals("&&")) {
				temp = (String) queue.get(i + 1);
				if (temp.startsWith("("))// 如果表达式带括号则需要预处理
					temp = PatternMatch.transfromExp(temp);// 预处理子表达式
				flag2 = PatternMatch.processSubExp(billId, temp);
				if (flag == true && flag2 == true) {
					rst = true;
				} else {
					rst = false;
				}
				flag = rst;
				i++;
			}
		}
		return rst;
	}

	public static boolean processSubExp(String billId, String exp) throws Exception {
		String[] expTemp = null;
		if (exp.contains("==")) {
			expTemp = PatternMatch.split(exp, "==");
			expTemp[0] = expTemp[0].trim();
			expTemp[1] = expTemp[1].trim();
			if (exp.startsWith("S")) {// S(v,L(v)-6,1) == '6'
				String rst = PatternMatch.processSubstr(billId, expTemp[0]);
				if (expTemp[1].startsWith("'")) {
					expTemp[1] = expTemp[1].substring(1, expTemp[1].length() - 1);
				} else if (expTemp[1].startsWith("S")) {// (S(v,L(v)-4,1)!=S(v,L(v)-3,1))
					// expTemp[1] = PatternMatch.transfromExp(expTemp[1]);//
					// 去掉右括号
					expTemp[1] = PatternMatch.processSubstr(billId, expTemp[1]);
				}
				if (rst.equals(expTemp[1]))
					return true;
			} else if (exp.startsWith("C")) {// C(S(v,3,4),'4')==true
				boolean flag = PatternMatch.processContain(billId, expTemp[0]);
				if (Boolean.toString(flag).equals(expTemp[1])) {
					return true;
				} else
					return false;

			} else if (exp.startsWith("L")) {

			}
			return false;
		} else if (exp.contains("!=")) {
			expTemp = PatternMatch.split(exp, "!=");
			expTemp[0] = expTemp[0].trim();
			expTemp[1] = expTemp[1].trim();
			if (exp.startsWith("S")) {// S(v,L(v)-6,1) == '6'
				String rst = PatternMatch.processSubstr(billId, expTemp[0]);
				if (expTemp[1].startsWith("'")) {
					expTemp[1] = expTemp[1].substring(1, expTemp[1].length() - 1);
				} else if (expTemp[1].startsWith("S")) {// (S(v,L(v)-4,1)!=S(v,L(v)-3,1))
					//expTemp[1] = PatternMatch.transfromExp(expTemp[1]);// 去掉右括号
					expTemp[1] = PatternMatch.processSubstr(billId, expTemp[1]);
				}
				if (!rst.equals(expTemp[1]))
					return true;
			} else if (exp.startsWith("C")) {// C(S(v,3,4),'4')==true
				boolean flag = PatternMatch.processContain(billId, expTemp[0]);
				if (!Boolean.toString(flag).equals(expTemp[1])) {
					return true;
				} else
					return false;

			} else if (exp.startsWith("L")) {

			}
			return false;
		}
		return false;
	}

	public static boolean processContain(String billId, String exp) throws Exception {
		// C(S(v,3,4),'4')
		boolean rst = false;
		char c = exp.charAt(2);
		if (c == 'S') {
			if (exp.contains("L(v)")) {
				// 需处理如下情况-C(S(v,L(v)-3,3),'4')--转换--C(S(v,3,4),'4')
				exp = PatternMatch.getSubStrExp(billId, exp);
			}

			int index = exp.indexOf(")");
			String temp = exp.substring(2, index + 1);// para: S(v,3,4)
			String substr = PatternMatch.processSubstr(billId, exp);
			exp = exp.replace(temp, substr);// C("5104",'4');
			String[] para = PatternMatch.split(exp, ",");
			para[1] = PatternMatch.getParaInt(para[1]);
			rst = PatternMatch.invokeContain(substr, para[1]);
		}

		return rst;
	}

	public static String getSubStrExp(String billId, String exp) throws Exception {// C(S(v,L(v)-3,3),'4')---------C(S(v,3,4),'4')
		int three = PatternMatch.isThreeComma(exp);
		String subExp = exp.substring(2, three);// subExp--------S(v,L(v)-3,3)
		String temp = PatternMatch.processSubStrExp(billId, subExp);// temp-----S(v,5,3)
		CharSequence cs = temp.subSequence(0, temp.length());
		String str = exp.replace(subExp, cs);
		return str;
	}

	public static String processSubStrExp(String billId, String exp) throws Exception {
		// S(v,L(v)-6,1)
		String[] paras = PatternMatch.split(exp, ",");

		// Substr共三个参数，第1个为billId,只需处理后两个参数
		// 第2个参数
		if (paras[1].contains("L(v)")) {
			String str = Integer.toString(PatternMatch.invokeLength(billId));
			CharSequence cs = str.subSequence(0, str.length());
			paras[1] = paras[1].replace("L(v)", cs);
			paras[1] = Arithmetic.calculate(paras[1]);// 计算算术表达式字符串值，比如"11-6"
		}

		// 第3个参数
		// 需要截掉最后位的右括号才能进行算术运算
		paras[2] = paras[2].substring(0, paras[2].length() - 1);
		if (paras[2].contains("L(v)")) {
			String str = Integer.toString(PatternMatch.invokeLength(billId));
			CharSequence cs = str.subSequence(0, str.length());
			paras[2] = paras[2].replace("L(v)", cs);
			paras[2] = Arithmetic.calculate(paras[2]);// 计算算术表达式字符串值，比如"11-6"
		}

		StringBuffer sb = new StringBuffer();
		sb.append(paras[0]).append(",").append(paras[1]).append(",").append(paras[2]).append(")");
		return sb.toString();// S(v,5,1)
	}

	public static String processSubstr(String billId, String exp) throws Exception {
		// S(v,L(v)-6,1)
		String[] paras = PatternMatch.split(exp, ",");

		// Substr共三个参数，第1个为billId,只需处理后两个参数
		// 第2个参数
		if (paras[1].contains("L(v)")) {
			String str = Integer.toString(PatternMatch.invokeLength(billId));
			CharSequence cs = str.subSequence(0, str.length());
			paras[1] = paras[1].replace("L(v)", cs);
			paras[1] = Arithmetic.calculate(paras[1]);// 计算算术表达式字符串值，比如"11-6"
		}

		// 第3个参数
		// 需要截掉最后位的右括号才能进行算术运算
		paras[2] = paras[2].substring(0, paras[2].length() - 1);
		if (paras[2].contains("L(v)")) {
			String str = Integer.toString(PatternMatch.invokeLength(billId));
			CharSequence cs = str.subSequence(0, str.length());
			paras[2] = paras[2].replace("L(v)", cs);
			paras[2] = Arithmetic.calculate(paras[2]);// 计算算术表达式字符串值，比如"11-6"
		}

		int begin = Integer.parseInt(paras[1]);
		int length = Integer.parseInt(paras[2]);
		String rst = PatternMatch.invokeSubstr(billId, begin, length);
		return rst;// S(v,5,1)
	}

	public static String processContainSubstr(String billId, String exp) throws Exception {
		// S(v,L(v)-6,1)
		String[] paras = PatternMatch.split(exp, ",");

		// Substr共三个参数，第1个为billId,只需处理后两个参数
		// 第2个参数
		if (paras[1].contains("L(v)")) {
			String str = Integer.toString(PatternMatch.invokeLength(billId));
			CharSequence cs = str.subSequence(0, str.length());
			paras[1] = paras[1].replace("L(v)", cs);
			paras[1] = Arithmetic.calculate(paras[1]);// 计算算术表达式字符串值，比如"11-6"
		}

		// 第3个参数
		// 需要截掉最后位的右括号才能进行算术运算
		paras[2] = paras[2].substring(0, paras[2].length() - 1);
		if (paras[2].contains("L(v)")) {
			String str = Integer.toString(PatternMatch.invokeLength(billId));
			CharSequence cs = str.subSequence(0, str.length());
			paras[2] = paras[2].replace("L(v)", cs);
			paras[2] = Arithmetic.calculate(paras[2]);// 计算算术表达式字符串值，比如"11-6"
		}
		int begin = Integer.parseInt(paras[1]);
		int length = Integer.parseInt(paras[2]);
		String rst = PatternMatch.invokeSubstr(billId, begin, length);
		return rst;
	}

	public static int isFirstComma(String exp) {// ,
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (c == ',')
				return i;
		}
		return 0;
	}

	// 表达式分为2种:带左右括号和不带左右括号；表达式约束：同一表达式逻辑运算符(逻辑或||、逻辑与&&)两侧要么都带，要么都不带括号
	public static String transfromExp(String exp) {
		// 去掉左括号 (S(v,L(v)-4,1)=='4'---------S(v,L(v)-4,1)=='4'
		if (exp.startsWith("(")) {
			exp = exp.substring(1, exp.length());
		}

		// 去掉右括号 S(v,L(v)-4,1)=='4')---------S(v,L(v)-4,1)=='4'
		if (exp.endsWith(")")) {
			exp = exp.substring(0, exp.length() - 1);
		}
		return exp;
	}

	public static int isSecondComma(String exp, int j) {// ,
		for (int i = j + 1; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (c == ',')
				return i;
		}
		return 0;
	}

	public static int isThreeComma(String exp) {// ,
		int flag = 0;
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (c == ',') {
				flag++;
				if (flag == 3) {
					return i;
				}
			}
		}
		return 0;
	}


	private List parse(String exp) {
		List expList = new ArrayList();

		int length = exp.length();
		for (int i = 0; i < length; i++) {
			char c = exp.charAt(i);
			switch (c) {

			case 'S': {
				expList.add(String.valueOf(c));
				continue;
			}

			case '(': {
				expList.add(String.valueOf(c));
				continue;
			}

			case ')': {
				expList.add(String.valueOf(c));
				continue;
			}

			case ',': {
				expList.add(String.valueOf(c));
				continue;
			}

			case '-': {
				expList.add(String.valueOf(c));
				continue;
			}
			case '!': {
				char[] token = new char[2];
				token[0] = c;
				token[1] = exp.charAt(i + 1);
				String temp = String.valueOf(token);
				expList.add(temp);
				i++;
				continue;
			}

			case '\'': {
				char[] token = new char[3];
				token[0] = c;
				token[1] = exp.charAt(i + 1);
				token[2] = exp.charAt(i + 2);
				String temp = String.valueOf(token);
				expList.add(temp);
				i++;
				i++;
				continue;
			}
			}

			if (PatternMatch.isNumeric(c)) {
				expList.add(String.valueOf(c));
				continue;
			}
		}

		return expList;
	}

	public static int invokeLength(String billId) throws Exception {
		// L(v)
		return PatternMatch.length(billId);
	}

	public static String invokeSubstr(String billId, int begin, int length) throws Exception {
		// S(v,8,1)
		return PatternMatch.substr(billId, begin, length);
	}

	public static boolean invokeContain(String billId, String str) throws Exception {
		// C(v,'6')
		return PatternMatch.contain(billId, str);
	}

	public static Queue splitExp(String exp) {
		Queue queue = new Queue();
		// 首先对表达式进行分割，第一次采用"||"分割，确保分割后每段都没有"||"
		String[] firstSplit = PatternMatch.split(exp, "||");

		for (int i = 0; i < firstSplit.length; i++) {
			// 其次对分割后表达式再进行分割，第二次采用"&&"分割，确保分割后每段都没有"&&"
			String[] secendSplit = PatternMatch.split(firstSplit[i], "&&");
			int size = secendSplit.length;
			if (secendSplit.length > 1) {
				for (int j = 0; j < size; j++) {
					queue .put(secendSplit[j].trim());
					if (j < size - 1)
						queue.put("&&");
				}
			} else {
				queue.put(firstSplit[i].trim());
			}

			if (i < firstSplit.length - 1)
				queue.put("||");
		}
		return queue;
	}

	public static String[] split(String str, String splitChar) {
		StringTokenizer st = null;
		if ("||".equals(splitChar)) {
			st = new StringTokenizer(str, "||");
		} else if ("&&".equals(splitChar)) {
			st = new StringTokenizer(str, "&&");
		} else if (",".equals(splitChar)) {
			st = new StringTokenizer(str, ",");
		} else if ("==".equals(splitChar)) {
			st = new StringTokenizer(str, "==");
		} else if ("!=".equals(splitChar)) {
			st = new StringTokenizer(str, "!=");
		}

		String[] temp = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			temp[i] = st.nextToken();
			i++;
		}
		return temp;
	}

	/**
	 * 判断当前字符串是否是数字,用ascii码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	/**
	 * 判断当前字符是否是数字,用ascii码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(char c) {
		int ch = (int) c;
		if (ch < 48 || ch > 57)
			return false;
		return true;
	}

	public static String transform(String exp, String regionId) {
		String str = exp.replace("@bill_id", "v");
		str = str.replace("@region_id", regionId);
		str = str.replace("$substr", "S");
		str = str.replace("$length", "L");
		str = str.replace("$contain", "C");
		//System.out.println("简化后表达式: " + str);
		return str;
	}

	// $length
	public static int length(String v) {
		return v.length();
	}

	// $substr-从字符串第begin个字符开始截取长度为length的字符串(下标从1开始)
	public static String substr(String v, int begin, int length) throws Exception {
		//if (begin == 0)
			//begin = 1;// 需要特殊处理
		//int beginIndex = begin - 1;
		int beginIndex = begin;
		int endIndex = begin + length ;
		if (endIndex > v.length()) {
			//throw new Exception("警告：传入参数有误【数组越界】,函数$substr(@bill_id,para1,para2)中para2<=" + (v.length() - begin + 1));
//			throw new Exception(CrmLocaleFactory.getResource("RSS0006744"));
		}
		// 从字符串索引(下标从0开始)为beginIndex的字符开始截取长度为endIndex-beginIndex 的字符串。
		String str ="ERROR";
		if(begin>=0){//2011-11-10 开始小于0时，扔出错误字符串
			str = v.substring(beginIndex, endIndex);
		}
		return str;
	}

	// $contain
	public static boolean contain(String v, String s) {
		return v.contains(s);
	}

	public static String getParaInt(String str) {
		String temp = null;
		if (str.startsWith("'")) {
			temp = str.substring(1, str.length() - 2);
		} else
			temp = str.substring(0, str.length() - 1);

		return temp;
	}
}
