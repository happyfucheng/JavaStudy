package study.java.thread.interrupt;

public class TestInterrupt implements Runnable {
	@Override
	public void run() {
		int i=0;
		boolean flag = Thread.currentThread().isInterrupted();
		while(!flag){
			System.out.println(i++);
		}
		/*while(true){
			System.out.println(i++);
		}*/
	}
	public static void main(String[] args) {
		TestInterrupt testInterrupt = new TestInterrupt();
		Thread myThread = new Thread(testInterrupt);
		myThread.start();
		myThread.interrupt();
	}
}
