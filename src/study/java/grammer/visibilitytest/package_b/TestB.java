package study.java.grammer.visibilitytest.package_b;

import study.java.grammer.visibilitytest.package_a.ITest;
import study.java.grammer.visibilitytest.package_a.TestA;

public class TestB extends TestA implements ITest{
	public static void main(String[] args) {
		new TestB().testA();
		System.out.println(a);
	}

}
