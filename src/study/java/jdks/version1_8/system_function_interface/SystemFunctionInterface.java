package study.java.jdks.version1_8.system_function_interface;

import java.util.function.Function;
import java.util.function.Predicate;

public class SystemFunctionInterface {
	public static void main(String[] args) {
		// Predicate断言
		Predicate<String> predicate = (s) ->s.length()>0;
		
		System.out.println(predicate.test(""));
		System.out.println(predicate.test("abc"));
		System.out.println(predicate.negate().test("abc"));
		
		// Function
		Function<String,Integer> toInteger = Integer::valueOf;
		Function<String,String> backToString = toInteger.andThen(String::valueOf);
		System.out.println(backToString.apply("123") + 1);	
	}

}
