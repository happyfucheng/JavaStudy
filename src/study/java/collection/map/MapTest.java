package study.java.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

public class MapTest {

	public static void main(String[] args) {
		
		Map<StringBuilder,String> m = new WeakHashMap<StringBuilder,String>();
		StringBuilder a= new StringBuilder("123");
		StringBuilder b = new StringBuilder("456");
		m.put(a, "good");
		m.put(b, "okok");
		m.put(null,"ccc");
		m.put(null, "ddd");
		a.append("4");
		b.append("7");
		a=null;
		b=null;
		System.gc();
		Set<Entry<StringBuilder,String>> set = m.entrySet();
		for(Entry<StringBuilder,String> e:set) {
			System.out.println(e);
		}
	}
}
