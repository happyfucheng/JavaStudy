package study.java.jdks.version1_5.concurrent;

import java.lang.reflect.Method;


public class MyConcurrent {
	public static void print(String a) {
		System.out.println("I am a string");
	}
	public static void print(Object a) {
		System.out.println("ok");
	}

	public static void print(Integer a) {
		System.out.println("yes");
	}
	public static void print(int a) {
		System.out.println("yesyes");
	}

	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		
		Object a = new String("ok");
			print(a);
		
	}

}
