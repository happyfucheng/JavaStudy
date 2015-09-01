package study.java.drools.RHS;

import java.util.Collection;
import java.util.Iterator;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

import study.java.drools.apiTest.KnowledgeBuilderTest;


public class DroolsRHSFactDef {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// 获取规则文件当中定义的Address对象并对其进行实例化
		KnowledgeBuilder builder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		builder.add(new ClassPathResource("drools_fact_def.drl",
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
		
		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		// 将KnowledgePackage集合添加到KnowledgeBase当中
		knowledgeBase.addKnowledgePackages(knowledgePackage);
		/*Order order = new Order();
		order.setName("测试定单");
		FactType addressType = knowledgeBase.getFactType("com.ai.drools.factdef", "Address");
		Object add = addressType.newInstance();
		addressType.set(add, "city", "中国上海");
		addressType.set(add, "addressName", "中国上海松江�?);
		// 获取规则文件当中定义的Person对象并对其进行实例化
		FactType personFact = knowledgeBase.getFactType("com.ai.drools.factdef", "Person");
		Object obj = personFact.newInstance();// 实例化该对象f
		personFact.set(obj, "name", "高杰");// 设置该对象的name属�?
		personFact.set(obj, "order", order);
		personFact.set(obj, "address", add);*/
		

		StatefulKnowledgeSession statefulKSession = knowledgeBase
				.newStatefulKnowledgeSession();
		// 插入�?��fact对象
//		statefulKSession.insert(obj);
		statefulKSession.fireAllRules();
		statefulKSession.dispose();
	}
}
