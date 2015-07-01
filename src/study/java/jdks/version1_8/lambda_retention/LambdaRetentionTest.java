package study.java.jdks.version1_8.lambda_retention;

import study.java.jdks.version1_8.function_interface.Converter;

public class LambdaRetentionTest {
	
	public static int outerStaticNum;
	
	int outerNum;
	
	public void testScope() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };


	}
	
	public static void main(String[] args) {
		final int num = 1;
		
		// 可以访问局部变量，但必须为final型（或不为final型，但值不可更改)
		Converter<Integer,String> stringConverter = (from) ->String.valueOf(from + num);
		
		System.out.println(stringConverter.convert(3));
	}

}
