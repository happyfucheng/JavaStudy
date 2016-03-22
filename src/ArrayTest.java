import java.util.Arrays;


public class ArrayTest {
	public static void main(String[] args) {
		Long[] a = new Long[]{1000222221l,10002l,10003l};
		
		Long[] b = Arrays.copyOf(a, a.length);
		System.out.println(a==b);
		System.out.println(a[0]==b[0]);
	}

}
