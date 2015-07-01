package study.java.jdks.version1_8.function_interface;

public class FunctionInterfaceTest {
	public static void main(String[] args) {
		Converter<String,Integer> converter = (String from) -> {return  Integer.valueOf(from);};
		Integer converted = converter.convert("123");
		System.out.println(converted + 3);
	}

}