package study.java.drools.RHS;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;

import study.java.drools.Customer;
import study.java.drools.RuleEngine;


public class DroolsRHSQuery {
	public static void main(String[] args) {
		String[] str = { "drools_query.drl" };
		Customer customer = new Customer();
		customer.setAge(2);
		customer.setName("张三");
		Object[] objs = {customer}; 
		StatefulKnowledgeSession session = RuleEngine.getInstance().runRules(objs, str);
		QueryResults results = session.getQueryResults("testQuery");
		for(QueryResultsRow qr:results){
			Customer cus = (Customer)qr.get("customer");
			System.out.println(cus.getName());
		}
		
		Object[] obj = {3};
		QueryResults res = session.getQueryResults("testQuery_params",obj);
		for(QueryResultsRow qr:res){
			Customer cus = (Customer)qr.get("customer");
			System.out.println(cus.getAge());
		}
		session.dispose();
	}
}
