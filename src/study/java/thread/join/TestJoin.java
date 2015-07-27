package study.java.thread.join;

public class TestJoin implements Runnable{

	@Override
	public void run() {
		for (int i=0;i<10;i++) {
			System.out.println("i="+i);
		}
	}

	public static void main(String[] args) {
		TestJoin testJoin = new TestJoin();
		Thread myThread = new Thread(testJoin);
		myThread.start();
		try {
			myThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i=0;i<10;i++) {
			System.out.println("main i="+i);
		}
	}
}
