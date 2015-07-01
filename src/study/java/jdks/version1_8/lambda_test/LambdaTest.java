package study.java.jdks.version1_8.lambda_test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class LambdaTest{
	
	public static void main(String[] args) {
		
		List<String> names = Arrays.asList(new String[]{"fucheng","qiaoww","hanyx"});
		
/*		Collections.sort(names, (String a, String b) -> {
		    return b.compareTo(a);
		});*/
		
/*		Collections.sort(names, (String a, String b) -> b.compareTo(a));*/
		
		// 再简洁一点
		Collections.sort(names, (a, b) -> b.compareTo(a));

		// 原始写法 会多出来一个字节码文件
/*		Collections.sort(names, new Comparator<String>() {
		    @Override
		    public int compare(String a, String b) {
		        return b.compareTo(a);
		    }
		});*/


		
		for(String name:names) {
			System.out.println(name);
		}
	}

}
