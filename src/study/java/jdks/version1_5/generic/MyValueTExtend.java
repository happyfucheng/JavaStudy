package study.java.jdks.version1_5.generic;

import java.io.Serializable;

public class MyValueTExtend<T extends Number&Comparable&Serializable> {
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public static void main(String[] args) {
		
		//编译报错
		//MyValueTExtend<String> my = new MyValueTExtend<String>();
		
		MyValueTExtend<Integer> my = new MyValueTExtend<Integer>();
		my.setValue(128);
		System.out.println(my.getValue());
	}
}
