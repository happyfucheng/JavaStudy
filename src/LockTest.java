
public class LockTest {
	public static int i = 0;
	
	//synchronized 为递归锁
	public static synchronized void  test() {
		if(i<5) {
		i++;
		System.out.println(i);
		test();
		}
	}
	public static void main(String[] args) {
		test();

	}

}
