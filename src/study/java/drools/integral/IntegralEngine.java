package study.java.drools.integral;

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


public class IntegralEngine {
	public static void main(String[] args) {
		// 设置时间格式
		System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
		KnowledgeBuilder builder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		builder.add(new ClassPathResource("addpoint.drl",
				KnowledgeBuilderTest.class), ResourceType.DRL);
		builder.add(new ClassPathResource("subtractpoint.drl",
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
		IntegralBean bean = new IntegralBean();
		bean.setUserName("hello kity");
		bean.setBackMondy(100d);
		bean.setBuyMoney(500d);
		bean.setBackNums(1);
		bean.setBuyNums(5);
		bean.setBillThisMonth(5);
		bean.setBirthDay(true);
		bean.setPoint(20l);
		
		// 插入�?��fact对象
		statefulKSession.insert(bean);
//		statefulKSession.insert(bean1);
		statefulKSession.fireAllRules();
		System.out.println(bean.getPoint()+"================");
		statefulKSession.dispose();
	}
}
