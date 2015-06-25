package study.java.jdks.version1_5.generic;

/**
 * 泛型方法
 * @author hadoop2
 *
 */
public class MyGenericMethod {
	public static <T> T getLast(T[] a) {
		return a[a.length-1];
	}
	
	public  <T> T getFirst(T[] a) {
		return a[0];
	}
	
	public static void main(String[] args) {
		String[] s = new String[]{"fucheng","chengfu","fucc"};
		System.out.println(MyGenericMethod.<String>getLast(s));
		System.out.println(new MyGenericMethod().<String>getFirst(s));
	}
}
