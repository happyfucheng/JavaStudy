package study.java.drools.RHS;

import study.java.drools.Customer;
import study.java.drools.RuleEngine;

public class DroolsRHSInsert {
	public static void main(String[] args) {
		String[] str = { "drools_insert.drl" };
		Customer customer = new Customer();
		customer.setAge(2);
		customer.setName("张三");
		Object[] objs = {customer}; 
		RuleEngine.getInstance().runRules(objs, str);
	}
}
