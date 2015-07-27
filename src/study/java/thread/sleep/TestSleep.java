package study.java.thread.sleep;

public class TestSleep implements Runnable {
	@Override
	public void run() {
		for (int i=0;i<10;i++) {
			System.out.println("i="+i);
		}
	}
	public static void main(String[] args) {
		TestSleep testSleep = new TestSleep();
		Thread myThread = new Thread(testSleep);
		myThread.start();
		try {
//			myThread.sleep(1000);
			Thread.sleep(1000);
			System.out.println("main over=========");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
