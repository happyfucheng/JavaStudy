package study.java.hanyx.rpn;

import study.java.hanyx.rpn.crmPatternExp.PatternMatch;

/**
 * 测试CRM项目对靓号规则的匹配
 * @author 韩元旭
 */
public class TestPattern {
	
	public static void main(String[] args) throws Exception {
		
		//靓号模式的匹配
		System.out.println(PatternMatch.checkBitRel("13803648899", "AABB", 0));
		
		//靓号规则的匹配
		String str1 = "$substr(@bill_id,$length(@bill_id)-5,1) == '6'|| $substr(@bill_id,$length(@bill_id)-5,1) == '8'|| $substr(@bill_id,$length(@bill_id)-5,1) == '9'";
//		String str1 = "S(v,L(v)-5,1) == '6'|| S(v,L(v)-5,1) == '8'|| S(v,L(v)-5,1) == '9'";
		
		String str2 = "$contain($substr(@bill_id,L(@bill_id)-4,4),'4')==false";
//		String str2 = "C(S(v,L(v)-4,4),'4')==false";
		
		String str3 = "($substr(@bill_id,L(@bill_id)-4,1)=='4'&&$substr(@bill_id,L(@bill_id)-3,1)!='4')||($substr(@bill_id,L(@bill_id)-4,1)!='4'&&$substr(@bill_id,L(@bill_id)-3,1)!='4')||($substr(@bill_id,L(@bill_id)-4,1)!='4'&&S(@bill_id,L(@bill_id)-3,1)!='4')";
//		String str3 = "(S(v,L(v)-4,1)=='4'&&S(v,L(v)-3,1)!='4')||(S(v,L(v)-4,1)!='4'&&S(v,L(v)-3,1)!='4')||(S(v,L(v)-4,1)!='4'&&S(v,L(v)-3,1)!='4')";
		
		System.out.println(PatternMatch.matchExp("13803648899", str1,"110"));

		System.out.println(PatternMatch.matchExp("13803648899", str2,"110"));
		
		System.out.println(PatternMatch.matchExp("13803648899", str3,"110"));
	}
	

}
