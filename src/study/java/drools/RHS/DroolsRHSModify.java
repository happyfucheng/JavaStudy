package study.java.drools.RHS;

import study.java.drools.RuleEngine;

public class DroolsRHSModify {
	public static void main(String[] args) {
		String[] str = { "drools_modify.drl" };
		RuleEngine.getInstance().runRules(null, str);
	}
}
