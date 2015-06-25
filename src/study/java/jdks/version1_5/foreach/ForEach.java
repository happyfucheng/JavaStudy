package study.java.jdks.version1_5.foreach;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 简化for循环
 * @author hadoop2
 *
 */
public class ForEach {
	
	public static void main(String[] args) {
		
		//遍历数组和实现了Iterator接口的
		
		
		// 遍历数组
		String[] names = new String[]{"zhangsan","lisi","wangwu","zhaoliu"};
		
		// 不要期待能改变数组元素的值
		for(String name:names) {
			name="fucheng";
		}
		System.out.println(names[0]);
		
		
		// 遍历Set
		Set<String> set = new TreeSet<String>();
		set.add("e1");
		set.add("e2");
		set.add("e3");
		
		for(String s:set) {
			System.out.println(s);
		}
		
		// 遍历Map
		Map<String,String> map = new HashMap<String,String>();
		map.put("k1", "value1");
		map.put("k2", "value2");
		map.put("k3", "value3");
		for(Map.Entry<String, String> entry:map.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			
		}
		
		//使用前必须判断是否为null
/*		int[] ages=null;
		for(int age:ages){
			System.out.println(age);
		}*/
		
		// 自定义一个
		MyForEach<String> myforEach = new MyForEach<String>();
		myforEach.setValue(new String[]{"11","22","33"});
		for(String s:myforEach) {
			System.out.println(s);
		}
		
	}

}
