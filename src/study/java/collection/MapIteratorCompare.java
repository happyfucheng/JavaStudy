package study.java.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapIteratorCompare {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<1000000;i++) {
			map.put(i+"", "value"+i);
		}
		
		long startTime2 = System.currentTimeMillis();
		Iterator<String> keySetI = map.keySet().iterator();
		while(keySetI.hasNext()) {
			String key = keySetI.next();
			String value = map.get(key);
		}
		System.out.println("通过KeySet遍历耗时:" + (System.currentTimeMillis() - startTime2) + "ms");
		
		
		long startTime1 = System.currentTimeMillis();
		Iterator<Map.Entry<String, String>> entrySetI = map.entrySet().iterator();
		while(entrySetI.hasNext()) {
			Map.Entry<String, String> entry = entrySetI.next();
			String key = entry.getKey();
			String value = entry.getValue();
		}
		System.out.println("通过EntrySety遍历耗时:" + (System.currentTimeMillis() - startTime1) + "ms");
		
		//测试结果：map元素1000000时，entrySet更快
		//通过KeySet遍历耗时:54ms
		//通过EntrySety遍历耗时:37ms

		
	}

}
