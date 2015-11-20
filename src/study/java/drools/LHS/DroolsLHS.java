package study.java.drools.LHS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

import study.java.drools.Customer;
import study.java.drools.Order;
import study.java.drools.apiTest.KnowledgeBuilderTest;


public class DroolsLHS {
	public static void main(String[] args) {
		// 设置时间格式
		System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
		KnowledgeBuilder builder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		builder.add(new ClassPathResource("droolslhs.drl",
				KnowledgeBuilderTest.class), ResourceType.DRL);
		// 打印编译错误日志
		if (builder.hasErrors()) {
			System.out.println("规则中存在错误，错误消息如下");
			KnowledgeBuilderErrors kbuidlerErrors = builder.getErrors();
			for (Iterator iter = kbuidlerErrors.iterator(); iter.hasNext();) {
				System.out.println(iter.next());
			}
		}
		// 产生规则包的集合
		Collection<KnowledgePackage> knowledgePackage = builder
				.getKnowledgePackages();

		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		// 将KnowledgePackage集合添加到KnowledgeBase当中
		kbase.addKnowledgePackages(knowledgePackage);

		StatefulKnowledgeSession statefulKSession = kbase
				.newStatefulKnowledgeSession();
		Customer customer = new Customer();
		customer.setAge(25);
		customer.setGender("male");
		customer.setCity("bj");
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setPrice(1200);
		order.setName("test");
		//Contains test
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		customer.setOrders(orders);
		
		
		List<String> orderNames = new ArrayList<String>();
		orderNames.add("test");
		
		// 插入�?��fact对象
		statefulKSession.setGlobal("orderNames", orderNames);
		//statefulKSession.getGlobal(arg0)
		statefulKSession.insert(customer);
		statefulKSession.insert(order);
		statefulKSession.fireAllRules();
		statefulKSession.dispose();
		//System.out.println(customer.getAge());
		
	}
}
