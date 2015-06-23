package study.java.jdks.version1_7.new_map;

import java.util.ArrayList;
import java.util.List;


public class MapTest {

	public static void main(String[] args) {
		// 二进制字面量
		byte nByte=(byte)0b0101;
		short nShort = (short)0B0010;
		int nInt = 0b1111;
		long nLong = 0B0100;
		System.out.println(nByte);
		System.out.println(nShort);
		System.out.println(nInt);
		System.out.println(nLong);
		
		// 数字字面量允许使用下划线
		int i = 1_234_576;
		System.out.println(i);
		float f = 1_234.55_66_77_88f;
		System.out.println(f);
		
		// 泛型实例的创建可以通过类型推断来简化
		List<String> list = new ArrayList<>();
		
		// 在可变参数方法中传递非具体化参数,改进编译警告和错误
		
	
	}
	
	public static <T> void addToList(List<T> listArg,T element) {
		listArg.add(element);
	}
	
	public static <T> void addToList(List<T> listArg,T...elements) {
		for(T x:elements) {
			listArg.add(x);
		}
	}
	
	public static void testHeapPollution() {
	    List l = new ArrayList<Number>();
	    List<String> ls = l;       // unchecked warning
	    l.add(0, new Integer(42)); // another unchecked warning
	    String s = ls.get(0);      // ClassCastException is thrown
	}
}
