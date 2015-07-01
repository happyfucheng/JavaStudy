package study.java.jdks.version1_8.method_refrence;

import study.java.jdks.version1_8.function_interface.Converter;


public class MethodRefrenceTest {
	
	public  String myConverter(Integer s) {
		return String.valueOf(s);
	}
	
	public static void main(String[] args) {
		// Converter必须是一个函数式接口
		Converter<String,Integer> converter1 = Integer::valueOf;
		
		// Converter必须是一个函数式接口
		MethodRefrenceTest mft = new MethodRefrenceTest();
		Converter<Integer,String> converter2 = mft::myConverter;
		
		System.out.println(converter1.convert("123") + 123);
		System.out.println(converter2.convert(123) + "123");
		
		// 注意Person必须是一个函数式接口
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("fu", "cheng");
		System.out.println(person.getFirstName() + person.getLastName());
		
		
	}

}
