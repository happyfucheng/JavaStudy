package study.java.thread.synchroniz;
public class TestWait {
	 public static void main(String[] args) {
		Object obj = new Object();
		TestWait1 wait1 = new TestWait1(obj);
		TestWait2 wait2 = new TestWait2(obj);
		
		new Thread(wait2).start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(wait1).start();
	}
}
class TestWait1 implements Runnable{
	private Object obj;

	public TestWait1 (Object obj){
		this.obj=obj;
	}
	@Override
	public void run() {
		synchronized (obj) {
			System.out.println("TestWait1  wait pre ");
			try {
				//obj.wait(1000);
				obj.wait();
			 System.out.println("TestWait1 wait ofter");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class TestWait2 implements Runnable{
	private Object obj;
	public TestWait2 (Object obj){
		this.obj=obj;
	}
	@Override
	public void run() {
		synchronized (obj) {
				System.out.println("TestWait2  notify pre");
				obj.notify();
				System.out.println("TestWait2 notify ofter");
		}
	}
}