package study.java.drools;

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


public class RuleEngine {
	private static RuleEngine ruleEngine = new RuleEngine();

	private RuleEngine() {
	}

	public static RuleEngine getInstance() {
		return ruleEngine;
	}

	public StatefulKnowledgeSession runRules(Object[] facts, String[] ruleFiles) {
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		if (ruleFiles != null) {
			for (String ruleFile : ruleFiles) {
				System.out.println("add ruleFile========" + ruleFile);
				kbuilder.add(new ClassPathResource(ruleFile,
						KnowledgeBuilderTest.class), ResourceType.DRL);
			}
		}
		if (kbuilder.hasErrors()) {
			System.out.println("规则中存在错误，错误消息如下");
			KnowledgeBuilderErrors kbuidlerErrors = kbuilder.getErrors();
			for (Iterator iter = kbuidlerErrors.iterator(); iter.hasNext();) {
				System.out.println(iter.next());
			}
		}
		Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
		kbase.addKnowledgePackages(pkgs);
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		if (facts != null) {
			for (int i = 0; i < facts.length; i++) {
				Object fact = facts[i];
				System.out.println("insert fact: " + fact);
				ksession.insert(fact);
			}
		}
		ksession.fireAllRules();
		ksession.dispose();
		return ksession;
	}

}
