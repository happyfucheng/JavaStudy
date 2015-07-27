package study.java.thread.yield;

public class TestYield implements Runnable{
	@Override
	public void run() {
		for (int i=0;i<1000;i++) {
			/*if(i%10==0){
				Thread.currentThread().yield();
			}*/
			System.out.println("TestYield 1="+i);
		}
	}
	public static void main(String[] args) {
		
		TestYield testYield = new TestYield();
		Thread myThread1 = new Thread(testYield);
		//myThread1.setPriority(Thread.NORM_PRIORITY+3);
		TestYield2 testYield2 = new TestYield2();
		Thread myThread2 = new Thread(testYield2);
		//myThread2.setPriority(Thread.NORM_PRIORITY-3);

		myThread1.start();
		myThread2.start();
		
		
	}
}

class TestYield2 implements Runnable{

	@Override
	public void run() {
		for (int i=0;i<1000;i++) {
			System.out.println("TestYield 2="+i);
		}
	}
	
}