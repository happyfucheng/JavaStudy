package study.java.grammer;

public class InternTest {
	public static void main(String [] args) {
		
		String a = new String("123");
		
		String b = new String("123");
		
		String c = "123";
		
		System.out.println(a == b);
		System.out.println(a.intern() == b.intern());
		System.out.println(a.intern() == c);
	}

}
