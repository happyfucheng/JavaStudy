package study.java.jdks.version1_5.generic;

import java.util.ArrayList;

public class MyGenericWildcard {
	public static void main(String[] args) {
		ArrayList<String> as = new ArrayList<String>();
		ArrayList<Object> ao = null;
		// 编译报错
		// ao = as;
		
		ArrayList<?> ao1 = null;
		ao1 = as;
		
		// 创建对象不能使用通配符
		//ArrayList<?> xx = new ArrayList<?>();
	}

}
