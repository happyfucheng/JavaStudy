package study.java.drools.apiTest;

import java.util.Collection;
import java.util.Iterator;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.impl.ClassPathResource;

public class KnowledgeBuilderTest {

	public static void main(String[] args) {
		KnowledgeBuilder builder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		builder.add(new ClassPathResource("test.drl",
				KnowledgeBuilderTest.class), ResourceType.DRL);
		//打印编译错误日志
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
	}
}
