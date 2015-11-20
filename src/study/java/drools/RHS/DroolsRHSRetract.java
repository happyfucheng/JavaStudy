package study.java.drools.RHS;

import study.java.drools.RuleEngine;

public class DroolsRHSRetract {
	public static void main(String[] args) {
		String[] str = { "drools_retract.drl" };
		RuleEngine.getInstance().runRules(null, str);
	}
}
