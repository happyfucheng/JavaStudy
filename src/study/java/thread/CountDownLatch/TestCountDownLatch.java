package study.java.thread.CountDownLatch;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCountDownLatch {
	public static void main(String[] args) throws InterruptedException {
		// 创建CyclicBarrier对象，
		// 并设置执行完一组5个线程的并发任务后，再执行MainTask任务
		long time = new Date().getTime();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		CountDownLatch downLatch = new CountDownLatch(5);
		executorService.execute(new SubTask("A", downLatch));
		executorService.execute(new SubTask("b", downLatch));
		executorService.execute(new SubTask("c", downLatch));
		executorService.execute(new SubTask("d", downLatch));
		executorService.execute(new SubTask("e", downLatch));
		executorService.shutdown();
		downLatch.await();
		System.out.println(new Date().getTime()-time);
	}
}

/**
 * 一组并发任务
 */
class SubTask extends Thread {
	private String name;
	private CountDownLatch downLatch;

	SubTask(String name, CountDownLatch downLatch) {
		this.name = name;
		this.downLatch = downLatch;
	}

	public void run() {
		System.out.println("[并发任务" + name + "]  开始执行");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("[并发任务" + name + "]  开始执行完毕，通知障碍器");
			// 每执行完一项任务就通知障碍器
		downLatch.countDown();
	}
}
