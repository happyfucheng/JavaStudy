package study.java.grammer;

import java.lang.reflect.Field;

class Father {
	public int i=1;
	
	private int j = 3;
	
	public void print() {
		System.out.println(i);
	}
	public void fatherOnly() {
		System.out.println("father only");
	}
}
class Son extends Father {
	public int i=2;
	public void print() {
		System.out.println(i);
	}
	public void sonOnly() {
		System.out.println("son only");
	}
}



public class FatherSon {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Father son = new Son();
		// 打印父类
		System.out.println(son.i);
		
		//System.out.println(son.j);
		// 打印子类方法（子类成员变量)
		son.print();
		
		son.fatherOnly();
		
		//son.sonOnly();
		
		System.out.println(son.getClass().getName());
		
		Class clazz = son.getClass();
		System.out.println("clazz.getDeclaredFields()");
		Field[] dfields = clazz.getDeclaredFields();
		for(Field field:dfields) {
			System.out.println(field.getName());
		}
		System.out.println("clazz.getFields()");
		Field[] afields = clazz.getFields();
		for(Field field:afields) {
			System.out.println(field.getName());
			System.out.println(field.get(son));
		}
	}

}
