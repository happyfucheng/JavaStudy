package study.java.thread.synchroniz;

public class TestSynchronized {
	private Object obj = new Object();
	public  synchronized void m1(){
		for (int i=0;i<50;i++) {
			System.out.println("m1="+i);
		}
	}
	public  void m2(){
		for (int i=0;i<50;i++) {
			System.out.println("m2="+i);
		}	
	}
	public static synchronized void m3(){
//	public  synchronized void m3(){
		for (int i=0;i<50;i++) {
			System.out.println("m3="+i);
		}
	}
	public  void main(String[] args) {
		TestSynchronized testSynchronized = new TestSynchronized();
		Thread1 thread = new Thread1(testSynchronized);
		thread.start();//m1
		testSynchronized.m2();
		testSynchronized.m3();
	}
}

class Thread1 extends Thread{
	private TestSynchronized testSynchronized;
	public Thread1(TestSynchronized testSynchronized){
		this.testSynchronized=testSynchronized;
	}
	public void run(){
		testSynchronized.m1();
	}
}